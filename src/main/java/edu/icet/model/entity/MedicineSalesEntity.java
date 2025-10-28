package edu.icet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class MedicineSalesEntity {
    @Id
    private Long medicineSalesId;
    private  Long customerId;
    private Long medicineId;
    private Integer quanatity;
}
