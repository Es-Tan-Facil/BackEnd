package esTanFacil.backend.model;

import jakarta.persistence.*;


@Entity
public class CDonations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int kmDonated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_km_donated_id")
    private int addKm;

}

