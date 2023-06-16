package io.github.JeffersonSantanaHorbach.domain.repository;

import io.github.JeffersonSantanaHorbach.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
