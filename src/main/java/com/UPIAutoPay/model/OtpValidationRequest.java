package com.UPIAutoPay.model;

import lombok.Data;

@Data
public class OtpValidationRequest {
    private String mobileNo;
    private String otpCode;
}