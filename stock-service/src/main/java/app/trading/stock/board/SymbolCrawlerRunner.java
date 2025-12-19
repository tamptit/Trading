package app.trading.stock.board;

import app.trading.stock.service.data.SymbolCrawlerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class SymbolCrawlerRunner implements CommandLineRunner {

    private final SymbolCrawlerService crawlerService;

    public SymbolCrawlerRunner(SymbolCrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @Override
    public void run(String... args) throws Exception {
        crawlerService.crawlAndSave();
    }
}
