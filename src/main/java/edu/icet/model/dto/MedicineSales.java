package edu.icet.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MedicineSales {
    private Long medicineSalesId;
    private  Long customerId;
    private Long medicineId;
    private Integer quanatity;
}

