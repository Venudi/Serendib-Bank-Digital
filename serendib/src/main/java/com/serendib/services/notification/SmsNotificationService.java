package com.serendib.services.notification;

public class SmsNotificationService implements NotificationService {
    @Override
    public boolean sendNotification(String phoneNumber, String message) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
        return true;
    }
    
}
