package org.cldutil.crbook.persist.test;

import static org.junit.Assert.*;

import java.io.File;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cldutil.crbook.common.xml.XmlImporter;
import org.cldutil.crbook.persist.DBHeader;
import org.cldutil.crbook.persist.JDBCPersistService;
import org.cldutil.crbook.persist.SetBookNum;
import org.cldutil.util.jdbc.DataSourcePool;
import org.junit.BeforeClass;
import org.junit.Test;

public class SetBookNumTest {
	public static Logger logger = LogManager.getLogger(SetBookNumTest.class);
	
	public static DataSource ds = null;
	public static JDBCPersistService pService=null;
	
	@BeforeClass
	public static void setUp() {
		logger.info("setup");
		ds = DataSourcePool.setupDataSource(DataSourcePool.initFromProperties("crpersist.properties"));
		pService = new JDBCPersistService(ds);
	}

	@Test
	public void setBookNum(){
		SetBookNum setBookNum = new SetBookNum(pService);
		setBookNum.setBookNum();
	}

}
