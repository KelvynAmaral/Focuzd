package br.unibh.sdm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.sdm.entidades.*;
import br.unibh.sdm.persistencia.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, UsuarioTest.DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioTest.class);
	private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Configuration
	@EnableDynamoDBRepositories(basePackageClasses = UsuarioRepository.class)
	public static class DynamoDBConfig {

		@Value("${amazon.aws.accesskey}")
		private String amazonAWSAccessKey;

		@Value("${amazon.aws.secretkey}")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}

	@Autowired
	private UsuarioRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando usuarios...");
		Usuario u1 = new Usuario("1", "Henrique", "teste123@gmail");
		Usuario u2 = new Usuario("2", "Julia", "ju123@gmail.com");
		Usuario u3 = new Usuario("3", "Carlos", "test123@gmail.com");

		repository.save(u1);
		repository.save(u2);
		repository.save(u3);

		LOGGER.info("Pesquisado todos");
		Iterable<Usuario> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Usuario usuario : lista) {
			LOGGER.info(usuario.toString());
		}

		LOGGER.info("Pesquisado um objeto");
		Optional<Usuario> result = repository.findById("1");
		assertEquals(result.isPresent(), true);
		LOGGER.info("Encontrado: {}", result.get());
	}

	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		Usuario u1 = new Usuario();
		u1.setId("1");
		repository.delete(u1);

		Optional<Usuario> result = repository.findById("1");
		Assert.assertFalse(result.isPresent());
		
		LOGGER.info("Exclusão feita com sucesso");
	}

	@Test
    public void teste3ExclusaoTotal() throws ParseException {
    LOGGER.info("Excluindo objetos...");
    Iterable<Usuario> result = repository.findAll();
    for(Usuario u: result){
        repository.delete(u);
    }
    Optional<Usuario> exclude = repository.findById("123");
    
    assertEquals(exclude.isPresent(), false);

	LOGGER.info("Exclusão total dos dados feita com sucesso");
    
}

}