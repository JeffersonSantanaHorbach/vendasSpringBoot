package io.github.JeffersonSantanaHorbach.service.impl;

import io.github.JeffersonSantanaHorbach.domain.repository.Pedidos;
import io.github.JeffersonSantanaHorbach.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceimpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceimpl(Pedidos repository) {
        this.repository = repository;
    }
}
