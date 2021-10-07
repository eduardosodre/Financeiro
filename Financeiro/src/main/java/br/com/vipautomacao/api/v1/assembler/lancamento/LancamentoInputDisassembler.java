package br.com.vipautomacao.api.v1.assembler.lancamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.modelinput.lancamento.LancamentoInput;
import br.com.vipautomacao.domain.model.Lancamento;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.model.Categoria;
import br.com.vipautomacao.domain.model.Usuario;
@Component
public class LancamentoInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Lancamento toDomainObject(LancamentoInput restauranteInput) {
		return modelMapper.map(restauranteInput, Lancamento.class);
	}
	public void copyToDomainObject(LancamentoInput lancamentoInput, Lancamento lancamento) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// br.com.vipautomacao.domain.model.Lancamento was altered from 1 to 2

		if(lancamento.getConta() == null){
			lancamento.setConta(new Conta());
		}
		if(lancamento.getCategoria() == null){
			lancamento.setCategoria(new Categoria());
		}
		if(lancamento.getConta2() == null){
			lancamento.setConta2(new Conta());
		}
		if(lancamento.getUsuario() == null){
			lancamento.setUsuario(new Usuario());
		}
		modelMapper.map(lancamentoInput, lancamento);
	}
}
