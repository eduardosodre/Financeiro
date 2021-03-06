package br.com.vipautomacao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.vipautomacao.domain.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>, JpaSpecificationExecutor<Conta> {

	boolean temResponsavel(Integer contaId, Integer usuarioId);
}
