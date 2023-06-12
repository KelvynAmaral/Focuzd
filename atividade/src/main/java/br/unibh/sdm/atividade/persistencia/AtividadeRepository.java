package br.unibh.sdm.atividade.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.atividade.entidades.Atividade;

public interface AtividadeRepository extends CrudRepository<Atividade, Long> {

	List<Atividade> findBynomeAtividade(String nomeAtividade);
	
	List<Atividade> findByDiaSemana(String diaSemana);

	Atividade findById(long id);

}
