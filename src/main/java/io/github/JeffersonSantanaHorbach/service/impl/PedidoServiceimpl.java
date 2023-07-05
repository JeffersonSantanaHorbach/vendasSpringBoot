package io.github.JeffersonSantanaHorbach.service.impl;

import io.github.JeffersonSantanaHorbach.api.dto.ItemPedidoDTO;
import io.github.JeffersonSantanaHorbach.api.dto.PedidoDTO;
import io.github.JeffersonSantanaHorbach.domain.entity.Cliente;
import io.github.JeffersonSantanaHorbach.domain.entity.ItemPedido;
import io.github.JeffersonSantanaHorbach.domain.entity.Pedido;
import io.github.JeffersonSantanaHorbach.domain.entity.Produto;
import io.github.JeffersonSantanaHorbach.domain.enums.StatusPedido;
import io.github.JeffersonSantanaHorbach.domain.repository.Clientes;
import io.github.JeffersonSantanaHorbach.domain.repository.ItensPedido;
import io.github.JeffersonSantanaHorbach.domain.repository.Pedidos;
import io.github.JeffersonSantanaHorbach.domain.repository.Produtos;
import io.github.JeffersonSantanaHorbach.exception.BusinessRuleException;
import io.github.JeffersonSantanaHorbach.exception.PedidoNaoEncontradoException;
import io.github.JeffersonSantanaHorbach.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceimpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new BusinessRuleException("Código de Cliente inválido "));
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido =  converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    //TODO Escrever alguma coisa
    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if (items.isEmpty()){
            throw new BusinessRuleException("Não é possível realizar um pedido sem items.");
        }
        return items
                .stream()
                .map(dto ->{
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new BusinessRuleException(
                                            "Código de produto inválido: " + idProduto)
                            );
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
