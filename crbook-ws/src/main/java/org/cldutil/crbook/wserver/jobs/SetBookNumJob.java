package org.cldutil.crbook.wserver.jobs;

import javax.servlet.ServletContext;

import org.cldutil.crbook.persist.JDBCPersistService;
import org.cldutil.crbook.persist.SetBookNum;
import org.cldutil.crbook.wsserver.InitListener;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SetBookNumJob implements Job {
	public SetBookNumJob(){
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ServletContext servletContext = (ServletContext) context.getMergedJobDataMap().get(InitListener.PARAM_SERVLET_CONTEXT);
		SetBookNum setBookNum = new SetBookNum((JDBCPersistService) servletContext.getAttribute(InitListener.PERSIST_MANAGER_KEY));
		setBookNum.setBookNum();
	}
}
