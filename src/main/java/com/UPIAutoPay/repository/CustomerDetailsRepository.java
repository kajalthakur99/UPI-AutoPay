package com.UPIAutoPay.repository;


import com.UPIAutoPay.entity.CustomerDetails;
import com.UPIAutoPay.entity.OtpDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<OtpDetails,Long> {

    @Query("select cd from CustomerDetails cd where cd.loanNo=:loanNo ")
    Optional<CustomerDetails> findByLoanNo(@Param("loanNo")String loanNo);
}
