package com.ecom.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class NotificationService {
	
	 @Async
	    public void sendWelcomeEmail(String email, String name) {
	        System.out.println("Sending welcome email to " + email);
	        try {
	            Thread.sleep(2000); // simulate delay
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	        System.out.println("Email sent to " + email + " successfully.");
	    }

}
