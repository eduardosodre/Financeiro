package br.com.vipautomacao.api.v1.assembler.instituicaofinanceira;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.modelinput.instituicaofinanceira.InstituicaoFinanceiraInput;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;
@Component
public class InstituicaoFinanceiraInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public InstituicaoFinanceira toDomainObject(InstituicaoFinanceiraInput restauranteInput) {
		return modelMapper.map(restauranteInput, InstituicaoFinanceira.class);
	}
	public void copyToDomainObject(InstituicaoFinanceiraInput instituicaoFinanceiraInput, InstituicaoFinanceira instituicaoFinanceira) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// br.com.vipautomacao.domain.model.InstituicaoFinanceira was altered from 1 to 2

		modelMapper.map(instituicaoFinanceiraInput, instituicaoFinanceira);
	}
}
