package io.github.JeffersonSantanaHorbach.domain.repository;

import io.github.JeffersonSantanaHorbach.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
