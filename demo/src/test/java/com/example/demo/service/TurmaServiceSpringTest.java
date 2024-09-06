package com.example.demo.service;

import com.example.demo.database.entities.Estudante;
import com.example.demo.database.entities.Turma;
import com.example.demo.database.repositories.TurmaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


// EX 03
@SpringBootTest
class TurmaServiceSpringTest {

    @MockBean // mock de uma classe
    TurmaRepository turmaRepository;

    @Autowired // classe que vai receber o objeto mockado
    TurmaService turmaService;

    Turma turma;

    @BeforeEach
    public void setUp() {
        Estudante estudante = new Estudante(1L,
                "Joaquino",
                "11.22.33",
                Collections.emptyList());
        turma = new Turma(1L, "Jardim 1", List.of(estudante));
    }

    @Test
    void cadastrarTurma() {
        // given
        when(turmaRepository.save(any()))
                .thenReturn(turma);

        // when
        Turma resultado = turmaService.cadastrarTurma("Jardim 1");

        // then
        verify(turmaRepository).save(any());
        assertEquals(turma.getNome(), resultado.getNome());
    }

    @Test
    void listarTurmas() {
        // given
        when(turmaRepository.findAll())
                .thenReturn(List.of(turma));

        // when
        var resultado = turmaService.listarTurmas();

        // then
        verify(turmaRepository).findAll();
        assertEquals(turma.getNome(), resultado.get(0).getNome());
    }

    @Test
    void buscarTurmaPorId() {
        // given
        when(turmaRepository.findById(anyLong()))
                .thenReturn(Optional.of(turma));

        // when
        var resultado = turmaService.buscarTurmaPorId(1L);

        // then
        verify(turmaRepository).findById(anyLong());
        assertEquals(turma.getNome(), resultado.getNome());
    }

    @Test
    void atualizarEstudante() {
        // given
        when(turmaRepository.findById(anyLong()))
                .thenReturn(Optional.of(turma));

        turma.setNome("nova turma");
        when(turmaRepository.save(any()))
                .thenReturn(turma);
        // when
        var resultado = turmaService.atualizarTurma(1L,"nova turma");

        // then
        verify(turmaRepository).findById(anyLong());
        verify(turmaRepository).save(any());
        assertEquals("nova turma", resultado.getNome());
    }

    @Test
    void removerEstudante() {
        // when
        turmaService.removerTurma(1L);

        // then
        verify(turmaRepository).deleteById(anyLong());
    }
}