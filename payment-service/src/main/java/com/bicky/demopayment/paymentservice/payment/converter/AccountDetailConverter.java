package com.bicky.demopayment.paymentservice.payment.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;

import java.io.IOException;

@Converter(autoApply = true)
public class AccountDetailConverter implements AttributeConverter<AccountDetails, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AccountDetails accountDetails) {
        try {
            return objectMapper.writeValueAsString(accountDetails);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting map to JSON string.", e);
        }
    }

    @Override
    public AccountDetails convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, AccountDetails.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to map.", e);
        }
    }
}
