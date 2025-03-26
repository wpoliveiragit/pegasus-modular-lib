package br.com.pegasus.module.smartannotation.domain.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pegasus.module.smartannotation.domain.annotation.json.serializer.IntrospectorMaskSerializer;
import br.com.pegasus.module.smartannotation.domain.annotation.json.type.mask.StringMask;

public final class JsonUtil {

	private static final ObjectMapper JSON_MASK_SERIALIZER_MAPPER;

	static {
		JSON_MASK_SERIALIZER_MAPPER = new ObjectMapper();
		JSON_MASK_SERIALIZER_MAPPER.setAnnotationIntrospector(new IntrospectorMaskSerializer());
	}

	/**
	 * <p>
	 * Converte o objeto para json onde será considerado as annotações:
	 * </p>
	 * <ul>
	 * <li>{@link br.com.projeto.anotacoes.StringMask StringMask}</li>
	 * </ul>
	 * <strong>Observação:</strong> Todos os atributos devem possuir get e set.
	 * 
	 * @param obj O objeto a ser convertido para json.
	 * @return O json do objeto do parâmetro.
	 */
	public static final String toJsonMask(Object obj) {
		try {
			return JSON_MASK_SERIALIZER_MAPPER.writeValueAsString(obj);
		} catch (Throwable e) {
			return "{ERRO:toJsonMask} :: " + e.getMessage();
		}
	}

	public static void main(String[] args) {
		class X {

			@StringMask
			private String value = "bla";

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return JsonUtil.toJsonMask(this);
			}
		}

		System.out.println(new X());
	}

}
