package br.unibh.sdm.prontuario.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.prontuario.entidades.Prontuario;
import br.unibh.sdm.prontuario.persistencia.ProntuarioRepository;

/**
 * Classe contendo a logica de negocio para Prontuario
 * @author jhcru
 *
 */
@Service
public class ProntuarioService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final ProntuarioRepository prontuarioRepo;

    public ProntuarioService(ProntuarioRepository prontuarioRepository){
        this.prontuarioRepo=prontuarioRepository;
    }
    
    public List<Prontuario> getProntuarios(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Prontuario> lista = this.prontuarioRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Prontuario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }    

    public Prontuario getProntuarioById(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Prontuario com o codigo {}",id);
        }
        Optional<Prontuario> retorno = this.prontuarioRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Prontuario com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public List<Prontuario> getProntuarioByCpf(String cpf){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Prontuario> lista = this.prontuarioRepo.findByCpf(cpf);
        if (lista == null) {
        	return new ArrayList<Prontuario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public List<Prontuario> getProntuarioByNome(String nome){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Prontuario> lista = this.prontuarioRepo.findByCpf(nome);
        if (lista == null) {
        	return new ArrayList<Prontuario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public Prontuario saveProntuario(Prontuario prontuario){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Prontuario com os detalhes {}",prontuario.toString());
        }
        return this.prontuarioRepo.save(prontuario);
    }
    
    public void deleteProntuario(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Prontuario com id {}",id);
        }
        this.prontuarioRepo.deleteById(id);
    }

    public boolean isProntuarioExists(Prontuario prontuario){
    	Optional<Prontuario> retorno = this.prontuarioRepo.findById(prontuario.getId());
        return retorno.isPresent() ? true:  false;
    }

}