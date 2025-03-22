package br.com.pegasus.module.smartmonitor.resource.logger.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.pegasus.module.smartmonitor.resource.logger.type.AttributeLogType;

public final class LogMethodUtil {

	public static final Map<Method, String> getMethodMap(List<Method> methodList, Class<?> annMethod,
			AttributeLogType annFieldName) {
		return getMethodMap(methodList, annMethod, annMethod, annFieldName);
	}

	public static final Map<Method, String> getMethodMap(List<Method> methodList, Class<?> annMethod,
			Class<?> caninicAnn, AttributeLogType annField) {

		return methodList.stream().collect(Collectors.toMap(//
				method -> method, //
				method -> {
					String msg = getAnnotationValue(method, annMethod, annField);
					return LogMsgPatternUtil.CREATE_MSG_LOG.getFormat(caninicAnn.getSimpleName(), method.getName(),
							msg);
				}));
	}

	private static String getAnnotationValue(Method method, Class<?> annotationClass, AttributeLogType annField) {
		Object annotation = method.getAnnotation(annotationClass.asSubclass(Annotation.class));
		if (annotation == null) {
			throw new RuntimeException(
					LogMsgPatternUtil.GET_ANNOTATION_VALUE_NULL.getFormat(annotationClass.getSimpleName()));
		}

		try {
			return (String) annotationClass.getMethod(annField.getValue()).invoke(annotation);
		} catch (Exception ex) {
			throw new RuntimeException(LogMsgPatternUtil.GET_ANNOTATION_VALUE_NOT_FOUND.getFormat(annField.getValue(),
					annotationClass.getSimpleName()));
		}
	}

}
