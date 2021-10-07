package br.com.vipautomacao.api.v1.assembler.banco;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.modelinput.banco.BancoInput;
import br.com.vipautomacao.domain.model.Banco;
@Component
public class BancoInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Banco toDomainObject(BancoInput restauranteInput) {
		return modelMapper.map(restauranteInput, Banco.class);
	}
	public void copyToDomainObject(BancoInput bancoInput, Banco banco) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// br.com.vipautomacao.domain.model.Banco was altered from 1 to 2

		modelMapper.map(bancoInput, banco);
	}
}
