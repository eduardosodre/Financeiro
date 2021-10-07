package br.com.vipautomacao.api.v1.assembler.conta;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.modelinput.conta.ContaInput;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.model.Banco;
import br.com.vipautomacao.domain.model.Usuario;
@Component
public class ContaInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Conta toDomainObject(ContaInput restauranteInput) {
		return modelMapper.map(restauranteInput, Conta.class);
	}
	public void copyToDomainObject(ContaInput contaInput, Conta conta) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// br.com.vipautomacao.domain.model.Conta was altered from 1 to 2

		if(conta.getBanco() == null){
			conta.setBanco(new Banco());
		}
		if(conta.getUsuario() == null){
			conta.setUsuario(new Usuario());
		}
		modelMapper.map(contaInput, conta);
	}
}
