package io.github.JeffersonSantanaHorbach.domain.repository;

import io.github.JeffersonSantanaHorbach.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente,Integer> {

    @Query(value = "SELECT * FROM Cliente c WHERE c" +
            ".nome like '%:nome%'",nativeQuery = true)
    List<Cliente> encontrarProNome(@Param("nome") String nome);


//    @Query("DELETE FROM Cliente c WHERE c.nome = :nome")
//    @Modifying
//    void deleteByNome(String nome);

    boolean existsByNome (String nome);
}