package br.com.pegasus.module.smartannotation.domain.annotation.json.serializer;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import br.com.pegasus.module.smartannotation.domain.annotation.json.type.mask.StringMask;

public class IntrospectorMaskSerializer extends JacksonAnnotationIntrospector {
	private static final long serialVersionUID = 1L;

	@Override
	public Object findSerializer(Annotated annotated) {

		List<Class<? extends Annotation>> listAnnotation = Arrays.asList(//
				StringMask.class);

		for (Class<? extends Annotation> ann : listAnnotation) {
			if (annotated.hasAnnotation(ann)) {
				return new JsonSerializerMask();
			}
		}
		return null;// Ignora todas as outras anotações
	}

	@Override
	public boolean isAnnotationBundle(Annotation ann) {
		return false; // Evita processar bundles de anotações como @JacksonAnnotationsInside, etc.
	}

}
