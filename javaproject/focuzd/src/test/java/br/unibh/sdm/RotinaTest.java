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
        Rotina r1 = new Rotina("09", "09:30", "Segunda-Feira", df.parse("11/08/2021"));
        Rotina r2 = new Rotina("10", "10:30", "Segunda-Feira", df.parse("13/02/2022"));
        Rotina r3 = new Rotina("11", "11:30", "Segunda-Feira", df.parse("17/05/2022"));
        Rotina r4 = new Rotina("12", "13:30", "Segunda-Feira", df.parse("19/08/2022"));
        Rotina r5 = new Rotina("13", "10:30", "Segunda-Feira", df.parse("12/02/2023"));
        Rotina r6 = new Rotina("14", "11:30", "Segunda-Feira", df.parse("12/06/2023"));        
        repository.save(r1);
        repository.save(r2);
        repository.save(r3);
        repository.save(r4);
        repository.save(r5);
        repository.save(r6);
        Iterable<Rotina> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Rotina rotina : lista){
            LOGGER.info(rotina.toString());
        }
        
        LOGGER.info("Pesquisando rotina");
        Optional<Rotina> result = repository.findById("09");
        if (result.isPresent()){
            LOGGER.info(result.get().toString());
        }
        assertNotNull(null, result.get());
    }

    @Test
    public void teste2Exclusao() throws ParseException {
    LOGGER.info("Excluindo objetos...");
    Rotina r1 = new Rotina();
    r1.setId("09");
    repository.delete(r1);

    Optional<Rotina> result = repository.findById("09");
    Assert.assertFalse(result.isPresent());
    
    LOGGER.info("Exclusão feita com sucesso");
}

    @Test
    public void teste3ExclusaoTotal() throws ParseException {
    LOGGER.info("Excluindo objetos...");
    Iterable<Rotina> result = repository.findAll();
    for(Rotina r: result){
        repository.delete(r);
    }
    Optional<Rotina> exclude = repository.findById("123");
    
    assertEquals(exclude.isPresent(), false);
    LOGGER.info("Exclusão total dos dados feita com sucesso");
    
}



}
