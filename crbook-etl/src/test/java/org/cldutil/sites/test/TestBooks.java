package org.cldutil.sites.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cldutil.crbook.util.BookHandler;
import org.cldutil.crbook.util.BookUtil;
import org.cldutil.datacrawl.test.TestBase;
import org.cldutil.taskmgr.entity.RunType;
import org.cldutil.util.CsvUtil;
import org.cldutil.util.DownloadUtil;
import org.cldutil.util.PatternIO;
import org.cldutil.util.PatternResult;
import org.cldutil.util.StringUtil;
import org.cldutil.util.entity.CrawledItem;
import org.cldutil.util.entity.Product;
import org.junit.Before;
import org.junit.Test;

public class TestBooks extends TestBase {
	private static Logger logger =  LogManager.getLogger(TestBooks.class);
	private String propFile = "client1-v2.properties";
	
	public TestBooks(){
		super();
	}

	
	public static final String XRS52_CONF="52xrs.xml";
	public static final String A8Z8_CONF="a8z8.xml";
	public static final String DMZJ_CONF="dmzj.xml";
	public static final String LU28_CONF="28lu.xml";
	public static final String CL_CONF="childrenslibrary.xml";
	public static final String FKB_CONF="freekidsbooks.xml";
	public static final String BAOLINY_CONF="baoliny.xml";

	public static final String CBO_CONF="cbo.xml";
	private String[] allConf = new String[]{
			XRS52_CONF,
			A8Z8_CONF, //register user only (paid)
			DMZJ_CONF,
			//CL_CONF,
			//FKB_CONF,
			//BAOLINY_CONF,
	};
	
	
	@Before
    public void setUp() throws Exception{
		this.setProp(propFile);
    }
	
	//
	public void testOneRoot(String rootConf) throws Exception{
		List<CrawledItem> cil = browsePrd(rootConf, null, "lvl1", RunType.onePath);
		logger.info(cil);
		List<String> csvs = CsvUtil.outputCsv(cil, null);
		logger.info(csvs);
	}
	
	@Test
	public void testOneRoot1() throws Exception{
		testOneRoot(XRS52_CONF);
	}
	@Test
	public void testOneRoot2() throws Exception{
		testOneRoot(A8Z8_CONF);
	}
	@Test
	public void testOneRoot3() throws Exception{
		testOneRoot(DMZJ_CONF);
	}
	@Test
	public void test28LuRoot() throws Exception{
		testOneRoot(LU28_CONF);
	}
	
	//
	public void testOneCategory(String catConf, String url) throws Exception{
		List<CrawledItem> cil = browsePrd(catConf, url, "lvl2", RunType.oneLevel);
		logger.info(cil);
		List<String> csvs = CsvUtil.outputCsv(cil, null);
		logger.info(csvs);
	}
	
	@Test
	public void testOneCategory1() throws Exception{
		testOneCategory(XRS52_CONF, "http://www.52xrs.com/list/lsgs.htm");
	}
	
	@Test
	public void testOneCategory2() throws Exception{
		testOneCategory(DMZJ_CONF, "http://manhua.dmzj.com/gsmmx21/");
	}
	
	@Test
	public void testOneCategory28Lu() throws Exception{
		testOneCategory(LU28_CONF, "http://www.28lu.com/lianhuanhuaSpecial/hongyan/Index.html");
	}
	
	//
	public void testPrd(String bookConf, String url) throws Exception{
		List<CrawledItem> cil =browsePrd(bookConf, url, "prd", RunType.onePrd);
		logger.info(cil);
		List<String> csvs = CsvUtil.outputCsv(cil, null);
		logger.info(csvs);
	}
	
	@Test
	public void testPrdXrs52() throws Exception {
		testPrd(XRS52_CONF, "http://www.52xrs.com/comic/2413/");
	}
	@Test
	public void testPrdDMZJ() throws Exception {
		testPrd(DMZJ_CONF, "http://manhua.dmzj.com/gsmmx21/18175.shtml");
	}
	@Test
	public void testPrd28Lu() throws Exception {
		testPrd(LU28_CONF, "http://www.28lu.com/xd/170757886_29.html");
	}

	@Test
	public void dlHY() throws Exception{
		BookUtil.downloadSeries("client1-v2.properties", LU28_CONF, "lvl2", "http://www.28lu.com/lianhuanhuaSpecial/hongyan/Index.html", "c://mydoc//picbook");
	}
	@Test
	public void dlSGYY() throws Exception{
		BookUtil.downloadSeries("client1-v2.properties", LU28_CONF, "lvl2", "http://www.28lu.com/lianhuanhuaSpecial/sgyy/Index.html", "c://mydoc//picbook");
	}
	@Test
	public void dlSHZ() throws Exception{
		BookUtil.downloadSeries("client1-v2.properties", LU28_CONF, "lvl2", "http://www.28lu.com/lianhuanhuaSpecial/shc/Index.html", "c://mydoc//picbook");
	}
	@Test
	public void dlSS() throws Exception{
		BookUtil.downloadSeries("client1-v2.properties", LU28_CONF, "lvl2", "http://www.28lu.com/lianhuanhuaSpecial/ss/Index.html", "c://mydoc//picbook");
	}
	@Test
	public void dlSYQZ() throws Exception{
		BookUtil.downloadSeries("client1-v2.properties", LU28_CONF, "lvl2", "http://www.28lu.com/lianhuanhuaSpecial/syqz/Index.html", "c://mydoc//picbook");
	}
	@Test
	public void dlXYJ() throws Exception{
		BookUtil.downloadSeries("client1-v2.properties", LU28_CONF, "lvl2", "http://www.28lu.com/lianhuanhuaSpecial/xyjhnms/Index.html", "c://mydoc//picbook");
	}
	@Test
	public void dlXTZ() throws Exception{
		BookUtil.downloadSeries("client1-v2.properties", LU28_CONF, "lvl2", "http://www.28lu.com/lianhuanhuaSpecial/xtc/Index.html", "c://mydoc//picbook");
	}

}
