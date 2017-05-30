package org.cldutil.crbook.wserver.jobs;


import javax.servlet.ServletContext;

import org.cldutil.crbook.persist.JDBCPersistService;
import org.cldutil.crbook.persist.SetVolumeCover;
import org.cldutil.crbook.wsserver.InitListener;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SetVolumeCoverJob implements Job {
	
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		ServletContext servletContext = (ServletContext) jec.getMergedJobDataMap().get(InitListener.PARAM_SERVLET_CONTEXT);
		SetVolumeCover svc = new SetVolumeCover((JDBCPersistService) servletContext.getAttribute(InitListener.PERSIST_MANAGER_KEY));
		svc.setAllCoverUrl();
	}

}
