package com.bicky.demopayment.paymentservice.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Exclude specific URLs from being treated as static resources
        registry.addResourceHandler("/payment/payment-method")
                .addResourceLocations("classpath:/templates/")
                .setCachePeriod(0); // Disable caching for troubleshooting
    }
}
