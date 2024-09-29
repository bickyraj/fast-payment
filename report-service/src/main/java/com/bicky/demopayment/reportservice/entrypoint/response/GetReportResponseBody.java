package com.bicky.demopayment.reportservice.entrypoint.response;

import com.bicky.demopayment.reportservice.domain.entity.Purchase;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReportResponseBody {
    private double amount;
    private LocalDateTime createdAt;

    public static GetReportResponseBody fromPurchase(Purchase purchase) {
        return new GetReportResponseBody(purchase.getAmount(), purchase.getCreatedAt());
    }
}
