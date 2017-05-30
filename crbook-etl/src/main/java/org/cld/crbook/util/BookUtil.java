package org.cld.crbook.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cld.datacrawl.CrawlConf;
import org.cld.datacrawl.test.CrawlTestUtil;
import org.cld.taskmgr.entity.RunType;
import org.cld.util.DownloadUtil;
import org.cld.util.PatternIO;
import org.cld.util.PatternResult;
import org.cld.util.StringUtil;
import org.cld.util.entity.Category;
import org.cld.util.entity.CrawledItem;
import org.cld.util.entity.Product;

public class BookUtil {
	private static Logger logger =  LogManager.getLogger(BookUtil.class);
	public static String seriesNameKey = "seriesName";
	public static String bookNameKey="bookName";
	
	public static void downloadSeries(String crawlPropFile, String bookSiteConf, String prdTaskName, String seriesUrl, String localRoot) throws Exception{
		CrawlConf cconf = new CrawlConf(crawlPropFile);
		ExecutorService exeService = Executors.newFixedThreadPool(20);
		String taskId = "BookUtilTaskId";
		List<CrawledItem> cil = CrawlTestUtil.browsePrd(null, bookSiteConf, seriesUrl, prdTaskName, cconf, taskId, null, false, RunType.all);
		String seriesName= null;
		for (CrawledItem ci: cil){
			if (ci instanceof Product){
				String bookName = null;
				List<String> imageUrls = new ArrayList<String>();
				Product prd = (Product)ci;
				if (prd.getParamMap().containsKey(seriesNameKey)){
					seriesName = (String) prd.getParamMap().get(seriesNameKey);
				}
				if (prd.getParamMap().containsKey(bookNameKey)){
					bookName = (String)prd.getParamMap().get(bookNameKey);
				}
				logger.info(String.format("got prd %s", prd.toString()));
				List<String> additionalPageList = (List<String>) prd.getParam(BookHandler.BOOK_PAGE_URLS);
				if (additionalPageList!=null){
					imageUrls.addAll(additionalPageList);
				}
				
				PatternIO bookpageurlspattern = (PatternIO) prd.getParam(BookHandler.BOOK_PAGE_URLS_pattern);
				int totalPage = prd.getTotalPage();
				if(bookpageurlspattern!=null){
					PatternResult pattern = bookpageurlspattern.getPR();
					if (pattern!=null){
						for (int page=1; page<totalPage; page++){
							String url = PatternResult.guessUrl(pattern, page-1);
							logger.info(String.format("book %d, url for page %d is %s", ci.getName(), page, url));
							imageUrls.add(url);
						}
					}
				}
				if (seriesName==null){
					logger.error(String.format("series name not found!!"));
				}
				String localBookDir = String.format("%s%s%s%s%s", localRoot, File.separator, seriesName, File.separator, bookName);
				DownloadUtil du = new DownloadUtil(localBookDir, imageUrls);
				exeService.submit(du);
			}else if (ci instanceof Category){
				Category ctg = (Category)ci;
				logger.info(String.format("got category %s", ctg.toString()));
			}
		}
		
		exeService.shutdown();
		exeService.awaitTermination(4, TimeUnit.HOURS);
	}

}
