package com.bicky.demopayment.frontendservice.frontend.config;

import com.bicky.demopayment.frontendservice.frontend.exception.PaymentServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new PaymentServiceException("Internal Server Error occurred in payment-service");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
