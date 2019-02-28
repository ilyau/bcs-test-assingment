package ru.bcs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bcs.controller.StockPortfolioController;
import ru.bcs.entity.request.Stock;
import ru.bcs.entity.request.StockPortfolioAnalyzeRequest;
import ru.bcs.entity.response.Allocation;
import ru.bcs.entity.response.StockPortfolioAnalyzeResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class StockPortfolioControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @InjectMocks
    StockPortfolioController stockPortfolioController;

    @Test
    public void testAnalyticsRest() {
        List<Stock> stockList = new ArrayList<Stock>();
        stockList.add(new Stock("AAPL", new BigDecimal(50)));
        stockList.add(new Stock("HOG", new BigDecimal(10)));
        stockList.add(new Stock("MDSO", new BigDecimal(1)));
        stockList.add(new Stock("IDRA", new BigDecimal(1)));
        stockList.add(new Stock("MRSN", new BigDecimal(1)));

        StockPortfolioAnalyzeRequest stockPortfolioAnalyzeRequest = new StockPortfolioAnalyzeRequest();
        stockPortfolioAnalyzeRequest.setStocks(stockList);

        //

        String url = "http://localhost:" + this.port + "/stock-portfolio/analyze";
        String requestJson = "{\n" +
                "    \"stocks\": [\n" +
                "        {\n" +
                "            \"symbol\": \"AAPL\",\n" +
                "            \"volume\": 50\n" +
                "        },\n" +
                "        {\n" +
                "            \"symbol\": \"HOG\",\n" +
                "            \"volume\": 10\n" +
                "        },\n" +
                "        {\n" +
                "            \"symbol\": \"MDSO\",\n" +
                "            \"volume\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"symbol\": \"IDRA\",\n" +
                "            \"volume\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"symbol\": \"MRSN\",\n" +
                "            \"volume\": 1\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<StockPortfolioAnalyzeResponse> answer = restTemplate.exchange(url, HttpMethod.POST, entity, StockPortfolioAnalyzeResponse.class);

        assertEquals(HttpStatus.OK, answer.getStatusCode());

        List<Allocation> allocations = answer.getBody().getAllocations();
        assertEquals("Technology", allocations.get(0).getSector());
        assertEquals("Consumer Cyclical", allocations.get(1).getSector());
        assertEquals("Healthcare", allocations.get(2).getSector());
    }

    @Test
    public void testAnalyticsAction() {
        List<Stock> stockList = new ArrayList<Stock>();
        stockList.add(new Stock("AAPL", new BigDecimal(50)));
        stockList.add(new Stock("HOG", new BigDecimal(10)));
        stockList.add(new Stock("MDSO", new BigDecimal(1)));
        stockList.add(new Stock("IDRA", new BigDecimal(1)));
        stockList.add(new Stock("MRSN", new BigDecimal(1)));

        StockPortfolioAnalyzeRequest stockPortfolioAnalyzeRequest = new StockPortfolioAnalyzeRequest();
        stockPortfolioAnalyzeRequest.setStocks(stockList);

        StockPortfolioAnalyzeResponse stockPortfolioAnalyzeResponse = stockPortfolioController.analyze(stockPortfolioAnalyzeRequest);

        List<Allocation> allocations = stockPortfolioAnalyzeResponse.getAllocations();
        assertEquals("Technology", allocations.get(0).getSector());
        assertEquals("Consumer Cyclical", allocations.get(1).getSector());
        assertEquals("Healthcare", allocations.get(2).getSector());
    }

}
