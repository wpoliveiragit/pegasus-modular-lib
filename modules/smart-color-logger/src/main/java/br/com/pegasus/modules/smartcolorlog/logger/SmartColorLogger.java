package br.com.pegasus.modules.smartcolorlog.logger;

import br.com.pegasus.module.toolkit.method.CommonMethod;
import br.com.pegasus.module.toolkit.type.ObjectType;
import br.com.pegasus.modules.smartcolorlog.method.ColorLoggerMethod;
import br.com.pegasus.modules.smartcolorlog.pojo.SmartLogPojo;
import br.com.pegasus.modules.smartcolorlog.type.ColorLoggerLevelType;

public class SmartColorLogger {

	private SmartLogPojo infoLog;
	private SmartLogPojo warnLog;
	private SmartLogPojo errorLog;

	public SmartColorLogger() {
		Class<?> clazz = CommonMethod.captureCreatorClass();
		String currentClassLog = CommonMethod.abbreviatesCanonicalName(clazz);
		String nameClass = clazz.getCanonicalName();

		infoLog = new SmartLogPojo(nameClass + CommonMethod.getNextIndex(), ColorLoggerLevelType.INFO);
		warnLog = new SmartLogPojo(nameClass + CommonMethod.getNextIndex(), ColorLoggerLevelType.WARN);
		errorLog = new SmartLogPojo(nameClass + CommonMethod.getNextIndex(), ColorLoggerLevelType.ERRO);

		ColorLoggerMethod.logConfigHandler(infoLog, ColorLoggerMethod.infoConfig(currentClassLog));
		ColorLoggerMethod.logConfigHandler(warnLog, ColorLoggerMethod.warnConfig(currentClassLog));
		ColorLoggerMethod.logConfigHandler(errorLog, ColorLoggerMethod.erroConfig(currentClassLog));
	}

	// ## INFO
	public void info(String message) {
		ColorLoggerMethod.printLog(infoLog, message, ObjectType.ARRAY_OBJECT_EMPIT);
	}

	public void info(String message, Object... objects) {
		ColorLoggerMethod.printLog(infoLog, message, objects);
	}

	// ## WARNING
	public void warn(String message) {
		ColorLoggerMethod.printLog(warnLog, message, ObjectType.ARRAY_OBJECT_EMPIT);
	}

	public void warn(String message, Object... objects) {
		ColorLoggerMethod.printLog(warnLog, message, objects);
	}

	// ## ERROR
	public void error(String message) {
		ColorLoggerMethod.printLog(errorLog, message, ObjectType.ARRAY_OBJECT_EMPIT);
	}

	public void error(String message, Object... objects) {
		ColorLoggerMethod.printLog(errorLog, message, objects);
	}
	
	

}
