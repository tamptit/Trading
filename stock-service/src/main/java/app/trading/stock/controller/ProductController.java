package app.trading.stock.controller;

import app.trading.stock.entity.Symbols;
import app.trading.stock.repositories.SymbolsRepository;
import app.trading.stock.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/public/products")
@CrossOrigin(origins = "http://localhost:3000")
//@Api(value = "Products in Stock Service", description = "Operations pertaining to product management")
public class ProductController {

    @Autowired
    private SymbolsRepository symbolsRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ProductsResponse getProducts(){
        ProductsResponse response = new ProductsResponse();
        response.setSymbolsList(symbolsRepository.findAll());
        return response;
    }

    @GetMapping(value = "/welcome")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getWelcome() {
        return "Welcome Userrrrrr";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String addProduct(@RequestBody Symbols symbols){
        symbolsRepository.save(symbols);
        return "OK";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String deleteProduct(@RequestBody Symbols symbolsDTO){
        Optional<Symbols> product = symbolsRepository.findById(symbolsDTO.getCode());
        if(product.isPresent()){
            symbolsRepository.delete(product.get());
        }else{
            return "not found id";
        }
        return "DELETED";
    }


}
