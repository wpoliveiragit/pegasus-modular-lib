package br.com.pegasus.modules.smartcolorlog.config;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;

import br.com.pegasus.module.toolkit.method.CommonMethod;
import br.com.pegasus.module.toolkit.type.StringType;
import br.com.pegasus.modules.smartcolorlog.pojo.FieldPojo;
import br.com.pegasus.modules.smartcolorlog.type.ColorLogType;
import br.com.pegasus.modules.smartcolorlog.type.ColorLoggerLevelType;
import br.com.pegasus.modules.smartcolorlog.type.SmartColorSymbleLoggerType;
import br.com.pegasus.modules.smartcolorlog.util.ColorLoggerConstUtil;
import lombok.Getter;

public class ColorLoggerConfig extends Formatter {

	private static final long START_TIME_APP = System.currentTimeMillis();

	private String pattern;
	private String nameLevel;
	private String currentClassLog;
	private List<FieldPojo> logFields;

	private @Getter FieldPojo dateField;
	private @Getter FieldPojo levelField;
	private @Getter FieldPojo symbleField;
	private @Getter FieldPojo messageField;
	private @Getter FieldPojo elapsedTimeAppField;
	private @Getter FieldPojo currentClassLogField;

	public ColorLoggerConfig(ColorLoggerLevelType colorLoggerLevelType, String currentClassLog) {

		// set initial attributes
		logFields = new ArrayList<>();
		this.nameLevel = colorLoggerLevelType.name();
		this.currentClassLog = currentClassLog;

		// create objects
		symbleField = new FieldPojo();
		dateField = new FieldPojo();
		levelField = new FieldPojo();
		currentClassLogField = new FieldPojo();
		elapsedTimeAppField = new FieldPojo();
		messageField = new FieldPojo();

		defaultSetting();
	}

	public final void defaultSetting() {
		clearPattern(ColorLogType.BLUE_BOLD);

		// set texte
		symbleField.setText(SmartColorSymbleLoggerType.CIRCLE.getCode());
		dateField.setText(ColorLoggerConstUtil.DATE_TEXT_FORMAT);
		levelField.setText(StringType.L_BRACKET + nameLevel + StringType.R_BRACKET);
		currentClassLogField.setText(StringType.L_BRACKET + currentClassLog + StringType.R_BRACKET);
		elapsedTimeAppField.setText(ColorLoggerConstUtil.TEXT_FORMAT_MILI_SECONDS);
		messageField.setText(ColorLoggerConstUtil.TEXT_MESSAGE_FORMAT);

		// set log list
		logFields.add(symbleField);
		logFields.add(dateField);
		logFields.add(levelField);
		logFields.add(currentClassLogField);
		logFields.add(elapsedTimeAppField);
		logFields.add(messageField);
	}

	/** Remove todos os campos do log e coloca a cor padrao do texto */
	public final void clearPattern(final ColorLogType defaultColor) {
		logFields.clear();
		pattern = StringType.TXT_BLACK;

		// set default color
		symbleField.setTextColor(defaultColor);
		dateField.setTextColor(defaultColor);
		levelField.setTextColor(defaultColor);
		currentClassLogField.setTextColor(defaultColor);
		elapsedTimeAppField.setTextColor(defaultColor);
		messageField.setTextColor(defaultColor);
	}

	public final void addSymble() {
		logFields.add(symbleField);
	}

	public final void addDate() {
		logFields.add(dateField);
	}

	public final void addLevel() {
		logFields.add(levelField);
	}

	public final void addCurrentClassLog() {
		logFields.add(currentClassLogField);
	}

	public final void addElapsedTimeApp() {
		logFields.add(elapsedTimeAppField);
	}

	public final void addLogMessage() {
		logFields.add(messageField);
	}

	public final void createPattern() {
		pattern = logFields.stream().map(e -> e.getField() + StringType.TXT_SPACE).collect(Collectors.joining());
	}

	@Override
	public String format(LogRecord record) {
		String date = CommonMethod.timestampFormat(record.getMillis());

		String elapsedTimeApp = (System.currentTimeMillis() - START_TIME_APP) + StringType.TXT_BLACK;

		String patternLog = pattern + StringType.LINE_FEED;
		patternLog = patternLog//
				.replaceAll(ColorLoggerConstUtil.MESSAGE_POINT, record.getMessage())//
				.replaceAll(ColorLoggerConstUtil.DATE_POINT, date)//
				.replaceAll(ColorLoggerConstUtil.ELAPSED_TIME_APP_POINT, elapsedTimeApp);

		return patternLog;
	}

}