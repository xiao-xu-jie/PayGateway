package com.xujie.client.core.util;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.net.HttpHeaders;
import com.xujie.client.config.XPayConfig;
import com.xujie.client.core.exceptio.XOrderException;
import com.xujie.client.dto.XOrderDto;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.Map;

@Slf4j
@Data
public class RequestUtil {

    private WebClient webClient;

    private final String createOrderPath = "/order/create";

    public XOrderDto.XOrderCreateResponse createOrderRequest(Map<String, Object> request) {
        String response = webClient.post()
                .uri(createOrderPath)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(request)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // 配置 Hutool 的日期格式
        JSONConfig config = JSONConfig.create()
                .setDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject jsonObject = JSONUtil.parseObj(response, config);
        Integer code = jsonObject.getInt("code");
        if (code == null || code != 200) {
            throw new XOrderException("请求返回异常");
        }
        JSONObject data = jsonObject.getJSONObject("data");
        XOrderDto.XOrderCreateResponse bean = JSONUtil.toBean(data.toString(), config, XOrderDto.XOrderCreateResponse.class);
        return bean;
    }


    public RequestUtil(XPayConfig config) {
        // 配置固定大小连接池
        ConnectionProvider provider =
                ConnectionProvider.builder("x-request")
                        // 等待超时时间
                        .pendingAcquireTimeout(Duration.ofSeconds(60))
                        // 最大连接数
                        .maxConnections(200)
                        // 最大空闲时间
                        .maxIdleTime(Duration.ofSeconds(60 * 30))
                        // 最大等待连接数量
                        .pendingAcquireMaxCount(1000)
                        .build();

        /**
         * doOnBind 当服务器channel即将被绑定的时候调用。 doOnBound 当服务器channel已经被绑定的时候调用。 doOnChannelInit
         * 当channel初始化的时候被调用。 doOnConnection 当一个远程客户端连接上的时候被调用。 doOnUnbound 当服务器channel解绑的时候被调用。
         */
        HttpClient httpClient =
                HttpClient.create(provider)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .responseTimeout(Duration.ofSeconds(60))
                        .keepAlive(true)
                        // 连接成功
                        .doOnConnected(
                                connection ->
                                        connection
                                                .addHandlerLast(new ReadTimeoutHandler(60))
                                                .addHandlerLast(new WriteTimeoutHandler(60)))
                        // 每次请求后执行flush，防止服务器主动断开连接
                        .doAfterRequest(
                                (httpClientRequest, connection) -> {
                                    connection.channel().alloc().buffer().release();
                                    connection.channel().flush();
                                    connection.channel().pipeline().flush();
                                });
        log.info("X-Request 请求工具初始化成功：{}", config.getServerUrl());
        webClient = WebClient.builder()
                .baseUrl(config.getServerUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONNECTION, "keep-alive")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(logRequest())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest
                    .headers()
                    .forEach((name, values) -> values.forEach(value -> log.debug("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }
}
