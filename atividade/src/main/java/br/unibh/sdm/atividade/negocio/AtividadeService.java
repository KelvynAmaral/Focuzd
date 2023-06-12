package br.unibh.sdm.atividade.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.atividade.entidades.Atividade;
import br.unibh.sdm.atividade.persistencia.AtividadeRepository;

/**
 * Classe contendo a logica de negocio para Atividade
 * @author jhcru
 *
 */
@Service
public class AtividadeService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final AtividadeRepository atividadeRepo;

    public AtividadeService(AtividadeRepository atividadeRepository){
        this.atividadeRepo=atividadeRepository;
    }
    
    public List<Atividade> getAtividades(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Atividade> lista = this.atividadeRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Atividade>();
        }
        return IteratorUtils.toList(lista.iterator());
    }    

    public Atividade getAtividadeById(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Atividade com o codigo {}",id);
        }
        Optional<Atividade> retorno = this.atividadeRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Atividade com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public List<Atividade> getAtividadeBydiaSemana(String diaSemana){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Atividade> lista = this.atividadeRepo.findByDiaSemana(diaSemana);
        if (lista == null) {
        	return new ArrayList<Atividade>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public List<Atividade> getAtividadeByNome(String nomeAtividade){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Atividade> lista = this.atividadeRepo.findByDiaSemana(nomeAtividade);
        if (lista == null) {
        	return new ArrayList<Atividade>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public Atividade saveAtividade(Atividade atividade){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Atividade com os detalhes {}",atividade.toString());
        }
        return this.atividadeRepo.save(atividade);
    }
    
    public void deleteAtividade(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Atividade com id {}",id);
        }
        this.atividadeRepo.deleteById(id);
    }

    public boolean isAtividadeExists(Atividade atividade){
    	Optional<Atividade> retorno = this.atividadeRepo.findById(atividade.getId());
        return retorno.isPresent() ? true:  false;
    }

}