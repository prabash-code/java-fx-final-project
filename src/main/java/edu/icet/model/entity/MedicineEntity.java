package edu.icet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class MedicineEntity {
    @Id
    private Long medicineId;
    private Long suppliedId;
    private String name;
    private String brand;
    private String category;
    private LocalDate expireDate;
    private String quantity;
    private Double unitPrice;
}
