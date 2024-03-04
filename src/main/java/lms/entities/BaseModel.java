package lms.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    private Long id;
    private LocalDate createdAd;
    private LocalDate updateAd;

    @PrePersist
    private void prePersist() {
        this.createdAd = LocalDate.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updateAd = LocalDate.now();
    }
}
