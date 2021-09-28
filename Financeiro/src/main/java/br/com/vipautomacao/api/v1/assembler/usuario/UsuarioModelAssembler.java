package br.com.vipautomacao.api.v1.assembler.usuario;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.model.usuario.UsuarioModel;
import br.com.vipautomacao.domain.model.Usuario;
@Component
public class UsuarioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}
	
	public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
		return usuarios.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}
}
