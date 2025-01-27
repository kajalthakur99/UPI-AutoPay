package com.UPIAutoPay.service;

import com.UPIAutoPay.entity.CustomerDetails;
import com.UPIAutoPay.model.OtpValidationRequest;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.ResourceBundle;

public interface CustomerService {

    ResponseEntity<?> resetPassword(String applicationNo);

    ResponseEntity<?> matchOtp(OtpValidationRequest otpValidationRequest);
}
