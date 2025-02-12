package com.xujie.config;

import cn.hutool.core.util.PrimitiveArrayUtil;
import cn.hutool.json.JSONUtil;
import com.xujie.util.SkywalkingUtil;
import io.netty.buffer.ByteBufAllocator;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
public class ApiLogFilter implements GlobalFilter, Ordered {

    private static final String START_TIME = "startTime";

    private static final String X_REAL_IP = "X-Real-IP";

    private final DataBuffer emptyBuffer = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT).allocateBuffer(0);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        SkywalkingUtil.putTidIntoMdc(exchange);

        //请求的基本信息
        List<String> ips = exchange.getRequest().getHeaders().get(X_REAL_IP);
        String ip = ips != null ? ips.get(0) : null;
        String method = exchange.getRequest().getMethod().name();
        String path = exchange.getRequest().getURI().getPath();

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        ServerHttpRequest request = exchange.getRequest();
        if (path.contains("api-docs")) {
            log.warn("{}正在请求接口文档{}", ip, path);
            return chain.filter(exchange.mutate().request(request).build());
        }

        //打印请求开始日志
        log.info("请求开始:\n\n方法:{}\nHost:{}\nip:{}\n请求接口:{}\n", method, exchange.getRequest().getURI().getHost(), ip,
                path);

        //打印header头
        log.info("请求头:\n\n{}\n", JSONUtil.toJsonStr(exchange.getRequest().getHeaders()));

        //包装response,在任意地方返回打印结束信息等
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            @NotNull
            public Mono<Void> writeWith(@NotNull Publisher<? extends DataBuffer> body) {
                SkywalkingUtil.putTidIntoMdc(exchange);
                Long startTime = exchange.getAttribute(START_TIME);
                if (startTime != null) {
                    Long executeTime = (System.currentTimeMillis() - startTime);
                    int code = 500;
                    if (exchange.getResponse().getStatusCode() != null) {
                        code = exchange.getResponse().getStatusCode().value();
                    }
                    log.info("请求结束信息:\n\n响应状态码：{}\n请求耗时：{}ms\n", code, executeTime);
                }

                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        DataBufferUtils.release(dataBuffer);
                        String data = new String(content, StandardCharsets.UTF_8);//data
                        log.info("请求结束返回: \n\n{}\n", data);
                        return bufferFactory.wrap(content);
                    }));
                } else {
                    log.error("<--- {} 响应code异常", getStatusCode());
                }
                MDC.clear();
                return super.writeWith(body);
            }
        };
        //打印请求参数
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        if (!queryParams.isEmpty()) {
            log.info("请求参数(url)：\n\n{}\n", queryParams);
        }

        if (HttpMethod.POST.name().equals(method)) {
            return DataBufferUtils.join(request.getBody()).defaultIfEmpty(emptyBuffer).flatMap(dataBuffer -> {
                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                if (PrimitiveArrayUtil.isNotEmpty(bytes)) {
                    dataBuffer.read(bytes);
                    String bodyString = new String(bytes, StandardCharsets.UTF_8);
                    try {
                        bodyString = UriUtils.decode(bodyString, "UTF-8");
                    } catch (Exception e) {
                        log.error("请求参数编码错误有特殊符号", e);
                    }
                    log.info("请求参数(form)：\n\n{}\n", bodyString);
                    exchange.getAttributes().put("POST_BODY", bodyString);
                    DataBufferUtils.release(dataBuffer);
                    Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                        return Mono.just(buffer);
                    });
                    ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(request) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return cachedFlux;
                        }
                    };
                    return chain.filter(exchange.mutate().request(mutatedRequest).response(decoratedResponse).build());
                } else {
                    return chain.filter(exchange.mutate().request(request).response(decoratedResponse).build());
                }
            });
        }
        SkywalkingUtil.putTidIntoMdc(exchange);

        return chain.filter(exchange.mutate().request(request).response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
