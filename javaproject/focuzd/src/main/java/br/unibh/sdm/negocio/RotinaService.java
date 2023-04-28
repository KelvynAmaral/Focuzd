package br.unibh.sdm.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.unibh.sdm.entidade.Rotina;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import br.unibh.sdm.persistencia.RotinaRepository;

/**
 * Classe contendo a lógica de negócio para Rotina
 * 
 * @author Focuzd
 *
 */
@Service
public class RotinaService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final RotinaRepository rotinaRepo;

    public RotinaService(RotinaRepository RotinaRepository) {
        this.rotinaRepo = RotinaRepository;
    }

    public List<Rotina> getRotinas() {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os objetos");
        }
        Iterable<Rotina> lista = this.rotinaRepo.findAll();
        if (lista == null) {
            return new ArrayList<Rotina>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Rotina getRotinaById(long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando rotinas com o codigo {}", id);
        }
        Optional<Rotina> retorno = this.rotinaRepo.findById(id);
        if (!retorno.isPresent()) {
            throw new RuntimeException("Cotacao com o id " + id + " nao encontrada");
        }
        return retorno.get();
    }

    public Rotina saveRotina(Rotina rotina) {
        if (logger.isInfoEnabled()) {
            logger.info("Salvando Cotacao com os detalhes {}", rotina.toString());
        }
        return this.rotinaRepo.save(rotina);
    }

    public void deleteRotina(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Excluindo Cotacao com id {}", id);
        }
        this.rotinaRepo.deleteById(id);
    }

    public boolean isRotinaExists(Rotina rotina) {
        Optional<Rotina> retorno = this.rotinaRepo.findById(rotina.getId());
        return retorno.isPresent() ? true : false;
    }

}
