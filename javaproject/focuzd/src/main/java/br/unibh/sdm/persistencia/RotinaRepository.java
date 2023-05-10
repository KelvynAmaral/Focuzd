package br.unibh.sdm.persistencia;



import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.entidade.Rotina;

@EnableScan()
public interface RotinaRepository extends CrudRepository<Rotina, String> {

    

}