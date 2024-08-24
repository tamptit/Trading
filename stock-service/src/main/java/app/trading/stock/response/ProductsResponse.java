package app.trading.stock.response;

import app.trading.stock.mf.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductsResponse {
    private List<Products> productsList;
    private String errorMessage = "OK";

    public ProductsResponse() {
    }
}
