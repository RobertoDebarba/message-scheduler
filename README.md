# Plataforma de envio de comunicações

A plataforma permite realizar o envio (ainda não implementado) de comunicações de diversos tipos, como 
e-mail e SMS, através de agendamentos por APIs REST.

## Pré-requisitos

* JDK 14
* [Maven](https://maven.apache.org/)
* [Postgres 11](https://www.postgresql.org/)

### Configuração da base

Informe o valor das propriedades a seguir no arquivo `application.properties`:
* `spring.datasource.url`
* `spring.datasource.username`
* `spring.datasource.password`

As configurações devem apontar para uma base da dados Postgres vazia. A aplicação criará as tabelas e campos.

## Ferramentas
* [Spring Boot](https://spring.io/projects/spring-boot) - O projeto foi construído usando Spring Boot 2
* [Flyway](https://flywaydb.org/) - O banco de dados é criado e versionado usando scripts do Flyway
* [Postman](https://www.getpostman.com/) - As APIs são desenvolvidas usando a ferramenta Postman
* [Swagger](http://localhost:8080/swagger-ui.html) - As APIs são documentadas usando a ferramenta Swagger

## Documentação

* [Swagger](http://localhost:8080/swagger-ui.html) - `http://localhost:8080/swagger-ui.html`
    * A documentação das APIs e descrição dos contratos estão disponíveis nesse endereço ao executar a aplicação.
* Postman TODO

## Como executar

A aplicação irá iniciar no endereço <http://localhost:8080>  
Você pode executar localmente pela IDE ou linha de comando: 

### IDE 

Importando o projeto na sua IDE favorita como um projeto Maven e executar o método `main` da classe `com.arc.sbtest.SBtemplateApplication`.

### Linha de comando

```shell
mvn spring-boot:run
```

## Como desenvolver

### Execução dos testes

A aplicação possui testes automatizados unitários e sua execução pode ser feita através do seguinte comando:

```shell
mvn test
```

## License

The codebase is licensed under [GPL v3.0](http://www.gnu.org/licenses/gpl-3.0.html).