package br.com.rezende.jdbi.example;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface MyDAO {
	//Cria tabela TBLEXEMPLO no banco
	@SqlUpdate("CREATE TABLE IF NOT EXISTS TBLEXEMPLO (ID int NOT NULL AUTO_INCREMENT, name varchar(100) NOT NULL, PRIMARY KEY (ID))")
	void createTable();
	
	//Insere registro no banco na tabela TBLEXEMPLO
	@SqlUpdate("insert into TBLEXEMPLO (name) values (:name)")
	void insert(@Bind("name") String name);

	//filtra no banco da tabela TBLEXEMPLO
	@SqlQuery("select ID from TBLEXEMPLO where name = :name")
	int findIdByName(@Bind("name") String name);
	
	//filtra no banco da tabela TBLEXEMPLO
	@SqlQuery("select name from TBLEXEMPLO where id = :id")
	String findNameById(@Bind("id") int id);
	
	//Altera registro no banco na tabela TBLEXEMPLO
	@SqlUpdate("update TBLEXEMPLO set name = :name where id = :id")
	int updateNameById(@Bind("id") int id, @Bind("name") String name);
	
	//Apaga registro no banco na tabela TBLEXEMPLO
	@SqlUpdate("delete from TBLEXEMPLO where id = :id")
	int deleteById(@Bind("id") int id);

	//Apaga tabela após conclusão de testes
	@SqlUpdate("drop table TBLEXEMPLO")
	void dropTable();

	//Finaliza conexão
	void close();
}
