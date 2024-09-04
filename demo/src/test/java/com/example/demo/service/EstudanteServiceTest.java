package com.example.demo.service;

import com.example.demo.database.entities.Estudante;
import com.example.demo.database.repositories.EstudanteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)// adiciona o mockito ao JUnit nessa classe de teste
class EstudanteServiceTest {

    @Mock // mock de uma classe
    EstudanteRepository estudanteRepository;

    @InjectMocks // classe que vai receber o objeto mockado
    EstudanteService estudanteService;

    @Test
    void cadastrarEstudante() {
        // given
        Estudante estudante = new Estudante(1L,
                "Joaquino",
                "11.22.33",
                Collections.emptyList());
        when(estudanteRepository.save(any(Estudante.class)))
                .thenReturn(estudante);

        // when
        Estudante resultado = estudanteService.cadastrarEstudante("Joaquino",
                "11.22.33");

        // then
        verify(estudanteRepository).save(any(Estudante.class));
        assertEquals(estudante.getNome(), resultado.getNome());
    }

    @Test
    void listarEstudantes() {
        // given
        Estudante estudante = new Estudante(1L,
                "Joaquino",
                "11.22.33",
                Collections.emptyList());
        when(estudanteRepository.findAll())
                .thenReturn(List.of(estudante));

        // when
        List<Estudante> resultado = estudanteService.listarEstudantes();

        // then
        verify(estudanteRepository).findAll();
        assertEquals(estudante.getNome(), resultado.get(0).getNome());
    }

    @Test
    void buscarEstudantePorId() {
        // given
        Estudante estudante = new Estudante(1L,
                "Joaquino",
                "11.22.33",
                Collections.emptyList());

        when(estudanteRepository.findById(anyLong()))
                .thenReturn(Optional.of(estudante));

        // when
        Estudante resultado = estudanteService.buscarEstudantePorId(1L);

        // then
        verify(estudanteRepository).findById(anyLong());
        assertEquals(estudante.getNome(), resultado.getNome());
    }

    @Test
    void atualizarEstudante() {
        // given
        Estudante estudante = new Estudante(1L,
                "Joaquino",
                "11.22.33",
                Collections.emptyList());

        when(estudanteRepository.findById(anyLong()))
                .thenReturn(Optional.of(estudante));
        when(estudanteRepository.save(any(Estudante.class)))
                .thenReturn(estudante);

        // when
        Estudante resultado = estudanteService.atualizarEstudante(1L,
                "Mourrice",
                "32.12.23");

        // then
        verify(estudanteRepository).findById(anyLong());
        verify(estudanteRepository).save(any(Estudante.class));
        assertEquals("Mourrice", resultado.getNome());
    }

    @Test
    void removerEstudante() {
        // when
        estudanteService.removerEstudante(1L);

        // then
        verify(estudanteRepository).deleteById(anyLong());
    }
}