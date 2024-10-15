package com.bicky.demopayment.productservice.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ProductFilterPayload {
    private String name;
    private Integer page;
    private Integer pageSize;
    private String sortBy;

    public Integer getPage() {
        if (page < 1) {
            page = 1;
        }
        return page;
    }
}
