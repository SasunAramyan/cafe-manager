package com.boot.cafemanager.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfig {

    public MappingJackson2HttpMessageConverter getJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        converter.setObjectMapper(mapper);
        return converter;
    }

    public RequestResponseLoggingInterceptor getLoggingInterceptor() {
        return new RequestResponseLoggingInterceptor();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));

        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(getLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

        final List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(getJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    static class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

        public static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingInterceptor.class);


        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            traceRequest(request, body);
            ClientHttpResponse response = execution.execute(request, body);
            traceResponse(response);
            return response;
        }

        private void traceRequest(HttpRequest request, byte[] body) {
            logger.debug("===========================request begin================================================");
            logger.debug("URI         : {}", request.getURI());
            logger.debug("Method      : {}", request.getMethod());
            logger.debug("Headers     : {}", request.getHeaders());
            logger.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
            logger.debug("==========================request end================================================");
        }

        private void traceResponse(ClientHttpResponse response) throws IOException {
            StringBuilder inputStringBuilder = new StringBuilder();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    inputStringBuilder.append(line);
                    inputStringBuilder.append('\n');
                    line = bufferedReader.readLine();
                }
                logger.debug("============================response begin==========================================");
                logger.debug("Status code  : {}", response.getStatusCode());
                logger.debug("Status text  : {}", response.getStatusText());
                logger.debug("Headers      : {}", response.getHeaders());
                logger.debug("Response body: {}", inputStringBuilder);
                logger.debug("=======================response end=================================================");


            }
        }

    }
}
