package com.UPIAutoPay.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "req_structure_details")
@Data
public class ReqStrDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "req_id")
    private Long reqId;

}
