package ru.bcs.entity.request;

import java.math.BigDecimal;

public class Stock {
    private String symbol;

    private BigDecimal volume;

    public Stock(String symbol, BigDecimal volume) {
        this.symbol = symbol;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}
