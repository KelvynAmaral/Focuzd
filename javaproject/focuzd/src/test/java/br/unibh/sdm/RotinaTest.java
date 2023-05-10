package br.unibh.sdm;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Optional;

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
import br.unibh.sdm.entidade.*;
import br.unibh.sdm.persistencia.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, RotinaTest.DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RotinaTest {
    private static Logger LOGGER = LoggerFactory.getLogger(RotinaTest.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = { RotinaRepository.class })
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
    private RotinaRepository repository;

    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Gerando teste...");
        Rotina r1 = new Rotina("123", "11:30", "Segunda-Feira", df.parse("11/08/2023"));
        repository.save(r1);
        Iterable<Rotina> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Rotina rotina : lista){
            LOGGER.info(rotina.toString());
        }
        LOGGER.info("Pesquisando rotina");
        Optional<Rotina> result = repository.findById("123");
        if (result.isPresent()){
            LOGGER.info(result.get().toString());
        }
        assertNotNull(null, result.get());
    }
        @Test
	    public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		Iterable<Rotina> result = repository.findAll();
        for(Rotina r: result){
            repository.delete(r);
        }
        Optional<Rotina> exclude = repository.findById("123");
        
        assertEquals(exclude.isPresent(), false);
        
    }

}
