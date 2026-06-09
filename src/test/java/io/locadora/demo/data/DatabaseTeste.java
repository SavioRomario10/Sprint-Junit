package io.locadora.demo.data;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTeste {

  static Connection con;

  @BeforeAll
  static void setUpDatabae() throws Exception {
    
    con = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

    con.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR(255))");
  }

  @BeforeEach
  void insertTest() throws Exception {
    con.createStatement().execute("INSERT INTO users (id, name) VALUES (1, 'João')");
  }

  @Test
  @DisplayName("Deve verificar se o usuário existe no banco de dados")
  void testUserExists() throws Exception {
    var rs = con.createStatement().executeQuery("SELECT * FROM users WHERE id = 1");

    assertTrue(rs.next());
    assertEquals("João", rs.getString("name"));
  }

  @AfterAll
  static void tearDownDatabase() throws Exception {
    con.close();
  }
}