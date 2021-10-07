package br.com.vipautomacao.api.v1.assembler.banco;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.model.banco.BancoModel;
import br.com.vipautomacao.domain.model.Banco;
@Component
public class BancoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public BancoModel toModel(Banco banco) {
		return modelMapper.map(banco, BancoModel.class);
	}
	
	public List<BancoModel> toCollectionModel(List<Banco> bancos) {
		return bancos.stream()
				.map(banco -> toModel(banco))
				.collect(Collectors.toList());
	}
}
