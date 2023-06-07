package br.unibh.sdm.rest;

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

import br.unibh.sdm.entidades.Rotina;
import br.unibh.sdm.negocio.RotinaService;

/**
 * Classe contendo as definições de serviços REST/JSON para Rotina
 * 
 * @author focuzd
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "rotina")

public class RotinaController {

    private final RotinaService rotinaService;

    public RotinaController(RotinaService rotinaService) {
        this.rotinaService = rotinaService;
    }

    @GetMapping(value = "")
    public List<Rotina> getRotinas() {
        return rotinaService.getRotinas();
    }

    @GetMapping(value = "{id}")
    public Rotina getRotinaById(@PathVariable String id) throws Exception {
        if (!ObjectUtils.isEmpty(id)) {
            return rotinaService.getRotinaById(id);
        }
        throw new Exception("Rotina com codigo " + id + " nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Rotina createRotina(@RequestBody @NotNull Rotina rotina) throws Exception {
        return rotinaService.saveRotina(rotina);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Rotina updateRotina(@PathVariable String id,
            @RequestBody @NotNull Rotina rotina) throws Exception {
        return rotinaService.saveRotina(rotina);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateRotina(@PathVariable String id) throws Exception {
        rotinaService.deleteRotina(id);
        return true;
    }

}
