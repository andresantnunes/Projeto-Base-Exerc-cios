package com.example.demo.controller;

import com.example.demo.service.EstudanteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EstudanteControllerTest {

    @Autowired
    EstudanteController estudanteController;

    @MockBean
    private EstudanteService estudanteService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void listarEstudantes() throws Exception {

        when(estudanteService.listarEstudantes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/estudantes"))
                .andExpect(status().isOk());

    }

    @Test
    void buscarEstudantePorId() {
    }

    @Test
    void cadastrarEstudante() {
    }

    @Test
    void atualizarEstudante() {
    }

    @Test
    void removerEstudante() {
    }
}