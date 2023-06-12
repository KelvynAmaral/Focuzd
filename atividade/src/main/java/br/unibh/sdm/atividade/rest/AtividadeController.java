package br.unibh.sdm.atividade.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.unibh.sdm.atividade.entidades.Atividade;
import br.unibh.sdm.atividade.negocio.AtividadeService;

/**
 * Classe contendo as definicoes de servicos REST/JSON para Atividade
 * @author jhcru
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "atividade")
public class AtividadeController {
   
    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService){
        this.atividadeService=atividadeService;
    }

    @GetMapping(value = "")
    public List<Atividade> getAtividades(){
        return atividadeService.getAtividades();
    }
    
    @GetMapping(value="{id}")
    public Atividade getAtividadeById(@PathVariable Long id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return atividadeService.getAtividadeById(id);
        }
        throw new Exception("Atividade com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Atividade createAtividade(@RequestBody @NotNull Atividade atividade) throws Exception {
         return atividadeService.saveAtividade(atividade);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Atividade updateAtividade(@PathVariable String id, 
    		@RequestBody @NotNull Atividade atividade) throws Exception {
         return atividadeService.saveAtividade(atividade);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateAtividade(@PathVariable Long id) throws Exception {
         atividadeService.deleteAtividade(id);
         return true;
    }
    
}