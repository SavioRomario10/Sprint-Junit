package io.locadora.demo.controller;

import io.locadora.demo.service.CarroService;
import io.locadora.demo.entity.CarroEntity;
import io.locadora.demo.exception.EntityNotFoundException;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/carros")
public class CarroController {

  private final CarroService service;

  public CarroController(CarroService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro) {
    try {
      var carroSalvo = service.save(carro);
      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(carroSalvo);
    } catch (IllegalArgumentException e) {
      return ResponseEntity
              .status(HttpStatusCode.valueOf(422))
              .body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> detalhesCarro(@PathVariable("id")Long id){
    try{
      var CarroEncontrado = service.findById(id);
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(CarroEncontrado);
    }
    catch(EntityNotFoundException e){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<CarroEntity>> listarCarros() {
    return ResponseEntity.ok(service.findAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> atualizar(
    @PathVariable("id") Long id, @RequestBody CarroEntity carro) {
    try{
      service.atualizar(id, carro);
      return ResponseEntity.noContent().build();
    }
    catch(EntityNotFoundException e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> atualizar(@PathVariable("id") Long id) {
    try{
      service.deletar(id);
      return ResponseEntity.noContent().build();
    }
    catch(EntityNotFoundException e){
      return ResponseEntity.notFound().build();
    }
  }
}