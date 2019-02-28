package ru.bcs.entity.response;

import java.math.BigDecimal;
import java.util.List;

public class StockPortfolioAnalyzeResponse {
    private BigDecimal value;

    private List<Allocation> allocations;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }
}
