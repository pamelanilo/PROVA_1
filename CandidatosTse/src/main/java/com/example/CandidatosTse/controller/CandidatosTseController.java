package com.example.CandidatosTse.controller;

import model.Candidato;
import service.CandidatosTseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService service;

    public CandidatosTseController(CandidatosTseService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String partido,
            @RequestParam(required = false) String texto,
            Model model
    ) {
       
        List<Candidato> lista = service.filtrar(cargo, partido, texto);

        model.addAttribute("candidatos", lista);
        model.addAttribute("total", lista.size());
        
        model.addAttribute("cargos", service.listarCargos());
        model.addAttribute("partidos", service.listarPartidos());

        model.addAttribute("cargo", cargo != null ? cargo : "");
        model.addAttribute("partido", partido != null ? partido : "");
        model.addAttribute("texto", texto != null ? texto : "");

        return "index"; 
    }
}
