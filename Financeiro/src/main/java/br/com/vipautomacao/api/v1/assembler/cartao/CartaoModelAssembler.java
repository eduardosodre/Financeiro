package br.com.vipautomacao.api.v1.assembler.cartao;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.model.cartao.CartaoModel;
import br.com.vipautomacao.domain.model.Cartao;
@Component
public class CartaoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CartaoModel toModel(Cartao cartao) {
		return modelMapper.map(cartao, CartaoModel.class);
	}
	
	public List<CartaoModel> toCollectionModel(List<Cartao> cartaos) {
		return cartaos.stream()
				.map(cartao -> toModel(cartao))
				.collect(Collectors.toList());
	}
}
