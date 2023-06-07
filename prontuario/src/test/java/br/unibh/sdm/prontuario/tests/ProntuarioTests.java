package br.unibh.sdm.prontuario.tests;
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

import br.unibh.sdm.prontuario.entidades.Prontuario;
import br.unibh.sdm.prontuario.persistencia.ProntuarioRepository;

/**
 * Classe de testes para a entidade Prontuario.
 *  <br>
 * Para rodar, antes sete a seguinte variavel de ambiente: -Dspring.config.location=C:/Users/jhcru/sdm/backend-prontuario/
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
public class ProntuarioTests {

    private static Logger LOGGER = LoggerFactory.getLogger(ProntuarioTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    
	@Autowired
	private ProntuarioRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Prontuario c1 = new Prontuario(null,"Joao Antunes","00000000000",df.parse("01/01/2000"),"25467","H17");
		repository.save(c1);

		Prontuario c2 = new Prontuario(null,"Maria Silva","00000000001",df.parse("02/02/1995"),"45728","C13");
		repository.save(c2);

		Prontuario c3 = new Prontuario(null,"Tiago Santos","00000000002",df.parse("03/03/1980"),"2456","B12");
		repository.save(c3);
		
		LOGGER.info("Pesquisado todos");
		Iterable<Prontuario> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Prontuario prontuario : lista) {
			LOGGER.info(prontuario.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Prontuario> result = repository.findByNome("Tiago Santos");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getCpf(), "00000000002");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		List<Prontuario> result = repository.findByCpf("00000000000");
		for (Prontuario prontuario : result) {
			LOGGER.info("Excluindo Prontuario id = "+prontuario.getId());
			repository.delete(prontuario);
		}
		result = repository.findByCpf("00000000001");
		for (Prontuario prontuario : result) {
			LOGGER.info("Excluindo Prontuario id = "+prontuario.getId());
			repository.delete(prontuario);
		}
		result = repository.findByCpf("00000000002");
		for (Prontuario prontuario : result) {
			LOGGER.info("Excluindo Prontuario id = "+prontuario.getId());
			repository.delete(prontuario);
		}
		result = repository.findByNome("Tiago Santos");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclus�o feita com sucesso");
	}
	
	
}
