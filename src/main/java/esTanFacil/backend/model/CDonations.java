package esTanFacil.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "donations")
public class CDonations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Long id;
    @Column (name = "name", nullable = false)
    private String name;
    @Column (name = "kmDonated", nullable = false)
    private int kmDonated;



}

