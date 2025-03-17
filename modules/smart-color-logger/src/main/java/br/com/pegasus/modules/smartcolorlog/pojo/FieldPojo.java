package br.com.pegasus.modules.smartcolorlog.pojo;

import br.com.pegasus.module.toolkit.type.StringType;
import br.com.pegasus.modules.smartcolorlog.type.ColorLogType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldPojo {

	private ColorLogType textColor;
	private String text;

	public FieldPojo() {
		textColor = ColorLogType.BLACK_MEDIUM;
		text = StringType.TXT_BLACK;
	}

	public String getField() {
		return textColor.getCode() + text;
	}

}
