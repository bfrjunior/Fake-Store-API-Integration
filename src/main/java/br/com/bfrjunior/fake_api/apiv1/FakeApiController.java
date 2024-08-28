package br.com.bfrjunior.fake_api.apiv1;

import br.com.bfrjunior.fake_api.apiv1.dto.ProductsDTO;
import br.com.bfrjunior.fake_api.business.FakeApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

    private final FakeApiService service;

    @Operation(summary = "Busca produtos da API e Salvar", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca dos dados"),
    })
    @GetMapping("")
    public ResponseEntity<List<ProductsDTO>> buscaProdutos() {
        return ResponseEntity.ok(service.buscaProdutos());

    }
}
