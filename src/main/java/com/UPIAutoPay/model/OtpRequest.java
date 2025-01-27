package com.UPIAutoPay.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpRequest {

    private String mobileNo;
    private String otpCode;
}
