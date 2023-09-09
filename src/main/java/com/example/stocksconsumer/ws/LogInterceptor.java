package com.example.stocksconsumer.ws;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Логгер обмена
 */
public class LogInterceptor implements ClientHttpRequestInterceptor {

    protected final Logger logger;

    public LogInterceptor(Logger logger) {
        this.logger = logger != null ? logger : LoggerFactory.getLogger(getClass());
    }

    @Override
    public ClientHttpResponse intercept(
            @NotNull HttpRequest req,
            @NotNull byte [] reqBody,
            @NotNull ClientHttpRequestExecution ex) throws IOException {

        if (logger.isInfoEnabled()) {
            logger.info("[REQUEST] {} {}\r\n{}",
                    req.getMethod(), req.getURI(),
                    new String(reqBody, StandardCharsets.UTF_8));
        }

        ClientHttpResponse response = ex.execute(req, reqBody);

        if (logger.isInfoEnabled()) {
            InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
            try (BufferedReader br = new BufferedReader(isr)) {
                String body = br.lines().collect(Collectors.joining("\n"));
                logger.info("[RESPONSE] {}\r\n{}",
                        response.getStatusCode(),
                        StringEscapeUtils.unescapeJava(body));
            }
        }

        return response;
    }

}