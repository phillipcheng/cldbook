package org.cldutil.crbook.common.entity.test;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cldutil.crbook.common.entity.Book;
import org.cldutil.crbook.common.entity.Volume;
import org.cldutil.util.PatternResult;
import org.junit.Test;


public class VolumeTest {
	private static Logger logger =  LogManager.getLogger(VolumeTest.class);
	
	public static Volume generateTestVolume(String id){
		Volume v = new Volume();
		v.setCoverUri("http://");
		v.setId(id);
		v.setName(id);
		v.setAuthor("me");
		v.setBookNum(12);
		
		//data is generated inside
		v.dataToJSON();
		
		return v;
	}

}
