package com.bicky.demopayment.paymentservice.config.feign;

import com.bicky.demopayment.paymentservice.exception.PaymentServiceException;
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
        } else if (response.status() == HttpStatus.FORBIDDEN.value()) {
            return new PaymentServiceException("Not authorized to access");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
