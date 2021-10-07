package br.com.vipautomacao.api.v1.assembler.cartao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.modelinput.cartao.CartaoInput;
import br.com.vipautomacao.domain.model.Cartao;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.model.Usuario;
@Component
public class CartaoInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Cartao toDomainObject(CartaoInput restauranteInput) {
		return modelMapper.map(restauranteInput, Cartao.class);
	}
	public void copyToDomainObject(CartaoInput cartaoInput, Cartao cartao) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// br.com.vipautomacao.domain.model.Cartao was altered from 1 to 2

		if(cartao.getInstituicao() == null){
			cartao.setInstituicao(new InstituicaoFinanceira());
		}
		if(cartao.getContaPagamento() == null){
			cartao.setContaPagamento(new Conta());
		}
		if(cartao.getUsuario() == null){
			cartao.setUsuario(new Usuario());
		}
		modelMapper.map(cartaoInput, cartao);
	}
}
