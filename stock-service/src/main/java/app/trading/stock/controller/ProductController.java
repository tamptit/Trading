package app.trading.stock.controller;

import app.trading.stock.mf.Products;
import app.trading.stock.mf.ProductRepository;
import app.trading.stock.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    ProductsResponse getProducts(){
        ProductsResponse response = new ProductsResponse();
        response.setProductsList(productRepository.findAll());
        return response;
    }

    @PostMapping("/product")
    String addProduct(@RequestBody Products product){
        productRepository.save(product);
        return "OK";
    }


}
