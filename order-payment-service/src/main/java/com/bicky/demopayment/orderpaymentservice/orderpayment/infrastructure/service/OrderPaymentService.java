package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.service;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.Order;
import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.OrderPayment;
import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.PaymentMethod;
import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.repository.OrderPaymentRepository;
import com.bicky.demopayment.orderpaymentservice.orderpayment.exception.PaymentServiceException;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.OrderClient;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.PaymentClient;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.request.CreatePaymentRequestBody;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response.CreatePaymentResponseBody;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response.GetOrderResponseBody;
import com.bicky.demopayment.orderpaymentservice.orderpayment.valueobject.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPaymentService {

    private final PaymentClient paymentClient;
    private final OrderClient orderClient;
    private final OrderPaymentRepository orderPaymentRepository;

    public boolean saveOrderPayment(Long orderId, Long paymentMethodId) {
        try {
            OrderPayment existOrderPayment = orderPaymentRepository.findByOrderIdAndPaymentMethodId(
                    orderId, paymentMethodId
            );

            if (!OrderPayment.isEmptyObject(existOrderPayment)) {
                return false;
            }

            GetOrderResponseBody orderResponseBody = orderClient.getOrder(orderId).getBody();
            if (orderResponseBody == null) {
                return false;
            }
            CreatePaymentResponseBody paymentResponseBody = paymentClient.createPayment(
                    CreatePaymentRequestBody.of(paymentMethodId, orderResponseBody.getTotalAmount())
            ).getBody();
            if (paymentResponseBody == null) {
                return false;
            }
            PaymentMethod paymentMethod = PaymentMethod.builder()
                    .id(paymentMethodId)
                    .build();
            Order order = Order.builder()
                    .id(orderId)
                    .build();

            OrderPayment orderPayment = OrderPayment.builder()
                    .paymentMethod(paymentMethod)
                    .order(order)
                    .build();
            orderPaymentRepository.save(orderPayment);

        } catch (PaymentServiceException e) {
            return false;
        }
        return true;
    }
}
