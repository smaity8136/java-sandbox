package com.seedollar.sandbox.moneyexchange.conversion.dto;

import java.math.BigDecimal;

public class CurrencyConversionDTO {

    private Long id;

    private String from;

    private String to;

    private BigDecimal conversionMultiple;

    private BigDecimal quantity;

    private BigDecimal totalCalculatedAmount;

    private Integer port;

    public CurrencyConversionDTO() {}

    public CurrencyConversionDTO(Long id, String from, String to, BigDecimal conversionMultiple, BigDecimal quantity, BigDecimal totalCalculatedAmount, Integer port) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.quantity = quantity;
        this.totalCalculatedAmount = totalCalculatedAmount;
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Integer getPort() {
        return port;
    }

    public BigDecimal getTotalCalculatedAmount() {
        return totalCalculatedAmount;
    }
}
