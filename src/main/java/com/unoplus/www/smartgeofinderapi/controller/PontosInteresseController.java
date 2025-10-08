package com.unoplus.www.smartgeofinderapi.controller;

import com.unoplus.www.smartgeofinderapi.domain.dto.PontosInteresseDTO;
import com.unoplus.www.smartgeofinderapi.domain.entity.PontosInteresse;
import com.unoplus.www.smartgeofinderapi.repository.PontosInteresseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PontosInteresseController {
    private final PontosInteresseRepository repository;

    public PontosInteresseController(PontosInteresseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/pontos-de-interesse")
    public ResponseEntity<Void> criarPontosDeInteresse(@RequestBody PontosInteresseDTO body) {
        repository.save(new PontosInteresse(body.nome(), body.x(), body.y()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar/pontos-de-interesse")
    public ResponseEntity<List<PontosInteresse>> listarPontosDeInteresse() {
        List<PontosInteresse> listaPontosInteresse = repository.findAll();
        return ResponseEntity.ok(listaPontosInteresse);
    }

    @GetMapping("/listar/pontos-proximos")
    public ResponseEntity<List<PontosInteresse>> listarPontosProximos(
            @RequestParam("x") Long x,
            @RequestParam("y") Long y,
            @RequestParam("dMax") Long dMax
    ) {
        Long xMin = x - dMax;
        Long xMax = x + dMax;
        Long yMin = y - dMax;
        Long yMax = y + dMax;

        List<PontosInteresse> pontosInteresseFiltrados = repository.encontrarPontosInteresseProximo(xMin, xMax, yMin, yMax)
                .stream()
                .filter(p -> distanciaEuclidiana(x, y, p.getX(), p.getY()) <= dMax)
                .toList();
        return ResponseEntity.ok(pontosInteresseFiltrados);
    }

    private double distanciaEuclidiana(Long x1, Long y1, Long x2, Long y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }
}
