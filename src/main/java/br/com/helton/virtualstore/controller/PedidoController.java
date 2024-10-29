package br.com.helton.virtualstore.controller;

import br.com.helton.virtualstore.dto.CadastroPedidoDTO;
import br.com.helton.virtualstore.dto.DetalhamentoPedidoDTO;
import br.com.helton.virtualstore.dto.PedidoDTO;
import br.com.helton.virtualstore.email.EmailPedidoRealizado;
import br.com.helton.virtualstore.model.Pedido;
import br.com.helton.virtualstore.model.Usuario;
import br.com.helton.virtualstore.repository.PedidoRepository;
import br.com.helton.virtualstore.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private EmailPedidoRealizado email;

    @PostMapping
    public ResponseEntity<List<DetalhamentoPedidoDTO>> cadastrar(@Valid @RequestBody CadastroPedidoDTO dto, @AuthenticationPrincipal Usuario usuario) {
        Pedido pedido = pedidoService.cadastrar(dto, usuario);
        List<DetalhamentoPedidoDTO> detalhamentoPedido = pedidoRepository.findDetalhamentoPedidoById(pedido.getId());
        email.enviar(detalhamentoPedido, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamentoPedido);
    }
}