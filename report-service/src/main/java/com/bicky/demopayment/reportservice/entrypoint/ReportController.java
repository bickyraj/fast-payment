package com.bicky.demopayment.reportservice.entrypoint;

import com.bicky.demopayment.reportservice.application.GetReportsUseCase;
import com.bicky.demopayment.reportservice.domain.entity.Purchase;
import com.bicky.demopayment.reportservice.entrypoint.response.GetReportResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {
    private final GetReportsUseCase getReportsUseCase;
    @GetMapping("")
    public ResponseEntity<List<GetReportResponseBody>> getReports() {
        GetReportsUseCase.Response response = getReportsUseCase.execute();
        return ResponseEntity.ok(response.getPurchaseList().stream().map(GetReportResponseBody::fromPurchase).toList());
    }
}
