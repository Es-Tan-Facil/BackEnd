package esTanFacil.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name= "news")
public class CNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;
    @Column (name = "title", nullable = false)
    private String title;
    @Column (name = "description",columnDefinition = "LONGTEXT", nullable = false)
    private String description;
    @Column (name = "date", nullable = false)
    private LocalDate date;

    @Column (columnDefinition = "LONGTEXT")
    private String urlImg;
    @PrePersist
    public void setDate() {
        this.date = LocalDate.now();
    }

}