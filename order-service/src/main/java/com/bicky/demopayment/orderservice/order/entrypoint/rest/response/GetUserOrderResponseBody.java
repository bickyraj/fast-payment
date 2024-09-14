package com.bicky.demopayment.orderservice.order.entrypoint.rest.response;

import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;
import com.bicky.demopayment.orderservice.order.domain.entity.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor(staticName = "of")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class GetUserOrderResponseBody {
    private Long id;
    private List<ResponseOrderItem> orderItems;

    @AllArgsConstructor(staticName = "of")
    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class ResponseOrderItem {
        private Long id;
        private Product product;
    }
}