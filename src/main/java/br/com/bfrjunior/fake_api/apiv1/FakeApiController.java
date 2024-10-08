package br.com.bfrjunior.fake_api.apiv1;

import br.com.bfrjunior.fake_api.apiv1.dto.ProductsDTO;
import br.com.bfrjunior.fake_api.business.service.FakeApiService;
import br.com.bfrjunior.fake_api.business.service.ProdutoService;
import br.com.bfrjunior.fake_api.infrastructure.entities.ProdutoEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "fake-api")
@Slf4j
public class FakeApiController {

    private final FakeApiService service;
    private final ProdutoService produtoService;

    @Operation(summary = "Busca produtos da API e salva", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os produtos"),
    })
    @PostMapping("/api")
    //@Scheduled(fixedDelay = 1000*60)
    public ResponseEntity<List<ProductsDTO>> salvaProdutosApi(){
        return ResponseEntity.ok(service.buscaProdutos());

    }


    @Operation(summary = "Salva novos produtos", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os produtos"),
    })
    @PostMapping("/")
    public ResponseEntity<ProductsDTO> salvaProdutosApi(@RequestBody ProductsDTO dto){

        return ResponseEntity.ok(produtoService.salvaProdutoDTO(dto));
    }

    @Operation(summary = "Atualiza produto", method ="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar os produtos"),
    })
    @PutMapping("/")
    public ResponseEntity<ProductsDTO> updateProdutos(@RequestParam("id") String id,@RequestBody ProductsDTO dto){

        return ResponseEntity.ok(produtoService.updateProduto(id,dto));
    }

    @Operation(summary = "Deleta produtos", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar os produtos"),
    })
    @DeleteMapping("/")
    public ResponseEntity<Void> deletaProduto(@RequestParam("nome") String nome){
        produtoService.deletaProduto(nome);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Busca todos os produtos", method ="GET   ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar os produtos"),
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductsDTO>> buscaTodosProdutos(){

        return ResponseEntity.ok(produtoService.buscaTodosProdutos());
    }

    @Operation(summary = "Busca produtos por nome", method ="GET   ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar os produtos"),
    })
    @GetMapping("/{nome}")
    public ResponseEntity<ProductsDTO> buscaProdutoPorNome(@PathVariable("nome") String nome){

        return ResponseEntity.ok(produtoService.buscaProdutoPorNome(nome));
    }
}
