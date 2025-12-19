package app.trading.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citizen")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id")
    private String pId;

    @Column(name="full_name")
    private String full_name;

    @Column(name = "phone_number")
    private String phone_number;

    // Java field names are in snake_case as requested
    @Column(name = "noi_o_tinh")
    private String noi_o_tinh;

    @Column(name = "noi_o_phuong_xa")
    private String noi_o_phuong_xa;

    @Column(name = "noi_o_dia_chi")
    private String noi_o_dia_chi;


}
