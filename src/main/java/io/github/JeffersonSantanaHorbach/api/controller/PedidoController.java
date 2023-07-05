package io.github.JeffersonSantanaHorbach.api.controller;

import io.github.JeffersonSantanaHorbach.api.dto.AtualizacaoStatusPedidoDto;
import io.github.JeffersonSantanaHorbach.api.dto.InfoItemPedidoDTO;
import io.github.JeffersonSantanaHorbach.api.dto.InformacoesPedidoDTO;
import io.github.JeffersonSantanaHorbach.api.dto.PedidoDTO;
import io.github.JeffersonSantanaHorbach.domain.entity.ItemPedido;
import io.github.JeffersonSantanaHorbach.domain.entity.Pedido;
import io.github.JeffersonSantanaHorbach.domain.enums.StatusPedido;
import io.github.JeffersonSantanaHorbach.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody @Valid PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById( @PathVariable Integer id ){
        return service
                .obterPedidoCompleto(id)
                .map(this::converter)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void upDateStatus(@PathVariable Integer id,
                             @RequestBody AtualizacaoStatusPedidoDto dto){
        service.atualizaStatus(id, StatusPedido.valueOf(dto.getNovoStatus()));
    }


    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataDoPedido(pedido.getDataPedido()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InfoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InfoItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}