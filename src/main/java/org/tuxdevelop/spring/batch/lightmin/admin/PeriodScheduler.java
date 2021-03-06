package org.tuxdevelop.spring.batch.lightmin.admin;

import java.util.Date;

import lombok.Getter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Getter
public class PeriodScheduler extends AbstractScheduler {

	private JobConfiguration jobConfiguration;
	private TaskScheduler executorService;
	private JobSchedulerConfiguration jobSchedulerConfiguration;
	private JobLauncher jobLauncher;
	private Job job;
	private JobRunner jobRunner;
	private JobParameters jobParameters;

	// TODO null checks
	public PeriodScheduler(final JobConfiguration jobConfiguration, final Job job, final JobParameters jobParameters,
			final JobIncremeter jobIncremeter, final JobLauncher jobLauncher) {
		this.jobConfiguration = jobConfiguration;
		this.executorService = new ThreadPoolTaskScheduler();
		this.jobSchedulerConfiguration = jobConfiguration.getJobSchedulerConfiguration();
		this.job = job;
		this.jobRunner = new JobRunner(job, jobLauncher, jobParameters, jobIncremeter);
	}

	@Override
	public void schedule() {
		final Date initialDelay = new Date(System.currentTimeMillis() + jobSchedulerConfiguration.getInitialDelay());
		executorService.scheduleWithFixedDelay(jobRunner, initialDelay, jobSchedulerConfiguration.getFixedDelay());
	}

}
