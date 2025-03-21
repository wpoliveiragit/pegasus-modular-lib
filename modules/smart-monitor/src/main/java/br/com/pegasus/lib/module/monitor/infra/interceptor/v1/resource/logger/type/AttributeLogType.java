package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.type;

import lombok.Getter;

public enum AttributeLogType {

	BEFORE("before"), //
	AFTER("after"), //
	EXCEPTION("exception");

	@Getter
	private final String value;

	private AttributeLogType(String value) {
		this.value = value;
	}
}
