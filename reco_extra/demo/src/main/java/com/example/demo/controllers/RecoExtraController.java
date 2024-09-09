package com.example.demo.controllers;

import com.example.demo.models.Colaborador;
import com.example.demo.services.RecoExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/recoextra")
public class RecoExtraController {
    @Autowired
    RecoExtraService recoExtraService;

    @GetMapping()
    public List<Long> getColaboradores(
            @RequestParam(required = false) Integer minDonaciones,
            @RequestParam(required = false) Integer minPuntos,
            @RequestParam(required = false) Integer maxColaboradores) {
        if (minDonaciones == null) minDonaciones = 0;
        if (minPuntos == null) minPuntos = 0;
        if (maxColaboradores == null) maxColaboradores = 10;
        Integer finalMinDonaciones = minDonaciones;
        Integer finalMinPuntos = minPuntos;
        Integer finalMaxColaboradores = maxColaboradores;
        List<Colaborador> colaboradores = StreamSupport.stream(recoExtraService.getColaboradores().spliterator(), false)
                .filter(colaborador ->
                        colaborador.hizoDonacionesEnLosUltimosDias(30, finalMinDonaciones) &&
                        colaborador.tienePuntos(finalMinPuntos))
                .limit(finalMaxColaboradores)
                .collect(Collectors.toList());
        return colaboradores.stream().map(Colaborador::getId).collect(Collectors.toList());
    }
}

