package io.locadora.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.locadora.demo.entity.CarroEntity;
import io.locadora.demo.exception.EntityNotFoundException;
import io.locadora.demo.repository.CarroRepository;

@Service
public class CarroService {

  private final CarroRepository repository;

  public CarroService(CarroRepository repository) {
    this.repository = repository;
  }

  public CarroEntity save(CarroEntity carro) {

    if(carro.getValorDiaria() <= 0){
      throw new IllegalArgumentException("Valor da diária deve ser maior que zero.");
    }
    return repository.save(carro);
  }

  public CarroEntity atualizar(Long id, CarroEntity carro){
    var carroExistente = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado."));

    carroExistente.setModelo(carro.getModelo());
    carroExistente.setAno(carro.getAno());
    carroExistente.setValorDiaria(carro.getValorDiaria());

    return repository.save(carroExistente);
  }

  public void deletar(Long id){
    var carroExistente = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado."));

    repository.delete(carroExistente);
  }

  public CarroEntity findById(Long id){
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado."));
  }

  public List<CarroEntity> findAll(){
    return repository.findAll();
  }
}