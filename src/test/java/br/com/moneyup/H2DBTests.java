package br.com.moneyup;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class H2DBTests extends DataSourceBasedDBTestCase
{
	@Autowired
	private DataSource datasource;

	@BeforeAll
	public void beforeAll() throws Exception
	{
		setUp();
	}

	@Override
	protected void setUpDatabaseConfig(DatabaseConfig config)
	{
		config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "\"");
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		config.setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
	}

	@Override
	protected IDataSet getDataSet() throws Exception
	{
		return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(String.format("/dataset/%s.xml", getDataFile())));
	}

	@Override
	protected DataSource getDataSource()
	{
		return datasource;
	}

	protected abstract String getDataFile();
}
