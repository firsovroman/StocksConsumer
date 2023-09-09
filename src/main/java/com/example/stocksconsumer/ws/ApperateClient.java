package com.example.stocksconsumer.ws;

import com.example.stocksconsumer.config.ApperateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ApperateClient {

    protected final Logger logger = LoggerFactory.getLogger(ApperateClient.class);


    private final ApperateConfig config;

    protected final RestTemplate restTemplate;

    private final LogInterceptor logInterceptor;


    public ApperateClient(ApperateConfig config) {
        this.config = config;
        this.restTemplate = createRestTemplate();
        this.logInterceptor = new LogInterceptor(logger);
    }

    private RestTemplate createRestTemplate() {

        return new RestTemplateBuilder()
                .additionalCustomizers(rt -> rt.setRequestFactory(createHttpRequestFactory()))
                .additionalInterceptors((request, body, execution) -> {
                    // Добавляем заголовок с токеном авторизации
                    request.getHeaders().add("Authorization", config.getToken());
                    return execution.execute(request, body);
                })
                .additionalInterceptors(logInterceptor)
                .build();
    }


    /**
     * Фабрика подключений
     * после использования надо обязательно уничтожать
     *
     * @return
     */
    private ClientHttpRequestFactory createHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(config.getConnTimeout());
        factory.setConnectTimeout(config.getConnTimeout());
        factory.setReadTimeout(config.getReadTimeout());
        return factory;
    }

}
