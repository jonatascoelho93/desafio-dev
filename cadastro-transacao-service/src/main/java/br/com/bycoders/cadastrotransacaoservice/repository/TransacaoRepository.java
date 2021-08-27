package br.com.bycoders.cadastrotransacaoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bycoders.cadastrotransacaoservice.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findByOrderByNomeLojaAsc();

}
