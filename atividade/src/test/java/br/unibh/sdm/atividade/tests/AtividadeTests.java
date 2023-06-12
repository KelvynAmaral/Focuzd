package br.unibh.sdm.atividade.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unibh.sdm.atividade.entidades.Atividade;
import br.unibh.sdm.atividade.persistencia.AtividadeRepository;

/**
 * Classe de testes para a entidade Atividade.
 *  <br>
 * Para rodar, antes sete a seguinte variavel de ambiente: -Dspring.config.location=C:/Users/jhcru/sdm/backend-atividade/
 *  <br>
 * Neste diretoio, criar um arquivo application.properties contendo as seguitnes variaveis:
 * <br>
 * # Connection parameters<br>
 * spring.datasource.url= jdbc:postgresql://HOST:5432/DBNAME<br>
 * spring.datasource.username=USER<br>
 * spring.datasource.password=PASSWORD<br>
 * <br>
 * spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true<br>
 * spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect<br>
 * <br>
 * # Hibernate ddl auto (create, create-drop, validate, update)<br>
 * spring.jpa.hibernate.ddl-auto=create<br>
 * @author jhcru
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtividadeTests {

    private static Logger LOGGER = LoggerFactory.getLogger(AtividadeTests.class);
    //private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    
	@Autowired
	private AtividadeRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Atividade c1 = new Atividade(null,"Joao Antunes","00000000000","12:30","25467","H17");
		repository.save(c1);

		Atividade c2 = new Atividade(null,"Maria Silva","00000000001","12:30","45728","C13");
		repository.save(c2);

		Atividade c3 = new Atividade(null,"Tiago Santos","00000000002","12:30","2456","B12");
		repository.save(c3);
		
		LOGGER.info("Pesquisado todos");
		Iterable<Atividade> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Atividade atividade : lista) {
			LOGGER.info(atividade.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Atividade> result = repository.findBynomeAtividade("Tiago Santos");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getDiaSemana(), "00000000002");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		List<Atividade> result = repository.findByDiaSemana("00000000000");
		for (Atividade atividade : result) {
			LOGGER.info("Excluindo Atividade id = "+atividade.getId());
			repository.delete(atividade);
		}
		result = repository.findByDiaSemana("00000000001");
		for (Atividade atividade : result) {
			LOGGER.info("Excluindo Atividade id = "+atividade.getId());
			repository.delete(atividade);
		}
		result = repository.findByDiaSemana("00000000002");
		for (Atividade atividade : result) {
			LOGGER.info("Excluindo Atividade id = "+atividade.getId());
			repository.delete(atividade);
		}
		result = repository.findBynomeAtividade("Tiago Santos");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclus�o feita com sucesso");
	}
	
	
}
