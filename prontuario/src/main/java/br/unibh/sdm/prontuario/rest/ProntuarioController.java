package br.unibh.sdm.prontuario.rest;

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

import br.unibh.sdm.prontuario.entidades.Prontuario;
import br.unibh.sdm.prontuario.negocio.ProntuarioService;

/**
 * Classe contendo as definicoes de servicos REST/JSON para Prontuario
 * @author jhcru
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "prontuario")
public class ProntuarioController {
   
    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService){
        this.prontuarioService=prontuarioService;
    }

    @GetMapping(value = "")
    public List<Prontuario> getProntuarios(){
        return prontuarioService.getProntuarios();
    }
    
    @GetMapping(value="{id}")
    public Prontuario getProntuarioById(@PathVariable Long id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return prontuarioService.getProntuarioById(id);
        }
        throw new Exception("Prontuario com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Prontuario createProntuario(@RequestBody @NotNull Prontuario prontuario) throws Exception {
         return prontuarioService.saveProntuario(prontuario);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Prontuario updateProntuario(@PathVariable String id, 
    		@RequestBody @NotNull Prontuario prontuario) throws Exception {
         return prontuarioService.saveProntuario(prontuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateProntuario(@PathVariable Long id) throws Exception {
         prontuarioService.deleteProntuario(id);
         return true;
    }
    
}