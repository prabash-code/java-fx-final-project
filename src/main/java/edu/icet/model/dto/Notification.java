package edu.icet.model.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Notification {

    private String medicineId;
    private String supplierId;
    private String name;
    private LocalDate expireDate;
    private Integer qty;
    private String email;

}
