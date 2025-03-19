package br.com.pegasus.modules.smartcolorlog.pojo;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.pegasus.modules.smartcolorlog.type.ColorLoggerLevelType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartLogPojo {

	private Logger logger;
	private Level level;

	public SmartLogPojo(String nameClass, ColorLoggerLevelType colorLoggerLevel) {
		logger = Logger.getLogger(nameClass);
		level = colorLoggerLevel.getLevel();
	}

}
