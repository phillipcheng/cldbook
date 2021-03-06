package org.cldutil.crbook.persist;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.cldutil.crbook.common.entity.Volume;

public interface ReadingTraversalProcess {
	
	public void postProcess(ReadingChildInfo ci, DataSource ds);
	
	public List<Volume> getSubVolumes(List<String> parentCatIds, int offset, int limit);
	
	public long getSubVC(List<String> parentCatIds);

}
