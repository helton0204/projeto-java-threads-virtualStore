package br.com.helton.virtualstore.service;

import br.com.helton.virtualstore.dto.CadastroPedidoDTO;
import br.com.helton.virtualstore.dto.PedidoDTO;
import br.com.helton.virtualstore.exception.ValidacaoException;
import br.com.helton.virtualstore.model.ItemPedido;
import br.com.helton.virtualstore.model.Pedido;
import br.com.helton.virtualstore.model.Usuario;
import br.com.helton.virtualstore.repository.EstoqueRepository;
import br.com.helton.virtualstore.repository.PedidoRepository;
import br.com.helton.virtualstore.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public Pedido cadastrar(CadastroPedidoDTO dto, Usuario usuario) {
        var itens = new ArrayList<ItemPedido>();
        for (var itemDto : dto.itens()) {
            var estoque = estoqueRepository.findByProdutoId(itemDto.produtoId());
            if (estoque.getQuantidade() >= itemDto.quantidade()) {
                var produto = produtoRepository.findById(itemDto.produtoId()).get();
                if (!produto.getAtivo())
                    throw new ValidacaoException("Pedido contém produto excluído: " + produto.getId());
                var itemPedido = new ItemPedido(null, produto, itemDto.quantidade());
                itens.add(itemPedido);
                estoque.diminuir(itemDto.quantidade());
            } else {
                throw new ValidacaoException("Estoque indisponível para o produto: " + itemDto.produtoId());
            }
        }

        var pedido = new Pedido(itens, usuario);
        repository.save(pedido);

        return pedido;

    }
}