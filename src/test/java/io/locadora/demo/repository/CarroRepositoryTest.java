package io.locadora.demo.repository;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.locadora.demo.entity.CarroEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CarroRepositoryTest {

  @Autowired
  CarroRepository repository;

  CarroEntity entity;

  @BeforeEach
  void setUp(){
    entity = new CarroEntity("Teste", 100.0, 2007);
  }

  @Test
  void deveSalvarCarro(){
    repository.save(entity);

    assertNotNull(entity.getId());
  }

  @Test
  void deveBuscarCarroPorId(){
    CarroEntity carroSalvo = repository.save(entity);

    Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

    assertThat(carroEncontrado.isPresent());
    assertThat(carroEncontrado.get().getModelo()).isEqualTo("Teste");
  }

  @Test
  void deveAtualizarCarro(){
    var carroSalvo = repository.save(entity);
    carroSalvo.setModelo("Teste Atualizado");

    var carroAtualizado = repository.save(carroSalvo);

    assertThat(carroAtualizado.getModelo()).isEqualTo("Teste Atualizado");
  }

  @Test
  void deveDeletarCarro(){
    var carroSalvo = repository.save(entity);

    repository.deleteById(carroSalvo.getId());

    Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

    assertThat(carroEncontrado.isEmpty());
  }
}