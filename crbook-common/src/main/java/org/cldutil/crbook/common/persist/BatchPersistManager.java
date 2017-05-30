package org.cldutil.crbook.common.persist;

public interface BatchPersistManager {
	public void batchInsertVols(String[][] vols);
	public void batchInsertPages(String[][] pages);
	public void batchInsertBooks(String[][] books);
}
