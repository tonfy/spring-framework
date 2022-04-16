/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.context.testfixture.beans;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * A stub {@link ApplicationListener}.
 *
 * @author Thomas Risberg
 * @author Juergen Hoeller
 * @author Genkui Du
 */
public class BeanThatListens implements ApplicationListener<ApplicationEvent> {

	private BeanThatBroadcasts beanThatBroadcasts;

	private final AtomicInteger eventCount = new AtomicInteger(0);


	public BeanThatListens() {
	}

	public BeanThatListens(BeanThatBroadcasts beanThatBroadcasts) {
		this.beanThatBroadcasts = beanThatBroadcasts;
		Map<?, BeanThatListens> beans = beanThatBroadcasts.applicationContext.getBeansOfType(BeanThatListens.class);
		if (!beans.isEmpty()) {
			throw new IllegalStateException("Shouldn't have found any BeanThatListens instances");
		}
	}


	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		eventCount.getAndIncrement();
		if (beanThatBroadcasts != null) {
			beanThatBroadcasts.receivedCount++;
		}
	}

	public int getEventCount() {
		return eventCount.get();
	}

	public void zero() {
		eventCount.set(0);
	}

}
