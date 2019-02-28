package ru.bcs.entity.response;

import java.math.BigDecimal;

public class Allocation {

    private String sector;

    private BigDecimal assetValue;

    private Double proportion;

    public Allocation(String sector, BigDecimal assetValue) {
        this.sector = sector;
        this.assetValue = assetValue;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public BigDecimal getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    public void addAssetValue(BigDecimal assetValue) {
        this.assetValue = this.assetValue.add(assetValue);
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }
}
