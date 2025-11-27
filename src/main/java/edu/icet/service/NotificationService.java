package edu.icet.service;

import edu.icet.model.dto.Notification;
import javafx.collections.ObservableList;

public interface NotificationService {
    ObservableList<Notification> getAll();

    ObservableList<Notification> getQuantityUpdates();
}
