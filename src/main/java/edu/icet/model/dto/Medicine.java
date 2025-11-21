package edu.icet.model.dto;

import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Medicine {
    private String medicineId;
    private String brand;
    private String name;
    private String suppliedId;
    private Double unitPrice;
    private int quantity;
    private LocalDate manufactureDate;
    private LocalDate expireDate;


}
