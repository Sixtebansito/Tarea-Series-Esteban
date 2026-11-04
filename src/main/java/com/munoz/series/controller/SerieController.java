package com.munoz.series.controller;

import com.munoz.series.models.Serie;
import com.munoz.series.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/series")
public class SerieController {
    private SerieService serieService;

    public SerieController(SerieService serieService){
        this.serieService = serieService;
    }

    // VER TODOS:
    @GetMapping
    public List<Serie> leerTodos(){
        return serieService.traerTodos();
    }

    // VER UNICO:
    @GetMapping("/{id}")
    public ResponseEntity<Serie> leerSerie(@PathVariable Long id){
        return serieService.leerSerie(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GUARDAR SERIE:
    @PostMapping
    public ResponseEntity<Serie> guardar(@RequestBody Serie serie){
        Serie nuevaSerie = serieService.guardar(serie);
        return new ResponseEntity<>(nuevaSerie, HttpStatus.CREATED);
    }

    // EDITAR SERIE:
    @PutMapping("/{id}")
    public ResponseEntity<Serie> editar(@PathVariable Long id, @RequestBody Serie serie){
        return serieService.editar(id, serie)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR SERIE:
    @DeleteMapping("/{id}")
    public ResponseEntity<Serie> eliminar(@PathVariable Long id){
        if(serieService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
