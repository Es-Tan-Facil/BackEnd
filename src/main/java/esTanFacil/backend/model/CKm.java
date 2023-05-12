package esTanFacil.backend.model;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "totalKm")
public class CKm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Long id;
    @Column (name = "totalKm", nullable = false)
    private int totalKm;

    public void updateTotalKm(int km) {
        this.totalKm += km;
    }

}

