package io.locadora.demo.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import io.locadora.demo.entity.CarroEntity;
import io.locadora.demo.repository.CarroRepository;
import io.locadora.demo.exception.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarroServiceTest {

  @InjectMocks
  CarroService service;

  @Mock
  CarroRepository repository;

  @Test
  void deveSalvarCarro() {

    CarroEntity carro = new CarroEntity("Modelo X", 100.0, 2020);

    when(repository.save(any())).thenReturn(carro);

    var carroSalvo = service.save(carro);

    assertThat(carroSalvo.getModelo()).isEqualTo("Modelo X");
    verify(repository).save(any());
  }

  @Test
  void deveDarErroAoSalvarComNegativo(){
    CarroEntity carro = new CarroEntity("Modelo X", 0, 2020);

    var erro = catchThrowable(() -> service.save(carro));

    assertThat(erro).isInstanceOf(IllegalArgumentException.class);

    verify(repository, never()).save(any());
  }

  @Test
  void deveAtualizarUmCarro(){
    var carroExist = new CarroEntity("Modelo X", 100.0, 2020);
    
    when(repository.findById(1L)).thenReturn(Optional.of(carroExist));

    var carro = new CarroEntity("Modelo Y", 150.0, 2021);
    carro.setId(1L);

    when(repository.save(any())).thenReturn(carro);

    var carroAtualizado = service.atualizar(1L, carro);

    assertThat(carroAtualizado.getModelo()).isEqualTo("Modelo Y");
    assertThat(carroAtualizado.getValorDiaria()).isEqualTo(150.0);
    assertThat(carroAtualizado.getAno()).isEqualTo(2021);
  }

  @Test
  void deveDarErroAoAtualizar(){
    var carro = new CarroEntity("Modelo Y", 150.0, 2021);
    carro.setId(1L);

    when(repository.findById(any())).thenReturn(Optional.empty());

    var erro = catchThrowable(() -> service.atualizar(1L, carro));

    assertThat(erro).isInstanceOf(EntityNotFoundException.class);
    verify(repository, never()).save(any());
  }

  @Test
  void deveDeletarUmCarro(){
    var carro = new CarroEntity("Modelo Y", 150.0, 2021);
    carro.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(carro));

    service.deletar(1L);

    verify(repository, times(1)).delete(any(CarroEntity.class));
  }
  @Test
  void deveDarErroDeletarUmCarroInexistente(){
    var carro = new CarroEntity("Modelo Y", 150.0, 2021);
    carro.setId(1L);

    when(repository.findById(any())).thenReturn(Optional.empty());

    var erro = catchThrowable(() -> service.deletar(1L));

    assertThat(erro).isInstanceOf(EntityNotFoundException.class);

    verify(repository, never()).delete(any());
  }

  @Test
  void deveBuscarOsCarros(){
    var carro = new CarroEntity("Modelo Y", 150.0, 2021);
    carro.setId(1L);

    when(repository.findById(any())).thenReturn(Optional.of(carro));

    var carroEncontrado = service.findById(1L);

    assertThat(carroEncontrado.getModelo()).isEqualTo("Modelo Y");
  }

  @Test
  void deveBuscarTodosCarros(){
    var carro1 = new CarroEntity(1l, "Test 1", 100, 2021);
    var carro2 = new CarroEntity(2l, "Test 2", 150, 2022);

    var lista = List.of(carro1, carro2);

    when(repository.findAll()).thenReturn(lista);

    var carrosEncontrados = service.findAll();

    assertThat(carrosEncontrados.size()).isEqualTo(2);
    verify(repository, times(1)).findAll();
    verifyNoMoreInteractions(repository);
  }
}