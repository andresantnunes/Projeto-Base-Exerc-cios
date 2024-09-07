package com.example.demo.controller;

import com.example.demo.database.entities.Estudante;
import com.example.demo.database.entities.Turma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// EX 07
@SpringBootTest
@AutoConfigureMockMvc
public class TurmaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    Turma turma;
    Estudante estudante;

    @BeforeEach
    public void setUp() {
        estudante = new Estudante();
        estudante.setId(1L);
        estudante.setNome("Jefferson");
        estudante.setMatricula("1234");

        turma = new Turma();
        turma.setNome("Estatistica");
        turma.setId(1L);

    }

    @Test
    public void adicionarEstudanteNaTurmaTest() throws Exception {
    }


    @Test
    void listarTurmas()  throws Exception{
        mockMvc.perform(get("/turmas")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void buscarTurmaPorId()  throws Exception{

        mockMvc.perform(post("/turmas")
                        .content("{\"nome\":\"nome\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());

        mockMvc.perform(get("/turmas/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("nome"));
    }

    @Test
    void cadastrarTurma()  throws Exception{
        mockMvc.perform(post("/turmas")
                        .content("{\"nome\":\"nome\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

    @Test
    void atualizarTurma()  throws Exception{
    }

    @Test
    void removerTurma()  throws Exception{
    }

    @Test
    void cadastrarEstudanteTurma()  throws Exception{
    }
}
