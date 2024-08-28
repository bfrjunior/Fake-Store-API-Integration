package br.com.bfrjunior.fake_api.business.service;

import br.com.bfrjunior.fake_api.apiv1.dto.ProductsDTO;
import br.com.bfrjunior.fake_api.business.converter.ProdutoConverter;
import br.com.bfrjunior.fake_api.infrastructure.client.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final FakeApiClient cliente;
    private final ProdutoConverter converter;
    private final ProdutoService produtoService;

    public List<ProductsDTO> buscaProdutos() {
        try {


            List<ProductsDTO> dto = cliente.buscaListaProdutos();
            dto.forEach(produto -> {
                        Boolean retorno = produtoService.existsPorNome(produto.getNome());
                        if (retorno.equals(false)) {
                            produtoService.salvaProdutos(converter.toEntity(produto));
                        } else {
                            throw new RuntimeException("Produto já existente no banco de dados " + produto.getNome());
                        }
                    }

            );
            return produtoService.buscaTodosProdutos();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar e gravar produtos no banco de dados");
        }
    }
    }

