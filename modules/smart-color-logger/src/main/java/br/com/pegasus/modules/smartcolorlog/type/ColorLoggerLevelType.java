package br.com.pegasus.modules.smartcolorlog.type;

import java.util.logging.Level;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColorLoggerLevelType {

	INFO(Level.INFO, "INFO"), //
	WARN(Level.WARNING, "WARN"), //
	ERRO(Level.SEVERE, "ERRO");

	private final Level level;
	private final String name;

}
