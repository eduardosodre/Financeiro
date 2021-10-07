package br.com.vipautomacao.api.v1.assembler.categoria;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.api.v1.modelinput.categoria.CategoriaInput;
import br.com.vipautomacao.domain.model.Categoria;
import br.com.vipautomacao.domain.model.Usuario;
@Component
public class CategoriaInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Categoria toDomainObject(CategoriaInput restauranteInput) {
		return modelMapper.map(restauranteInput, Categoria.class);
	}
	public void copyToDomainObject(CategoriaInput categoriaInput, Categoria categoria) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// br.com.vipautomacao.domain.model.Categoria was altered from 1 to 2

		if(categoria.getUsuario() == null){
			categoria.setUsuario(new Usuario());
		}
		modelMapper.map(categoriaInput, categoria);
	}
}
