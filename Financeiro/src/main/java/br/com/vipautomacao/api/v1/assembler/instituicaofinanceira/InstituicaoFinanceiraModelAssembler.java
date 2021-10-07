package br.com.vipautomacao.api.v1.assembler.instituicaofinanceira;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.model.instituicaofinanceira.InstituicaoFinanceiraModel;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;
@Component
public class InstituicaoFinanceiraModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public InstituicaoFinanceiraModel toModel(InstituicaoFinanceira instituicaoFinanceira) {
		return modelMapper.map(instituicaoFinanceira, InstituicaoFinanceiraModel.class);
	}
	
	public List<InstituicaoFinanceiraModel> toCollectionModel(List<InstituicaoFinanceira> instituicaoFinanceiras) {
		return instituicaoFinanceiras.stream()
				.map(instituicaoFinanceira -> toModel(instituicaoFinanceira))
				.collect(Collectors.toList());
	}
}
