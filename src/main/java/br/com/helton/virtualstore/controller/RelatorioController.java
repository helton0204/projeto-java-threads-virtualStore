//package br.com.helton.virtualstore.controller;
//
//import br.com.helton.virtualstore.dto.RelatorioEstoqueDTO;
//import br.com.helton.virtualstore.dto.RelatorioFaturamentoDTO;
//import br.com.helton.virtualstore.service.RelatorioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.CompletableFuture;
//
//@RestController
//@RequestMapping("admin/relatorios")
//public class RelatorioController {
//    @Autowired
//    private RelatorioService service;
//
//    @GetMapping("estoque")
//    public ResponseEntity<CompletableFuture<RelatorioEstoqueDTO>> obterInfoEstoque(){
//        var relatorio = service.infoEstoque();
//        return ResponseEntity.ok(relatorio);
//    }
//
//    @GetMapping("faturamento")
//    public ResponseEntity<CompletableFuture<RelatorioFaturamentoDTO>> obterInfoFaturamento(){
//        var relatorio = service.faturamentoObtido();
//        return ResponseEntity.ok(relatorio);
//    }
//}