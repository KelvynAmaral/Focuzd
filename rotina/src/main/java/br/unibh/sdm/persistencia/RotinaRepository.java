package br.unibh.sdm.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.entidades.Rotina;

@EnableScan()
public interface RotinaRepository extends CrudRepository<Rotina, String> {

    List<Rotina> findByid(String id);

}