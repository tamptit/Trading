package app.trading.stock.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "industry", schema = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Industry {

    @Id
    @Column(length = 4, nullable = false)
    private String value; // mã ngành, ví dụ "0500"

    @Column(length = 100, nullable = false)
    private String name; // tên ngành, ví dụ "Dầu khí"
}
