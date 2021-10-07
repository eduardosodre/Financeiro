package br.com.vipautomacao.api.v1.assembler.categoria;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.model.categoria.CategoriaModel;
import br.com.vipautomacao.domain.model.Categoria;
@Component
public class CategoriaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoriaModel toModel(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaModel.class);
	}
	
	public List<CategoriaModel> toCollectionModel(List<Categoria> categorias) {
		return categorias.stream()
				.map(categoria -> toModel(categoria))
				.collect(Collectors.toList());
	}
}
