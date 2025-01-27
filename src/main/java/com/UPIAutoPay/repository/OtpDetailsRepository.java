package com.UPIAutoPay.repository;

import com.UPIAutoPay.entity.OtpDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OtpDetailsRepository extends JpaRepository<OtpDetails,Long> {

    @Query("select COUNT(otp) from OtpDetails otp where otp.mobileNo=:mobileNo")
    int countMobileNo(String mobileNo);
    @Modifying
    @Transactional
    @Query("Delete from OtpDetails otp where otp.mobileNo=:mobileNo")
    void deletePrevOtp(String mobileNo);
    @Query("select otp from OtpDetails otp where otp.mobileNo=:mobileNo AND otp.otpCode=:otpCode")
    Optional<OtpDetails> validateOtp(String mobileNo, String otpCode);
}