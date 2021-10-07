package br.com.vipautomacao.api.v1.assembler.conta;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.model.conta.ContaModel;
import br.com.vipautomacao.domain.model.Conta;
@Component
public class ContaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ContaModel toModel(Conta conta) {
		return modelMapper.map(conta, ContaModel.class);
	}
	
	public List<ContaModel> toCollectionModel(List<Conta> contas) {
		return contas.stream()
				.map(conta -> toModel(conta))
				.collect(Collectors.toList());
	}
}
