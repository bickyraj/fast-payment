package com.bicky.demopayment.paymentservice.payment.converter;

import com.bicky.demopayment.paymentservice.shared.valueobject.CardDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter(autoApply = true)
public class CardDetailsConverter implements AttributeConverter<CardDetail, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(CardDetail cardDetail) {
        try {
            return objectMapper.writeValueAsString(cardDetail);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting map to JSON string.", e);
        }
    }

    @Override
    public CardDetail convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, CardDetail.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to map.", e);
        }
    }
}
