package ru.bcs.entity.response;

import java.math.BigDecimal;

public class QuoteResponse {
    private String sector;

    private BigDecimal latestPrice;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(BigDecimal latestPrice) {
        this.latestPrice = latestPrice;
    }
}
