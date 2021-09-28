package br.com.vipautomacao.api.v1.assembler.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioInput;
import br.com.vipautomacao.domain.model.Usuario;
@Component
public class UsuarioInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainObject(UsuarioInput restauranteInput) {
		return modelMapper.map(restauranteInput, Usuario.class);
	}
	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// br.com.vipautomacao.domain.model.Usuario was altered from 1 to 2

		modelMapper.map(usuarioInput, usuario);
	}
}
