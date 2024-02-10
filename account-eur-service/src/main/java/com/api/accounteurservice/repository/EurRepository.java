package com.api.accounteurservice.repository;

import com.api.accounteurservice.entity.Eur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EurRepository extends JpaRepository<Eur, Integer> {

    List<Eur> findByUserId(int userId);
}
