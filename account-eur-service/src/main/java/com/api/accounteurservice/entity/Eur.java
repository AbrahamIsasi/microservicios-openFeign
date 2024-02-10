package com.api.accounteurservice.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Eur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double  quantity;
    private String bank;
    private int userId;


}
