package com.UPIAutoPay.model;
import lombok.Data;

@Data
public class ResetPasswordResponse
{
    private CommonResponse commonResponse;

    private Long otpId;
    private String otpCode;
    private String mobileNo;
}