https://www.baeldung.com/spring-data-jdbc-intro

Spring Data JDBC is a persistence framework that is not as complex as Spring Data JPA. It doesn't provide cache, 
lazy loading, write-behind, or many other features of JPA. Nevertheless, it has its own ORM and provides most of 
the features we're used with Spring Data JPA like mapped entities, repositories, query annotations, and JdbcTemplate.

An important thing to keep in mind is that Spring Data JDBC doesn't offer schema generation. As a result, we are 
responsible for explicitly creating the schema.

