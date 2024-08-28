package br.com.bfrjunior.fake_api.business.service;


import br.com.bfrjunior.fake_api.apiv1.dto.ProductsDTO;
import br.com.bfrjunior.fake_api.business.converter.ProdutoConverter;
import br.com.bfrjunior.fake_api.infrastructure.entities.ProdutoEntity;
import br.com.bfrjunior.fake_api.infrastructure.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoConverter converter;
    public ProdutoEntity salvaProdutos(ProdutoEntity entity) {
        try{
            return repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Produtos "+ e);
        }
    }

    public ProductsDTO salvaProdutoDTO(ProductsDTO dto) {
        try{
            ProdutoEntity entity = converter.toEntity(dto);
            return converter.toDTO(repository.save(entity));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Produtos");
        }
    }

    public ProductsDTO buscaProdutoPorNome(String nome){
        try {
            return converter.toDTO(repository.findByNome(nome));
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar produto por nome %s", nome), e);
        }
    }

    public void deletaProduto(String nome){
        try {
            repository.deleteByNome(nome);
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao deletar produto %s", nome), e);
        }
    }

        public Boolean existsPorNome(String nome) {
        try {
            return repository.existsByNome(nome);
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar produto por nome %s", nome), e);
        }
    }

    public ProductsDTO updateProduto(String id,ProductsDTO dto){
        try {
            ProdutoEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Id não existe no banco de dados"));
            salvaProdutos(converter.toEntityUpdate(entity,dto,id));
            return converter.toDTO(repository.findByNome(entity.getNome()));
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao atualizar produto"), e);
        }
    }

    public List<ProductsDTO> buscaTodosProdutos(){
        try {
            return converter.toListDTO(repository.findAll());
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar todos os produtos"), e);
        }
    }

}
