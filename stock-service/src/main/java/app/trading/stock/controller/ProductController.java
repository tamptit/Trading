package app.trading.stock.controller;

import app.trading.stock.mf.Products;
import app.trading.stock.mf.ProductRepository;
import app.trading.stock.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ProductsResponse getProducts(){
        ProductsResponse response = new ProductsResponse();
        response.setProductsList(productRepository.findAll());
        return response;
    }

    @GetMapping(value = "/welcome")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getWelcome() {
        return "Welcome Userrrrrr";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String addProduct(@RequestBody Products product){
        productRepository.save(product);
        return "OK";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String deleteProduct(@RequestBody Products productDTO){
        Optional<Products> product = productRepository.findById(productDTO.getId());
        if(product.isPresent()){
            productRepository.delete(product.get());
        }else{
            return "not found id";
        }
        return "DELETED";
    }


}
