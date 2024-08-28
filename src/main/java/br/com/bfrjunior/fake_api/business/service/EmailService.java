package br.com.bfrjunior.fake_api.business.service;

import br.com.bfrjunior.fake_api.business.converter.ProdutoConverter;
import br.com.bfrjunior.fake_api.infrastructure.entities.ProdutoEntity;
import br.com.bfrjunior.fake_api.infrastructure.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final ProdutoRepository repository;
    private final ProdutoConverter converter;
    public ProdutoEntity salvaProdutos(ProdutoEntity entity) {
        try{
            return repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Produtos "+ e);
        }
    }
}
