package io.locadora.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class CarroTest {

  @Test
  @DisplayName("Deve calcular o valor do aluguel correto")
  void deveCalcularValorAluguel(){
    
    Carro carro = new Carro("Sedan", 100.0);
    double total = carro.calcularValorAluguel(3);

    assertEquals(300.0, total);
  }

  @Test
  @DisplayName("Deve calcular o valor do aluguel com desconto")
  void deveCalcularValorAluguelComDesconto(){
    
    Carro carro = new Carro("Sedan", 100.0);
    int quantDias = 5;


    double total = carro.calcularValorAluguel(quantDias);

    assertEquals(450.0, total);
  }
}