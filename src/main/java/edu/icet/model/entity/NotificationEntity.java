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
public class NotificationEntity {
    @Id
    private Long notificationId;
    private Long userId;
    private String type;
    private String message;
    private String createdDate;
}
