package ru.bcs.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.bcs.entity.request.Stock;
import ru.bcs.entity.request.StockPortfolioAnalyzeRequest;
import ru.bcs.entity.response.Allocation;
import ru.bcs.entity.response.QuoteResponse;
import ru.bcs.entity.response.StockPortfolioAnalyzeResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/stock-portfolio")
public class StockPortfolioController {

    private final String STOCK_QUOTE_URL = "https://api.iextrading.com/1.0/stock/%s/quote";

    @PostMapping("/analyze")
    public StockPortfolioAnalyzeResponse analyze(@RequestBody StockPortfolioAnalyzeRequest stockPortfolioAnalyzeRequest) {

        List<Stock> stocks = stockPortfolioAnalyzeRequest.getStocks();

        RestTemplate restTemplate = new RestTemplate();

        List<Allocation> allocations = new ArrayList<Allocation>();
        BigDecimal sum = new BigDecimal(0);

        // TODO realize parallel execution
        for (Stock stock : stocks) {
            String url = String.format(STOCK_QUOTE_URL, stock.getSymbol().toLowerCase());
            QuoteResponse quoteResponse = restTemplate.getForObject(url, QuoteResponse.class);

            BigDecimal stocksPrice = quoteResponse.getLatestPrice().multiply(stock.getVolume());

            sum = sum.add(stocksPrice);

            Allocation allocation = allocations.stream().filter(a -> a.getSector().equals(quoteResponse.getSector())).findFirst().orElse(null);
            if (allocation == null) {
                allocations.add(new Allocation(quoteResponse.getSector(), stocksPrice));
            } else {
                allocation.addAssetValue(stocksPrice);
            }
        }

        for (Allocation allocation : allocations) {
            Double proportion = allocation.getAssetValue().doubleValue() / sum.doubleValue();
            proportion = Math.round(proportion * 1000)/1000.0d;
            allocation.setProportion(proportion);
        }

        StockPortfolioAnalyzeResponse stockPortfolioAnalyzeResponse = new StockPortfolioAnalyzeResponse();
        stockPortfolioAnalyzeResponse.setAllocations(allocations);
        stockPortfolioAnalyzeResponse.setValue(sum);

        return stockPortfolioAnalyzeResponse;
    }
}
