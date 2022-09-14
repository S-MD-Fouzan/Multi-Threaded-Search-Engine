package com.amadeus.searchEngineV2.email;

public interface IEmailService {
	public void sendSimpleEmail(
    		String toEmail,
            String subject,
            String body
    );

}
