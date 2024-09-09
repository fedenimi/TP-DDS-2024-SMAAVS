package com.example.demo.repos;

import com.example.demo.models.Colaborador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface RecoExtraRepo extends CrudRepository<Colaborador, Long> {

}

