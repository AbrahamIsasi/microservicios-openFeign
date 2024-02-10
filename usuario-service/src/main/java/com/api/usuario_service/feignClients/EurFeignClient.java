package com.api.usuario_service.feignClients;

import com.api.usuario_service.modelos.Eur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account-eur-service",url = "http://localhost:8002")
@RequestMapping("/eur")
public interface EurFeignClient {

    @PostMapping()
    public Eur save(@RequestBody Eur eur);

    @GetMapping("/user/{userId}")
    public List<Eur> getEur(@PathVariable("userId") int userId);


}
