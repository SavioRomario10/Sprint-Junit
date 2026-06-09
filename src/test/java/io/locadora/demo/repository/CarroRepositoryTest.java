package io.locadora.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.locadora.demo.entity.CarroEntity;
import io.locadora.demo.repository.CarroRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CarroRepositoryTest {

  @Autowired
  CarroRepository repository;

  @Test
  void deveSalvarCarro(){
    var entity = new CarroEntity("Teste", 100.0, 2007);
    repository.save(entity);

    assertNotNull(entity.getId());
  }
}
