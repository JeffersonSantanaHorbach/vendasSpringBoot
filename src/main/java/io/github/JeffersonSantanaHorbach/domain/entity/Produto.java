package io.github.JeffersonSantanaHorbach.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name ="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idProduto;

    @Column(name= "descricao")
    private String descricao;

    @Column(name = "preco_unitarioi")
    private BigDecimal preco;


    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
