package com.bicky.demopayment.reportservice.application;

import com.bicky.demopayment.reportservice.domain.entity.Purchase;
import com.bicky.demopayment.reportservice.infrastructure.services.ReportService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReportsUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final List<Purchase> purchaseList;
    }

    private final ReportService reportService;
    public Response execute() {
        return Response.of(reportService.getUserReports());
    }
}
