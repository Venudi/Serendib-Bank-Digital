package com.serendib.services.notification;

public interface NotificationService {
    boolean sendNotification(String recipient, String message);
}
