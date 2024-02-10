package com.api.accounteurservice.service;

import com.api.accounteurservice.entity.Eur;
import com.api.accounteurservice.repository.EurRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EurService {

    @Autowired
    private EurRepository eurRepository;

    public List<Eur> getAll(){
        return eurRepository.findAll();
    }

    public Eur findById(int id){
        return eurRepository.findById(id).orElse(null);
    }

    public Eur save(Eur eur){
        Eur nuevoEur = eurRepository.save(eur);
        return nuevoEur;
    }

    public Eur delete(int id){
        Eur eurDelete = eurRepository.findById(id).orElse(null);
        if (eurDelete != null){
            eurRepository.delete(eurDelete);
        }
        return eurDelete;
    }

    public List<Eur> byUsuarioId(int userId){
        return eurRepository.findByUserId(userId);
    }













}
