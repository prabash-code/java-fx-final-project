package edu.icet.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Notification {
    private Long notificationId;
    private Long userId;
    private String type;
    private String message;
    private String createdDate;
}
