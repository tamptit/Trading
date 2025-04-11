package app.trading.stock.mf;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="product_id")
    private String productId;
    @Column(name="name_inform")
    private String nameInformation;
    @Column(name="value_inform")
    private String valueInformation;

    public Products() {
    }

    public Products(String productId, String nameInformation, String valueInformation) {
        this.productId = productId;
        this.nameInformation = nameInformation;
        this.valueInformation = valueInformation;
    }

//    public Product(int id, String productId, String nameInformation, String valueInformation) {
//        this.id = id;
//        this.productId = productId;
//        this.nameInformation = nameInformation;
//        this.valueInformation = valueInformation;
//    }
}
