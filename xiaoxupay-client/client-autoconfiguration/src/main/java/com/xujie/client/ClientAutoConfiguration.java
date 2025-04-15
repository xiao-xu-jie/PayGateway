package com.xujie.client;


import com.xujie.client.api.controller.ClientNotifyController;
import com.xujie.client.config.XPayConfig;
import com.xujie.client.core.events.publisher.XOrderEventPublisher;
import com.xujie.client.core.util.RequestUtil;
import com.xujie.client.service.impl.XOrderServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(prefix = "pay", name = "client.enable", havingValue = "true")
@EnableConfigurationProperties(XPayConfig.class)
@Import({RequestUtil.class, XOrderServiceImpl.class, ClientNotifyController.class, XOrderEventPublisher.class})
public class ClientAutoConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("======= X Pay Gateway 加载成功 =======");
        System.out.println(
                """
                        /\\ \\ /\\ \\    /\\  _`\\                   /\\  _`\\           /\\ \\__                                         \s
                        \\ `\\`\\/'/'   \\ \\ \\L\\ \\ __     __  __   \\ \\ \\L\\_\\     __  \\ \\ ,_\\    __   __  __  __     __     __  __   \s
                         `\\/ > <      \\ \\ ,__/'__`\\  /\\ \\/\\ \\   \\ \\ \\L_L   /'__`\\ \\ \\ \\/  /'__`\\/\\ \\/\\ \\/\\ \\  /'__`\\  /\\ \\/\\ \\  \s
                            \\/'/\\`\\    \\ \\ \\/\\ \\L\\.\\_\\ \\ \\_\\ \\   \\ \\ \\/, \\/\\ \\L\\.\\_\\ \\ \\_/\\  __/\\ \\ \\_/ \\_/ \\/\\ \\L\\.\\_\\ \\ \\_\\ \\ \s
                            /\\_\\\\ \\_\\   \\ \\_\\ \\__/.\\_\\\\/`____ \\   \\ \\____/\\ \\__/.\\_\\\\ \\__\\ \\____\\\\ \\___x___/'\\ \\__/.\\_\\\\/`____ \\\s
                            \\/_/ \\/_/    \\/_/\\/__/\\/_/ `/___/> \\   \\/___/  \\/__/\\/_/ \\/__/\\/____/ \\/__//__/   \\/__/\\/_/ `/___/> \\
                                                          /\\___/                                                           /\\___/
                                                          \\/__/                                                            \\/__/\s
                        V1.0.0
                        """
        );
    }
}
