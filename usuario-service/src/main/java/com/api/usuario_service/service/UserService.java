package com.api.usuario_service.service;

import com.api.usuario_service.entitys.User;
import com.api.usuario_service.feignClients.EurFeignClient;
import com.api.usuario_service.modelos.Eur;
import com.api.usuario_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EurFeignClient eurFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        User nuevoUser = userRepository.save(user);
        return nuevoUser;
    }

    public User deleteUser(int id) {
        User userToDelete = userRepository.findById(id).orElse(null);
        if (userToDelete != null) {
            userRepository.delete(userToDelete);
        }
        return userToDelete;
    }

    public List<Eur> getEurs(int userId) {
        List<Eur> eurs = eurFeignClient.getEur(userId);
        return eurs;
    }

    public Eur saveEur(int userId, Eur eur) {
        eur.setUserId(userId);
        Eur nuevoEur = eurFeignClient.save(eur);
        return nuevoEur;
    }

    public Map<String, Object> getUserandMoney(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            result.put("Mensaje", "user doesn't exist");
            return result;
        }
        result.put("User", user);

        List<Eur> eurs = eurFeignClient.getEur(userId);
        if (eurs == null) {
            result.put("Eurs", "wrong obtain list of eurs to user");
        } else if (eurs.isEmpty()) {
            result.put("Eurs", "user doesn't exist");
        } else {
            result.put("Eurs", eurs);

        }
        return result;
    }
}

