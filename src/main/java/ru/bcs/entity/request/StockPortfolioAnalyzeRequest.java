package ru.bcs.entity.request;

import java.util.List;

public class StockPortfolioAnalyzeRequest {
    private List<Stock> stocks;

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
