package io.locadora.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import io.locadora.demo.entity.CarroEntity;
import io.locadora.demo.exception.EntityNotFoundException;
import io.locadora.demo.service.CarroService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

  @Autowired
  MockMvc mvc;

  @MockitoBean
  CarroService service;

  @Test
  void deveSalvarUmCarro() throws Exception {
    CarroEntity carro = new CarroEntity(1L, "Modelo X", 100.0, 2020);

    when(service.save(any())).thenReturn(carro);

    String json= """
        {
          "modelo": "Modelo X",
          "valorDiaria": 100.0,
          "ano": 2020
        }
        """;

    ResultActions result =mvc.perform(
          post("/carros")
            .contentType("application/json")
            .content(json)
    );

    result
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").value(1L));
  }

  @Test
  void deveObterDetalhesCarro() throws Exception {
    when(service.findById(any())).thenReturn(
      new CarroEntity(1L, "Modelo X", 100.0, 2020)
    );

    mvc.perform(
      get("/carros/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
  }

  @Test
  void deveRetornarErroAoBuscarCarro() throws Exception {
    when(service.findById(any())).thenThrow(
      EntityNotFoundException.class);

    mvc.perform(
      get("/carros/1"))
        .andExpect(status().isNotFound());
  }

  @Test
  void deveListarCarros() throws Exception {
    var listagem = List.of(
      new CarroEntity(1L, "Modelo X", 100.0, 2020),
      new CarroEntity(2L, "Modelo Y", 150.0, 2021)
    );

    when(service.findAll()).thenReturn(listagem);

    mvc.perform(
      get("/carros"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1L))
        .andExpect(jsonPath("$[1].id").value(2L));
  }

  @Test
  void deveAtualizarUmCarro() throws Exception {

    when(service.atualizar(any(), any())).thenReturn(
      new CarroEntity(1L, "Modelo Y", 150.0, 2021)
    );

    String json= """
    {
      "modelo": "Modelo Y",
      "valorDiaria": 100.0,
      "ano": 2021
    }
    """;

    mvc.perform(
      put("/carros/1")
        .contentType("application/json")
        .content(json))
          .andExpect(status().isNoContent());
  }

  @Test
  void deveDarErroAoAtualizar() throws Exception {
    when(service.atualizar(any(), any())).thenThrow(
      EntityNotFoundException.class);

    String json= """
    {
      "modelo": "Modelo Y",
      "valorDiaria": 100.0,
      "ano": 2021
    }
    """;
    
    mvc.perform(
      put("/carros/1")
        .contentType("application/json")
        .content(json))
        .andExpect(status().isNotFound());
  }

  @Test
  void deveDeletarUmCarro() throws Exception {
    doNothing().when(service).deletar(any());

    mvc.perform(
      delete("/carros/1"))
        .andExpect(status().isNoContent());
  }

  @Test
  void deveDarErroAoDeletar() throws Exception {
    doThrow(EntityNotFoundException.class).when(service).deletar(any());

    mvc.perform(
      delete("/carros/1"))
        .andExpect(status().isNotFound());
  }
}