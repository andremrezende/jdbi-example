package br.com.rezende.jdbi.example;

import static org.junit.Assert.*;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;

/**
 * 
 * @author Rezende
 *
 */
public class MyDAOTest {

	@Test
	public void testJdbi() {
		MyDAO dao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Realiza conexão com o mysql
			DBI dbi = new DBI("jdbc:mysql://localhost/test", "usuario", "senha");
			//Abra conexão com o MySQL
			dao = dbi.open(MyDAO.class);
			//Cria tabela
			dao.createTable();
			//Insere Registro com nome Rezebde
			dao.insert("Rezende");
			int id = dao.findIdByName("Rezende");
			//Nome retornado da consulta deve ser Rezende
			assertEquals(1, id);
			//Altera nome de Rezende para Rezende2
			dao.updateNameById(id, "Rezende2");
			String name2 = dao.findNameById(id);
			//Nome retornado deve ser Rezende2
			assertEquals("Rezende2", name2);
			
			//Apaga registro criado
			dao.deleteById(id);
			String name3 = dao.findNameById(id);
			//Valor retornado deve ser nulo, pois registro foi apagado acima
			assertNull(name3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail("Error");
		} finally {
			if (dao != null) {
				// Apaga tabela
				dao.dropTable();
				dao.close();
			}
		}
	}
}
