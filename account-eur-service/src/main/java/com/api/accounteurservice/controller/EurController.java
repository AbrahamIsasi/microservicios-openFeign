package com.api.accounteurservice.controller;

import com.api.accounteurservice.entity.Eur;
import com.api.accounteurservice.service.EurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eur")
public class EurController {

    @Autowired
    private EurService eurService;

    @GetMapping
    public ResponseEntity<List<Eur>> getAllEur(){
        List<Eur> eurs = eurService.getAll();
        if (eurs == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eurs);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Eur> getEurById (@PathVariable("id") int id){
        Eur eur = eurService.findById(id);
        if (eur == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eur);



    }

    @PostMapping
    public ResponseEntity<Eur> saveEur(@RequestBody Eur eur){
        Eur nuevoEur = eurService.save(eur);
        return ResponseEntity.ok(nuevoEur);

    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Eur>> listEurForUserId(@PathVariable("userId") int id){
        List<Eur> eurs = eurService.byUsuarioId(id);
        if (eurs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eurs);



    }






}
