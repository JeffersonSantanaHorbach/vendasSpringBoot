package io.github.JeffersonSantanaHorbach.domain.repository;

import io.github.JeffersonSantanaHorbach.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
