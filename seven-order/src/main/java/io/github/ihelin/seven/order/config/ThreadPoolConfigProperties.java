package io.github.ihelin.seven.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author iHelin
 */
@Component
@ConfigurationProperties(prefix = "seven.thread")
public class ThreadPoolConfigProperties {

	private Integer coreSize;

	private Integer maxSize;

	private Integer keepAliveTime;

	public Integer getCoreSize() {
		return coreSize;
	}

	public void setCoreSize(Integer coreSize) {
		this.coreSize = coreSize;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public Integer getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(Integer keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
}
