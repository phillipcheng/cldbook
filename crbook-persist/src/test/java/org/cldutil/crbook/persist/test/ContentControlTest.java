package org.cldutil.crbook.persist.test;

import javax.sql.DataSource;

import org.cldutil.crbook.persist.ContentControl;
import org.cldutil.crbook.persist.JDBCPersistService;
import org.cldutil.util.jdbc.DataSourcePool;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContentControlTest {
	public static DataSource ds = null;
	public static JDBCPersistService pService=null;
	
	@BeforeClass
	public static void setUp() {
		ds = DataSourcePool.setupDataSource(DataSourcePool.initFromProperties("crpersist.properties"));
		pService = new JDBCPersistService(ds);
	}
	
	@Test
	public void testContentControl(){
		ContentControl cc = new ContentControl("ContentControl.xml", pService);
		cc.main();
	}
}
