package io.github.JeffersonSantanaHorbach.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table( name = "cliente" )
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo nome obrigatório!")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo cpf obrigatório!")
    @CPF(message = "Informe um cpf válido")
    private String cpf;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @JsonIgnore
    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;

}