package io.locadora.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ClienteTest {

  @Test
  void deveCriarClienteComNome(){
    var cliente = new Cliente("João");

    String nome = cliente.getNome();

    assertNotNull(nome);
    assertThat(nome).isEqualTo("João");
  }
}