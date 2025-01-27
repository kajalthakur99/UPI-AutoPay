package com.UPIAutoPay.controller;


import com.UPIAutoPay.model.CommonResponse;
import com.UPIAutoPay.model.OtpValidationRequest;
import com.UPIAutoPay.service.CustomerService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/autoPay")
@CrossOrigin
public class OtpController {

    @Autowired
    private CustomerService service;

    @PostMapping("/generate-otp")
    public ResponseEntity<?> resetUserPassword(@RequestParam(name = "applicationNo") String applicationNo) throws IOException {
        return ResponseEntity.ok(service.resetPassword(applicationNo).getBody());
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> otpValidation(@RequestBody OtpValidationRequest otpValidationRequest){
        CommonResponse commonResponse = new CommonResponse();
        if (otpValidationRequest.getOtpCode() != null && otpValidationRequest.getMobileNo() != null){
            return ResponseEntity.ok(service.matchOtp(otpValidationRequest));
        }else{
            commonResponse.setCode("1111");
            commonResponse.setMsg("Required field ");
            return ResponseEntity.ok(commonResponse);
        }
    }
}
