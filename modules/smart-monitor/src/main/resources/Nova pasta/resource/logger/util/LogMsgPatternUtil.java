package br.com.pegasus.module.smartmonitor.resource.logger.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LogMsgPatternUtil {

	CREATE_MSG_LOG("[%s -> %s] %s."), //
	GET_ANNOTATION_VALUE_NULL("Problemas para recuperar a anotação %s."), //
	GET_ANNOTATION_VALUE_NOT_FOUND("Problemas para recuperar o campo '%s' da anotação '%s'."), //
	CHECK_METHOD_BEFORE_AFTER("Problemas no método '%s': não pode estar no mesmo método '%s' e ('%s' ou '%s')"),
	CHECK_METHOD_GLOBAL("Problemas no método '%s': não pode estar no mesmo método '%s' e ('%s' ou '%s' ou '%s')");

	private final String msg;

	public String getFormat(Object... values) {
		return String.format(msg, values);
	}

}
