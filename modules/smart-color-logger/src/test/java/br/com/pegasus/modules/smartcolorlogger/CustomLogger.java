package br.com.pegasus.modules.smartcolorlogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CustomLogger {

	public static void main(String[] args) {
		Logger logger = CustomLogger.createLogger(CustomLogger.class.getSimpleName());

		logger.severe("Este é um log SEVERE.");
		logger.warning("Este é um log WARNING.");
		logger.info("Este é um log INFO.");
	}

	public static final Logger createLogger(String loggerName) {
		final String PATTERN = "yyyy-MM-dd HH:mm:ss";
		final String RESET = "\u001B[0m";
		final String YELLOW = "\u001B[33m";
		final String RED = "\u001B[31m";
		final String BLUE = "\u001B[34m";
		final String LIGHT_BLUE = "\u001B[94m";
		final String BLACK = "\u001B[30m";
		final String BOLD = "\u001B[1m";

		final Logger log = Logger.getLogger(CustomLogger.class.getSimpleName());
		log.setLevel(Level.INFO);
		Formatter format = new Formatter() {

			@Override
			public String format(LogRecord record) {
				String color = Stream.of(record.getLevel().getName()).map(t -> switch (t) {
				case "SEVERE" -> BLACK;
				case "WARNING" -> RED;
				case "INFO" -> BLUE;
				default -> RESET;
				}).findFirst().get();

				String dt = new SimpleDateFormat(PATTERN).format(new Date(record.getMillis()));
				String datatime = String.format("[%s%s%s%s]", BOLD, YELLOW, dt, RESET);
				String logLevel = String.format("[%s%s%-5s%s]%s", BOLD, color, record.getLevel(), RESET, BOLD);
				String thread = String.format("[%s%s%s%s]", BOLD, LIGHT_BLUE, Thread.currentThread().getName(), RESET);
				String loggerName = String.format("[%s%s%s%s]", BOLD, LIGHT_BLUE, record.getLoggerName(), RESET);
				String message = String.format("%s%s%s%s", RESET, BLACK, record.getMessage(), RESET);
				return String.format("%s %s %s %s: %s%n", datatime, logLevel, thread, loggerName, message);
			}
		};

		ConsoleHandler ch = new ConsoleHandler();
		ch.setFormatter(format);
		log.addHandler(ch);
		log.setUseParentHandlers(false);
		return log;
	}

}
