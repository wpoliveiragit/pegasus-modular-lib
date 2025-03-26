package br.com.pegasus.module.smartannotation.domain.annotation.json.type.mask;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

/**
 * Adicione em um campo string, use o método
 * {@link br.com.pegasus.module.smartannotation.domain.util.JsonUtil#toStringMaskJson(Object)}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
public @interface StringMask {
}
