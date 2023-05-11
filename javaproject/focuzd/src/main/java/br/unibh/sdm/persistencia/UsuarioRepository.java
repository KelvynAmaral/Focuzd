package br.unibh.sdm.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.entidades.Usuario;

@EnableScan()
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    List<Usuario> findByid(String id);

}