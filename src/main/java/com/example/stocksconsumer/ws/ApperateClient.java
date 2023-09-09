package com.example.stocksconsumer.ws;

import com.example.stocksconsumer.config.ApperateConfig;
import com.example.stocksconsumer.entity.Companies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;

public class ApperateClient {

    protected final Logger logger = LoggerFactory.getLogger(ApperateClient.class);


    private final ApperateConfig config;

    protected final RestTemplate restTemplate;


    public ApperateClient(ApperateConfig config) {
        this.config = config;
        this.restTemplate = createRestTemplate();
    }

    private RestTemplate createRestTemplate() {
        return new RestTemplateBuilder()
                .build();
    }


    public String getUrlWithToken(String path) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(config.getUrl())
                .path("/company")
                .queryParam("last", 10)
                .queryParam("token", config.getToken());

        return builder.toUriString();
    }

//    public String makeGetRequest() {
//        // Создайте URL с токеном
//        String url = config.getUrl() + "company?last=1&token=" + config.getToken();
//
//        // Выполните GET-запрос
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        String responseBody = null;
//        // Обработайте ответ, если необходимо
//        if (response.getStatusCode().is2xxSuccessful()) {
//            responseBody = response.getBody();
//            System.out.println("Response: " + responseBody);
//        } else {
//            System.err.println("Request failed with status code: " + response.getStatusCode());
//        }
//        return responseBody;
//    }

    public Companies makeGetRequest() {
        // Создайте URL с токеном
        String url = config.getUrl() + "company?last=10&token=" + config.getToken();

        // Создайте ParameterizedTypeReference для сопоставления с объектом Companies
        ParameterizedTypeReference<Companies> responseType = new ParameterizedTypeReference<Companies>() {};

        // Выполните GET-запрос и сопоставьте ответ с объектом Companies
        ResponseEntity<Companies> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        HttpStatus httpStatus = responseEntity.getStatusCode();
        Companies response = responseEntity.getBody();

        if (HttpStatus.OK != httpStatus || response == null) {
            throw new IllegalStateException("Bad or empty response: " + httpStatus);
        }

        return response;
    }

    public Companies company() {
        logger.debug("company() called");
        long started = System.nanoTime();
        try {

            ResponseEntity<Companies> responseEntity = restTemplate.exchange(
                    getUrlWithToken("/company"),
                    HttpMethod.GET,
                    null,
                    Companies.class
            );

            HttpStatus httpStatus = responseEntity.getStatusCode();
            Companies response = responseEntity.getBody();

            if (HttpStatus.OK != httpStatus || response == null) {
                throw new IllegalStateException("Bad or empty response: " + httpStatus);
            }

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - started);

            logger.debug("completed in {} msecs", elapsed);
            return response;
        } catch (Exception ex) {
            logger.error("penalties() raised exception: {}", ex.toString());
            throw ex;
        }
    }




}
