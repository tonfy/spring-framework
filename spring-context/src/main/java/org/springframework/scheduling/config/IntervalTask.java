/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.scheduling.config;

import java.time.Duration;

import org.springframework.util.Assert;

/**
 * {@link Task} implementation defining a {@code Runnable} to be executed at a given
 * millisecond interval which may be treated as fixed-rate or fixed-delay depending on
 * context.
 *
 * @author Chris Beams
 * @author Arjen Poutsma
 * @since 3.2
 * @see ScheduledTaskRegistrar#addFixedRateTask(IntervalTask)
 * @see ScheduledTaskRegistrar#addFixedDelayTask(IntervalTask)
 */
public class IntervalTask extends Task {

	private final Duration interval;

	private final Duration initialDelay;


	/**
	 * Create a new {@code IntervalTask}.
	 * @param runnable the underlying task to execute
	 * @param interval how often in milliseconds the task should be executed
	 * @param initialDelay the initial delay before first execution of the task
	 * @deprecated as of 6.0, in favor on {@link #IntervalTask(Runnable, Duration, Duration)}
	 */
	@Deprecated
	public IntervalTask(Runnable runnable, long interval, long initialDelay) {
		this(runnable, Duration.ofMillis(interval), Duration.ofMillis(initialDelay));
	}

	/**
	 * Create a new {@code IntervalTask} with no initial delay.
	 * @param runnable the underlying task to execute
	 * @param interval how often in milliseconds the task should be executed
	 * @deprecated as of 6.0, in favor on {@link #IntervalTask(Runnable, Duration)}
	 */
	@Deprecated
	public IntervalTask(Runnable runnable, long interval) {
		this(runnable, Duration.ofMillis(interval), Duration.ZERO);
	}

	/**
	 * Create a new {@code IntervalTask} with no initial delay.
	 * @param runnable the underlying task to execute
	 * @param interval how often the task should be executed
	 * @since 6.0
	 */
	public IntervalTask(Runnable runnable, Duration interval) {
		this(runnable, interval, Duration.ZERO);
	}

	/**
	 * Create a new {@code IntervalTask}.
	 * @param runnable the underlying task to execute
	 * @param interval how often the task should be executed
	 * @param initialDelay the initial delay before first execution of the task
	 * @since 6.0
	 */
	public IntervalTask(Runnable runnable, Duration interval, Duration initialDelay) {
		super(runnable);
		Assert.notNull(interval, "Interval must not be null");
		Assert.notNull(initialDelay, "InitialDelay must not be null");

		this.interval = interval;
		this.initialDelay = initialDelay;
	}

	/**
	 * Copy constructor.
	 */
	IntervalTask(IntervalTask task) {
		super(task.getRunnable());
		Assert.notNull(task, "IntervalTask must not be null");

		this.interval = task.getIntervalDuration();
		this.initialDelay = task.getInitialDelayDuration();
	}




	/**
	 * Return how often in milliseconds the task should be executed.
	 * @deprecated as of 6.0, in favor of {@link #getIntervalDuration()}
	 */
	@Deprecated
	public long getInterval() {
		return this.interval.toMillis();
	}

	/**
	 * Return how often the task should be executed.
	 * @since 6.0
	 */
	public Duration getIntervalDuration() {
		return this.interval;
	}

	/**
	 * Return the initial delay before first execution of the task.
	 * @deprecated as of 6.0, in favor of {@link #getInitialDelayDuration()}
	 */
	@Deprecated
	public long getInitialDelay() {
		return this.initialDelay.toMillis();
	}

	/**
	 * Return the initial delay before first execution of the task.
	 * @since 6.0
	 */
	public Duration getInitialDelayDuration() {
		return this.initialDelay;
	}

}
