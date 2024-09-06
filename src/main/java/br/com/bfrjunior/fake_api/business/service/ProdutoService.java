package br.com.bfrjunior.fake_api.business.service;


import br.com.bfrjunior.fake_api.apiv1.dto.ProductsDTO;
import br.com.bfrjunior.fake_api.business.converter.ProdutoConverter;
import br.com.bfrjunior.fake_api.infrastructure.entities.ProdutoEntity;
import br.com.bfrjunior.fake_api.infrastructure.exceptions.BusinessException;
import br.com.bfrjunior.fake_api.infrastructure.exceptions.ConflictException;
import br.com.bfrjunior.fake_api.infrastructure.exceptions.UnprocessableEntityException;
import br.com.bfrjunior.fake_api.infrastructure.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoConverter converter;
    public ProdutoEntity salvaProdutos(ProdutoEntity entity) {
        try{
            return repository.save(entity);
        } catch (Exception e) {
            throw new BusinessException("Erro ao salvar Produtos "+ e);
        }
    }

    public ProductsDTO salvaProdutoDTO(ProductsDTO dto) {
        try{
            Boolean retorno = existsPorNome(dto.getNome());
            if(retorno.equals(true)) {
                throw new ConflictException("Produto já existente no banco de dados " + dto.getNome());
            }
            ProdutoEntity entity = converter.toEntity(dto);
            return converter.toDTO(repository.save(entity));
        }catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao salvar Produtos");
        }
    }

    public ProductsDTO buscaProdutoPorNome(String nome){
        try {
            ProdutoEntity produto = repository.findByNome(nome);
            if (Objects.isNull(produto)) {
                throw new UnprocessableEntityException("Não foram encontrados produtos com o nome " + nome);
            }
            return converter.toDTO(produto);
        }catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }catch (Exception e) {
            throw new BusinessException(format("Erro ao buscar produto por nome = %s", nome), e);
        }
    }

    public void deletaProduto(String nome){
        try {
            Boolean retorno = existsPorNome(nome);
            if(retorno.equals(false)){
                throw new UnprocessableEntityException("Não foi possivel deletar o produto,pois não existe produto com nome "+nome );
            }
            else{
                repository.deleteByNome(nome);
            }
        }catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }catch (Exception e) {
            throw new BusinessException(format("Erro ao deletar produto %s", nome), e);
        }
    }

        public Boolean existsPorNome(String nome) {
        try {
            return repository.existsByNome(nome);
        }catch (Exception e) {
            throw new BusinessException(format("Erro ao buscar produto por nome %s", nome), e);
        }
    }

    public ProductsDTO updateProduto(String id,ProductsDTO dto){
        try {
            ProdutoEntity entity = repository.findById(id).orElseThrow(() -> new UnprocessableEntityException("Produto não encontrado na base de dados"));
            salvaProdutos(converter.toEntityUpdate(entity,dto,id));
            return converter.toDTO(repository.findByNome(entity.getNome()));
        }catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }catch (Exception e) {
            throw new BusinessException("Erro ao atualizar produto", e);
        }
    }

    public List<ProductsDTO> buscaTodosProdutos(){
        try {
            return converter.toListDTO(repository.findAll());
        }catch (Exception e) {
            throw new BusinessException(format("Erro ao buscar todos os produtos"), e);
        }
    }

}
