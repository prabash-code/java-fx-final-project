package edu.icet.service.impl;

import edu.icet.model.dto.Notification;
import edu.icet.repository.NotificationRepository;
import edu.icet.repository.impl.NotificationRepositoryImpl;
import edu.icet.service.NotificationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationServiceImpl implements NotificationService {
    NotificationRepository notificationRepository=new NotificationRepositoryImpl();
    ObservableList <Notification>list= FXCollections.observableArrayList();
    ObservableList<Notification>qtyList=FXCollections.observableArrayList();
    @Override
    public ObservableList<Notification> getAll() {
        ResultSet allNotifications = notificationRepository.getAllNotifications();


        try{
            while(allNotifications.next()){
                list.add(new Notification(allNotifications.getString("medicineId"),
                        allNotifications.getString("supplierId"),
                        allNotifications.getString("name"),
                        allNotifications.getDate("expireDate").toLocalDate(),
                        allNotifications.getInt("quantity"),
                        allNotifications.getString("email")
                ));
            }
            return  list;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Notification> getQuantityUpdates() {
        ResultSet allQtyNotifications = notificationRepository.getAllNotifications();
        try{
            while(allQtyNotifications.next()){
                qtyList.add(new Notification(allQtyNotifications.getString("medicineId"),
                        allQtyNotifications.getString("supplierId"),
                        allQtyNotifications.getString("name"),
                        allQtyNotifications.getDate("expireDate").toLocalDate(),
                        allQtyNotifications.getInt("quantity"),
                        allQtyNotifications.getString("email")
                ));
            }
            return  qtyList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
