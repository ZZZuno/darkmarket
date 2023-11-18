package com.dark.service;

import com.dark.dto.EmailDTO;

public interface EmailService {

	void sendMail(EmailDTO dto, String message);
}