package com.example.demo.services;

import com.example.demo.models.Colaborador;
import com.example.demo.repos.RecoExtraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecoExtraService {
    @Autowired
    RecoExtraRepo recoExtraRepo;

    public ArrayList<Colaborador> getColaboradores() {
        return (ArrayList<Colaborador>) recoExtraRepo.findAll();
    }

    public Colaborador saveColaborador(Colaborador colaborador) {
        return recoExtraRepo.save(colaborador);
    }
}
