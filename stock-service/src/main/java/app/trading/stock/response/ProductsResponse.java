package app.trading.stock.response;

import app.trading.stock.entity.Symbols;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductsResponse {
    private List<Symbols> symbolsList;
    private String errorMessage = "OK";

    public ProductsResponse() {
    }
}
