import java.util.ArrayList;

public class StocksPortfolio {
    private String name;
    private IStockmarketService stockMarketService;
    private ArrayList<Stock> stockArray = new ArrayList<Stock>();


    public IStockmarketService getMarketService() {
        return this.stockMarketService;
    }

    public void setMarketService(IStockmarketService stockMarketService) {
        this.stockMarketService = stockMarketService;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalValue() {
        Double totalValue = 0.0;
        for (Stock stock: this.stockArray) {
            totalValue = totalValue + (this.stockMarketService.getPrice(stock.getName()) * stock.getQuantity());
        }
        return totalValue;
    }

    public void addStock(Stock stock) {
        this.stockArray.add(stock);
    }

}