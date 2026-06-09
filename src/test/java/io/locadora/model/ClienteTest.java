package io.locadora.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class ClienteTest {

  @Test
  @DisplayName("Deve criar um cliente com nome")
  void deveCriarClienteComNome(){
    var cliente = new Cliente("João");

    String nome = cliente.getNome();

    assertNotNull(nome);
    assertThat(nome).isEqualTo("João");
  }
}