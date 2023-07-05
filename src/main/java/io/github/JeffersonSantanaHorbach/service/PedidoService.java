package io.github.JeffersonSantanaHorbach.service;

import io.github.JeffersonSantanaHorbach.api.dto.PedidoDTO;
import io.github.JeffersonSantanaHorbach.domain.entity.Pedido;
import io.github.JeffersonSantanaHorbach.domain.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
