package org.tuxdevelop.spring.batch.lightmin.model;

import java.io.Serializable;

import org.springframework.batch.core.StepExecution;
import org.tuxdevelop.spring.batch.lightmin.util.DurationHelper;

import lombok.Data;
import lombok.Setter;

/**
 *
 * @author Marcel Becker
 *
 */
@Data
public class StepExecutionModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long jobInstanceId;
	private StepExecution stepExecution;
	@Setter
	private String duration;

	public String getDuration() {
		this.duration = DurationHelper.createDurationValue(stepExecution.getStartTime(), stepExecution.getEndTime());
		return duration;
	}
}
