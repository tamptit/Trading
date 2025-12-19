package app.trading.stock.entity;
import io.micrometer.core.ipc.http.HttpSender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "symbols")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Symbols {
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    @Column(name = "company_name", length = 500, nullable = false)
    private String companyName;

    @Column(name = "floor_id")
    private String floorId;

    @Column(name = "industry_id")
    private String industryId;

    @Column(name = "floor_name")
    private String floorName;

    @Column(name = "industry_name")
    private String industryName;

}