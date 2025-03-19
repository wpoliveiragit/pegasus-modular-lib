package br.com.pegasus.modules.smartcolorlog.method;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.pegasus.module.toolkit.type.BooleanType;
import br.com.pegasus.module.toolkit.type.NumberType;
import br.com.pegasus.modules.smartcolorlog.config.ColorLoggerConfig;
import br.com.pegasus.modules.smartcolorlog.pojo.SmartLogPojo;
import br.com.pegasus.modules.smartcolorlog.type.ColorLogType;
import br.com.pegasus.modules.smartcolorlog.type.ColorLoggerLevelType;

public class ColorLoggerMethod {

	public static void printLog(SmartLogPojo smartLog, String message, Object... objects) {
		String messageLog = (objects == null || objects.length == NumberType.N_0) ? //
				message : String.format(message, objects);
		smartLog.getLogger().log(smartLog.getLevel(), messageLog);
	}

	public static void logConfigHandler(SmartLogPojo smartLog, Formatter formatter) {
		Logger logger = smartLog.getLogger();
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.setUseParentHandlers(BooleanType.FALSE);
		logger.addHandler(consoleHandler);
		logger.setLevel(Level.ALL);
	}

	public static Formatter infoConfig(String currentClassLog) {
		return createFormatter(currentClassLog, ColorLoggerLevelType.INFO, ColorLogType.GREEN_BOLD);
	}

	public static Formatter warnConfig(String currentClassLog) {
		return createFormatter(currentClassLog, ColorLoggerLevelType.WARN, ColorLogType.YELLOW_BOLD);
	}

	public static Formatter erroConfig(String currentClassLog) {
		return createFormatter(currentClassLog, ColorLoggerLevelType.ERRO, ColorLogType.RED_BOLD);
	}

	private static Formatter createFormatter(//
			String currentClassLog, ColorLoggerLevelType colorLoggerLevel, ColorLogType colorLog) {

		ColorLoggerConfig configLog = new ColorLoggerConfig(colorLoggerLevel, currentClassLog);

		// setColor
		configLog.clearPattern(ColorLogType.BLUE_BOLD);
		configLog.getSymbleField().setTextColor(colorLog);
		configLog.getLevelField().setTextColor(colorLog);
		configLog.getMessageField().setTextColor(colorLog);

		// add pattern
		configLog.addSymble();
		configLog.addLevel();
		configLog.addDate();
		configLog.addCurrentClassLog();
		configLog.addElapsedTimeApp();
		configLog.addLogMessage();

		// create pattern
		configLog.createPattern();

		return configLog;
	}

}
