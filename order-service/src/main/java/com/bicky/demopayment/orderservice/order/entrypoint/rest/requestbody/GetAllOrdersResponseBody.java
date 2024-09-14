package com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;

import java.util.List;

public class GetAllOrdersResponseBody {
    private List<Order> orders;
}
