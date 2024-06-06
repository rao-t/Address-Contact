package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.client.ClientHttpRequestFactory;
// import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // @Bean
    // public RestTemplate restTemplate() {
    //     return new RestTemplate(clientHttpRequestFactory());
    // }

    // private ClientHttpRequestFactory clientHttpRequestFactory() {
    //     SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    //     factory.setConnectTimeout(3000);  // 3 seconds connection timeout
    //     factory.setReadTimeout(5000);     // 5 seconds read timeout
    //     return factory;
    // }
}
