package com.api.usuario_service.modelos;

import lombok.Data;

@Data
public class Eur {

    private double  quantity;
    private String bank;
    private int userId;

    public Eur() {
        super();
    }
}
