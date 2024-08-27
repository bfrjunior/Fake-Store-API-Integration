package br.com.bfrjunior.fake_api.business;

import br.com.bfrjunior.fake_api.apiv1.dto.ProductsDTO;
import br.com.bfrjunior.fake_api.infrastructure.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final FakeApiClient cliente;

    public List<ProductsDTO> buscaProdutos() {
        return cliente.buscaListaProdutos();
    }
}
