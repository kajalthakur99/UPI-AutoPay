package com.UPIAutoPay.ServiceIMPL;

import com.UPIAutoPay.Utility.OtpUtility;
import com.UPIAutoPay.entity.CustomerDetails;
import com.UPIAutoPay.entity.OtpDetails;
import com.UPIAutoPay.model.CommonResponse;
import com.UPIAutoPay.model.OtpValidationRequest;
import com.UPIAutoPay.model.ResetPasswordResponse;
import com.UPIAutoPay.repository.CustomerDetailsRepository;
import com.UPIAutoPay.repository.OtpDetailsRepository;
import com.UPIAutoPay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerserviceIMPL implements CustomerService {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;
    @Autowired
    private OtpDetailsRepository otpDetailsRepository;
    @Autowired
    private OtpUtility otpUtility;

    public ResponseEntity<?> resetPassword(String applicationNo) {

        CommonResponse commonResponse = new CommonResponse();
        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        try {
            Optional<CustomerDetails> customerDetails = customerDetailsRepository.findByLoanNo(applicationNo);
            if (customerDetails.isPresent()) {
                CustomerDetails userDetailData = customerDetails.get();
                int otpCode = otpUtility.generateOtp(userDetailData);
                if (otpCode > 0) {
                    OtpDetails otpManage = new OtpDetails();
                    otpManage.setOtpCode(String.valueOf(otpCode));
                    otpManage.setOtpExprTime(LocalDateTime.now());
                    otpManage.setMobileNo(userDetailData.getMobileNo());
                    otpDetailsRepository.save(otpManage);

                    resetPasswordResponse.setOtpId(otpManage.getOtpId());
                    resetPasswordResponse.setOtpCode(String.valueOf(otpCode));
                    resetPasswordResponse.setMobileNo(otpManage.getMobileNo());

//                    if (otpUtility.sendOtp(userDetailData.getMobileNo(), otpCode)){
//                        commonResponse.setCode("0000");
//                        commonResponse.setMsg("otp send success");
//                    }
                    resetPasswordResponse.setCommonResponse(commonResponse);
                    return ResponseEntity.ok(resetPasswordResponse);
                } else {
                    commonResponse.setMsg("Otp did not generated, please try again");
                    commonResponse.setCode("1111");
                    return ResponseEntity.ok(commonResponse);
                }
            } else {
                commonResponse.setMsg("user not found");
                commonResponse.setCode("1111");
                return ResponseEntity.ok(commonResponse);
            }
        } catch (Exception e) {
            commonResponse.setMsg("Technical error.");
            commonResponse.setCode("1111");
            return ResponseEntity.ok(commonResponse);
        }
    }

    public ResponseEntity<?> matchOtp(OtpValidationRequest otpValidationRequest) {

        CommonResponse commonResponse = new CommonResponse();
        try {
            Optional<OtpDetails> otpManages = otpDetailsRepository.validateOtp(otpValidationRequest.getMobileNo(), otpValidationRequest.getOtpCode());
            OtpDetails otpManage = otpManages.get();
            Duration duration = Duration.between(otpManage.getOtpExprTime(), LocalDateTime.now());
            long betweenTime = duration.toMinutes();
            if (betweenTime <= 8) {
                commonResponse.setMsg(" Otp match Success");
                commonResponse.setCode("0000");
                return ResponseEntity.ok(commonResponse);
            } else {
                commonResponse.setMsg("Your Otp is expired");
                commonResponse.setCode("1111");
                return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(commonResponse);
            }
        } catch (Exception e) {
            commonResponse.setMsg("Otp or emailId is not correct");
            commonResponse.setCode("1111");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
        }
    }
}
