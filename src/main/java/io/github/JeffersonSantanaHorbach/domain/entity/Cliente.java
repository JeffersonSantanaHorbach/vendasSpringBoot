package io.github.JeffersonSantanaHorbach.domain.entity;

public class Cliente {

    private Integer id_cliente;
    private String nome;

    public Cliente() {
    }

    public Cliente(Integer id_cliente, String nome) {
        this.id_cliente = id_cliente;
        this.nome = nome;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
