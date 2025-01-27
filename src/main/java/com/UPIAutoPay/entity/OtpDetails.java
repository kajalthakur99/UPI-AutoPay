package com.UPIAutoPay.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_detail")
@Data
public class OtpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="otp_id")
    private Long otpId;
    @Column(name="mobile_no")
    private String mobileNo;
    @Column(name="otp_code")
    private String otpCode;
    @Column(name="expr_time")
    private LocalDateTime otpExprTime=LocalDateTime.now();
}
