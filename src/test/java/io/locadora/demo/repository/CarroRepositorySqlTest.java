package io.locadora.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import io.locadora.demo.entity.CarroEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CarroRepositorySqlTest {
  @Autowired
  CarroRepository repository;

  @Test
  @Sql("/sql/popular-carros.sql")
  void deveBuscarPorModelo(){
    List<CarroEntity> carros = repository.findByModelo("SUV");
    
    assertEquals(1, carros.size());    
  }
}
