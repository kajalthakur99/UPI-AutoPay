package com.UPIAutoPay.model;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OtpVerifyResponse {

    private String custName;
    private String loanNo;
    private String mobileNo;
    private String email;
    private Date startDate;
    private Date expiryDate;
    private String amount;



    public OtpVerifyResponse() {
        this.custName = "";
        this.loanNo = "";
        this.mobileNo = "";
        this.email = "";
        this.startDate = null;
        this.expiryDate = null;
        this.amount = "";
    }

}
