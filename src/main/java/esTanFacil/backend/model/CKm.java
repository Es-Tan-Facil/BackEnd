package esTanFacil.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CKm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalKm;

    @OneToMany(mappedBy = "addKm")
    private List<CDonations> donations;




}

