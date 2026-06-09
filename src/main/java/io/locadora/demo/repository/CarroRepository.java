package io.locadora.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.locadora.demo.entity.CarroEntity;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
  List<CarroEntity> findByModelo(String modelo);
}