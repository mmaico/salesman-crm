package br.com.kproj.salesman.infra;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe helper para testes que necessitem do DBUnit.
 */
@Component
public class DBUnitUtil {
	
	public static final String DATASET_FILE_PATH = "dbunit-dataset.xml";
	
	private static ReplacementDataSet replacementDataSet;
	private static IDataSet dataSet;
	private static Connection connection;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Limpa a base de dados e insere os dados padroes para os testes.
	 * @throws java.io.IOException Se nao conseguir fazer o parse do dataset.
	 * @throws java.sql.SQLException Se nao conseguir pegar uma conexao.
	 * @throws DatabaseUnitException Se nao conseguir limpar os dados da base e reinserta-los.
	 */
	public void initDBUnit() throws IOException, SQLException, DatabaseUnitException {
		initDBUnit(DBUnitUtil.DATASET_FILE_PATH);
	}

	/**
	 * Limpa a base de dados e insere os dados padroes para os testes.
	 * @param resoucePath Nome do arquivo de dataset do dbunit.
	 * @throws java.io.IOException Se nao conseguir fazer o parse do dataset.
	 * @throws java.sql.SQLException Se nao conseguir pegar uma conexao.
	 * @throws DatabaseUnitException Se nao conseguir limpar os dados da base e reinserta-los.
	 */
	public void initDBUnit(String resoucePath) throws IOException, SQLException, DatabaseUnitException {
		FlatXmlDataSetBuilder xmlBuilder = new FlatXmlDataSetBuilder();
		xmlBuilder.setCaseSensitiveTableNames(true);
		
		dataSet = xmlBuilder.build(new File("src/test/resources/" + resoucePath));
		DBUnitUtil.replacementDataSet = new ReplacementDataSet(dataSet);
		DBUnitUtil.replacementDataSet.addReplacementObject("[null]", null);
		
		try {
			
			connection = dataSource.getConnection();
			
			IDatabaseConnection iConnection = new DatabaseConnection(connection);
			iConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
			
			DatabaseOperation.DELETE_ALL.execute(iConnection, DBUnitUtil.replacementDataSet);
			DatabaseOperation.INSERT.execute(iConnection, DBUnitUtil.replacementDataSet);
			
		} finally {
			closeConnection(connection);
		}
	}

	private static void closeConnection(Connection conn) {
		try {
			
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}