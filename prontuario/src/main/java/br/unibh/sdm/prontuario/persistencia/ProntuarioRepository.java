package br.unibh.sdm.prontuario.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.prontuario.entidades.Prontuario;

public interface ProntuarioRepository extends CrudRepository<Prontuario, Long> {

	List<Prontuario> findByNome(String nome);
	
	List<Prontuario> findByCpf(String cpf);

	Prontuario findById(long id);

}
