package com.munoz.series.service;

import com.munoz.series.models.Serie;
import com.munoz.series.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {
    private SerieRepository serieRepository;

    public SerieService (SerieRepository serieRepository){
        this.serieRepository = serieRepository;
    }

    // TRAER TODOS:
    public List<Serie> traerTodos(){
        return serieRepository.findAll();
    }

    // TRAER UNO SOLO:
    public Optional<Serie> leerSerie(Long id){
        return serieRepository.findById(id);
    }

    // GUARDAR SERIE:
    public Serie guardar(Serie serie){
        return serieRepository.save(serie);
    }

    // EDITAR SERIE:
    public Optional<Serie> editar(Long id, Serie serieActualizado){
        return serieRepository.findById(id)
                .map(serieExiste -> {
                    serieExiste.setTitulo(serieActualizado.getTitulo());
                    serieExiste.setPlataforma(serieActualizado.getPlataforma());
                    serieExiste.setCalificacion(serieActualizado.getCalificacion());

                    return serieRepository.save(serieExiste);
                });
    }

    // ELIMINAR SERIE:
    public boolean eliminar(Long id){
        if (serieRepository.existsById(id)) {
            serieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
