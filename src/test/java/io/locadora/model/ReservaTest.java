package io.locadora.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import io.locadora.exception.ReservaInvalidaException;

import static org.assertj.core.api.Assertions.*;

class ReservaTest {

  Cliente cliente;
  Carro carro;

  @BeforeEach
  void setUp(){
    cliente = new Cliente("João");
    carro = new Carro("Fusca", 100.0);
  }

  @Test
  @DisplayName("Deve criar uma reserva")
  void deveCriarReserva(){
    var dias = 5;

    var reserva = new Reserva(cliente, carro, dias);

    assertThat(reserva).isNotNull();

    assertThatNoException().isThrownBy(() -> new Reserva(cliente, carro, 1));
  }

  @Test
  @DisplayName("Deve lançar exceção para reserva inválida")
  void deveLancarExcecaoParaReservaInvalida(){
    var dias = 0;

    assertThatThrownBy(() -> new Reserva(cliente, carro, dias)).isInstanceOf(ReservaInvalidaException.class).hasMessage("A reserva deve ser feita para pelo menos 1 dia.");
  }

  @Test
  @DisplayName("Deve calcular o valor total da reserva")
  void deveCalcularValorTotalDaReserva(){
    var reserva = new Reserva(cliente, carro, 3);

    assertThat(reserva.calcularTotal()).isEqualTo(300.0);
  }
}