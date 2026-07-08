package br.com.gabrielmsantos.produto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void deveConectarAoBanco() throws Exception {

        assertNotNull(dataSource);

        try (Connection connection = dataSource.getConnection()) {

            assertNotNull(connection);
            assertFalse(connection.isClosed());

            System.out.println("Banco: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("Versão: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("URL: " + connection.getMetaData().getURL());
        }
    }
}