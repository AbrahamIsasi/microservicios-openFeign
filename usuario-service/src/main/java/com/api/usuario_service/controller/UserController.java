package com.api.usuario_service.controller;

import com.api.usuario_service.entitys.User;
import com.api.usuario_service.modelos.Eur;
import com.api.usuario_service.repository.UserRepository;
import com.api.usuario_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listUser(){
    List<User> users = userService.getAll();
    if (users.isEmpty()){
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<User> saveUsers(@RequestBody User user){
        User nuevoUser = userService.saveUser(user);
        return ResponseEntity.ok(nuevoUser);
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User update) {
        // Buscar al usuario por su ID
        User existingUser = userService.getUserById(id);
        // Verificar si el usuario no existe
        if (existingUser == null) {
            return ResponseEntity.notFound().build(); // Devolver respuesta 404 si el usuario no existe
        }
        // Actualizar los campos del usuario con los valores proporcionados en update
        existingUser.setName(update.getName());
        existingUser.setEmail(update.getEmail());
        existingUser.setCountry(update.getCountry());
        // Puedes actualizar otros atributos según sea necesario

        // Guardar el usuario actualizado en la base de datos
        userService.saveUser(existingUser);

        // Devolver una respuesta con el usuario actualizado
        return ResponseEntity.ok(existingUser);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        User deletedUser = userService.deleteUser(id);
        if (deletedUser != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/eur/{userId}")
    public ResponseEntity<List<Eur>> listEur(@PathVariable("userId") int id){
        User user = userService.getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();

        }
        List<Eur> eur = userService.getEurs(id);
        return ResponseEntity.ok(eur);

    }

    @PostMapping("/eur/{userId}")
    public ResponseEntity<Eur> saveEur(@PathVariable("userId") int userId, @RequestBody Eur eur){
        Eur nuevoEur = userService.saveEur(userId, eur);
        return ResponseEntity.ok(nuevoEur);
    }


    @GetMapping("/todos/{userId}")
        public ResponseEntity<Map<String, Object>> listeverythingcash(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserandMoney(userId);
        return ResponseEntity.ok(result);
    }























}
