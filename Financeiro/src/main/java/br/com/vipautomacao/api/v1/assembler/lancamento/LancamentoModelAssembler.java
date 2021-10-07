package br.com.vipautomacao.api.v1.assembler.lancamento;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.model.lancamento.LancamentoModel;
import br.com.vipautomacao.domain.model.Lancamento;
@Component
public class LancamentoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public LancamentoModel toModel(Lancamento lancamento) {
		return modelMapper.map(lancamento, LancamentoModel.class);
	}
	
	public List<LancamentoModel> toCollectionModel(List<Lancamento> lancamentos) {
		return lancamentos.stream()
				.map(lancamento -> toModel(lancamento))
				.collect(Collectors.toList());
	}
}
