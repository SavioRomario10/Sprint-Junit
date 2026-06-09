package io.locadora.data;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class DatabaseTeste {

  static Connection con;

  @BeforeAll
  static void setUpDatabae() throws Exception {
    
    con = DriverManager.getConnection("jdbc:h2:mem:locadora", "sa", "");

    con.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR(255))");
  }

  @Test
  void insertTest() throws Exception {
    con.createStatement().execute("INSERT INTO users (id, name) VALUES (1, 'João')");
  }

  @AfterAll
  static void tearDownDatabase() throws Exception {
    con.close();
  }
}