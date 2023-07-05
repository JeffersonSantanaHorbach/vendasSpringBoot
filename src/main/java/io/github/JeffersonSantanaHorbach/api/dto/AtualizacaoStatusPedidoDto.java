package io.github.JeffersonSantanaHorbach.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AtualizacaoStatusPedidoDto {
    private String novoStatus;
}
