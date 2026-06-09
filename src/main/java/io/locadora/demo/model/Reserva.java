package io.locadora.demo.model;

import io.locadora.demo.exception.ReservaInvalidaException;

public class Reserva {

  private Cliente cliente;
  private Carro carro;
  private int dias;

  public Reserva(Cliente cliente, Carro carro, int dias) {

    if(dias < 1){
      throw new ReservaInvalidaException("A reserva deve ser feita para pelo menos 1 dia.");
    }

    this.cliente = cliente;
    this.carro = carro;
    this.dias = dias;
  }

  public double calcularTotal(){
    return this.carro.calcularValorAluguel(this.dias);
  }
}
