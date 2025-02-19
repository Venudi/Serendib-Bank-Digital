package com.serendib.services.notification;

public class EmailNotificationService implements NotificationService {
    @Override
    public boolean sendNotification(String email, String message) {
        System.out.println("Sending Email to " + email + ": " + message);
        return true;
    }
}