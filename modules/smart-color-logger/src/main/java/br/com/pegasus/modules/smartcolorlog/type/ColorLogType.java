package br.com.pegasus.modules.smartcolorlog.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColorLogType {

	RED_LIGHT("\u001B[91m"), //
	RED_MEDIUM("\u001B[31m"), //
	RED_BOLD("\u001B[31;1m"), //

	GREEN_LIGHT("\u001B[92m"), //
	GREEN_MEDIUM("\u001B[32m"), //
	GREEN_BOLD("\u001B[32;1m"), //

	BLUE_LIGHT("\u001B[94m"), //
	BLUE_MEDIUM("\u001B[34m"), //
	BLUE_BOLD("\u001B[34;1m"), //

	CYAN_LIGHT("\u001B[96m"), //
	CYAN_MEDIUM("\u001B[36m"), //
	CYAN_BOLD("\u001B[36;1m"), //

	MAGENTA_LIGHT("\u001B[95m"), //
	MAGENTA_MEDIUM("\u001B[35m"), //
	MAGENTA_BOLD("\u001B[35;1m"), //

	YELLOW_LIGHT("\u001B[93m"), //
	YELLOW_MEDIUM("\u001B[33m"), //
	YELLOW_BOLD("\u001B[38;5;226m"), //

	WHITE_LIGHT("\u001B[97m"), //
	WHITE_MEDIUM("\u001B[37m"), //
	WHITE_BOLD("\u001B[37;1m"), //

	BLACK_LIGHT("\u001B[90m"), //
	BLACK_MEDIUM("\u001B[30m"), //
	BLACK_BOLD("\u001B[30;1m"), //

	GREY_LIGHT("\u001B[90m"), //
	GREY_MEDIUM("\u001B[38;5;8m"), //
	GREY_BOLD("\u001B[38;5;8;1m"), //

	ORANGE_LIGHT("\u001B[38;5;214m"), //
	ORANGE_MEDIUM("\u001B[38;5;214m"), //
	ORANGE_BOLD("\u001B[38;5;214;1m"), //

	PURPLE_LIGHT("\u001B[35m"), //
	PURPLE_MEDIUM("\u001B[35m"), //
	PURPLE_BOLD("\u001B[35;1m"), //

	BROWN_LIGHT("\u001B[38;5;94m"), //
	BROWN_MEDIUM("\u001B[38;5;94m"), //
	BROWN_BOLD("\u001B[38;5;94;1m"), //

	BEIGE_LIGHT("\u001B[38;5;230m"), //
	BEIGE_MEDIUM("\u001B[38;5;230m"), //
	BEIGE_BOLD("\u001B[38;5;230;1m");//

	private final String code;
}
