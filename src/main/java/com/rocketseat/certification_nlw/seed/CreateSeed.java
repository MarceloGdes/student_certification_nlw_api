package com.rocketseat.certification_nlw.seed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// Script para rodar o create.sql como o JDBC Template
public class CreateSeed {

    //Definindo o JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    // Atribuindo nosso data source no jdbctemplate
    public CreateSeed(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        //Instanciando nosso dataSource
        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5200/pg_nlw");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        //Rodando o script
        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);

    }

    //Executando o script sql
    public void run(String... args) {
        executeSqlFile("src/main/resources/create.sql");
    }

    //Fazendo a leitura do arquivo e executando o arquivo no JDBC
    private void executeSqlFile(String filePath) {
        
        try {
            //Leitura
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));

            //Execução do script
            jdbcTemplate.execute(sqlScript);

            System.out.println("Seed executado com sucesso");

        }catch(IOException e) {
            System.err.println("Erro ao executar arquivo " + e.getMessage() + e.getCause());
        }
    }

}
