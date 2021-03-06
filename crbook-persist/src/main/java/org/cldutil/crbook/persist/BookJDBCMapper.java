package org.cldutil.crbook.persist;

import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cldutil.crbook.common.entity.Book;
import org.cldutil.util.jdbc.JDBCMapper;

public class BookJDBCMapper extends JDBCMapper{
	Logger logger = LogManager.getLogger(BookJDBCMapper.class);
	
	private BookJDBCMapper(){
		
	}
	private static BookJDBCMapper singleton = new BookJDBCMapper();
	
	public static BookJDBCMapper getInstance(){
		return singleton;
	}

	@Override
	public Object getObject(ResultSet cursor) {
    	try{
	    	Book b= new Book(
	    			cursor.getString(1), cursor.getInt(2), cursor.getString(3), 
	    			cursor.getInt(4), cursor.getInt(5), cursor.getTimestamp(6), 
	    			cursor.getString(7), cursor.getString(8), cursor.getInt(9),
					cursor.getString(10), cursor.getInt(11), false);
	    	return b;
    	}catch(Exception e){
    		logger.error("", e);
    		return null;
    	}
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

}
