package br.com.pegasus.module.toolkit.method;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import br.com.pegasus.module.toolkit.type.StringType;

public final class DateMethod {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(StringType.DATE_PATTERN)
			.withZone(ZoneId.systemDefault()); // Fuso horário padrão do sistema

	/**
	 * Obtem a data atual em formato de string.
	 * 
	 * @return A data atual em formato de string.
	 */
	public static String getDateTimeNow() {
		return ZonedDateTime.now(ZoneId.systemDefault()).format(FORMATTER);
	}

	/**
	 * Converte o timestamp em uma string data.
	 * 
	 * @param timestamp o timestamp.
	 * @return A String date.
	 */
	public static String toStringDate(long timestamp) {
		return FORMATTER.format(Instant.ofEpochMilli(timestamp));
	}

	/**
	 * Obtem o dia da semana da data.
	 * 
	 * @param date a data.
	 * @return o dia da semana da data.
	 */
	public static String getDayOfWeek(long timestamp) {
		return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).getDayOfWeek()
				.getDisplayName(TextStyle.FULL, Locale.getDefault());
	}

	/**
	 * Obtem o dia da semana da data.
	 * 
	 * @param date a data.
	 * @return o dia da semana da data.
	 */
	public static String getDayOfWeek(String date) {
		return LocalDate.parse(date, FORMATTER).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
	}
}
