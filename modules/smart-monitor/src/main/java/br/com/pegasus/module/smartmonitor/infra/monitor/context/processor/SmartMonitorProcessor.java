package br.com.pegasus.module.smartmonitor.infra.monitor.context.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.Enhancer;

import br.com.pegasus.module.smartmonitor.domain.adapter.SmartMonitorAdapter;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.SmartMonitorInterceptor;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.factory.LogPluginFactory;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.factory.MonitorAdvicePluginFactory;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.advice.MonitorClassAdvice;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.AfterLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.BeforeAfterLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.BeforeLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.ExceptionLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.GlobalLog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SmartMonitorProcessor implements SmartMonitorAdapter {

	private final DefaultListableBeanFactory defaultListableBeanFactory;
	private final Map<Object, SmartMonitorInterceptor> handlerMap = new HashMap<>(1);

	public Object filterAdvice(Object bean, String beanName) {
		SmartMonitorInterceptor monitorInterceptor = handlerMap.computeIfAbsent(bean, SmartMonitorInterceptor::new);
		MonitorAdvicePluginFactory.createMonitorAdvicePlugin(bean, defaultListableBeanFactory, monitorInterceptor);
		return createEnhancer(bean);
	}

	public boolean hasAdviceQualities(Object bean) {
		return bean.getClass().isAnnotationPresent(MonitorClassAdvice.class);
	}

	@Override
	public Object filterLog(Object bean, String beanName) {
		SmartMonitorInterceptor monitorInterceptor = handlerMap.computeIfAbsent(bean, SmartMonitorInterceptor::new);

		// CRIA O MAP de coleta de anotações
		Map<Class<? extends Annotation>, List<Method>> methodMap = Map.of(//
				BeforeLog.class, new ArrayList<>(), //
				AfterLog.class, new ArrayList<>(), //
				ExceptionLog.class, new ArrayList<>(), //
				BeforeAfterLog.class, new ArrayList<>(), //
				GlobalLog.class, new ArrayList<>()//
		);

		// adiciona cada metodo para cada lista das anotações correspondente
		for (Method method : bean.getClass().getDeclaredMethods()) {
			methodMap.keySet().forEach(annotation -> {
				if (method.isAnnotationPresent(annotation)) {
					methodMap.get(annotation).add(method);
				}
			});
		}

		// add cada listas no invoke do monitor correspondente
		methodMap.forEach((annotation, list) -> {
			if (!list.isEmpty()) {
				switch (AnnotationLog.getType(annotation)) {
				case BEFORE -> LogPluginFactory.createBeforeMethodLogPlugin(monitorInterceptor, bean, list);
				case AFTER -> LogPluginFactory.createAfterMethodLogPlugin(monitorInterceptor, bean, list);
				case EXCEPTION -> LogPluginFactory.createExceptionMethodLogPlugin(monitorInterceptor, bean, list);
				case BEFORE_AFTER -> LogPluginFactory.createBeforeAfterMethodLogPlugin(monitorInterceptor, bean, list);
				case GLOBAL -> LogPluginFactory.createAllMethodLogPlugin(monitorInterceptor, bean, list);
				default -> System.out.println("Tipo de log desconhecido.");// trocar por log
				}
			}
		});
		// veridica se existe ao menos um elemento em uma das listas
		return createEnhancer(bean);
	}

	public boolean hasLogQualities(Object bean) {
		// cria lista de anotações
		List<Class<? extends Annotation>> annotationList = Arrays.asList(//
				BeforeLog.class, AfterLog.class, ExceptionLog.class, BeforeAfterLog.class, GlobalLog.class);

		// Verifica se algum método possui alguma anotação de log
		return Arrays.stream(bean.getClass().getDeclaredMethods())//
				.anyMatch(method -> annotationList.stream().anyMatch(method::isAnnotationPresent));
	}

	private Object createEnhancer(Object bean) {
		SmartMonitorInterceptor monitorInterceptor = handlerMap.get(bean);
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(bean.getClass());
		enhancer.setCallback(monitorInterceptor);
		return enhancer.create();
	}

	private enum AnnotationLog {
		BEFORE(BeforeLog.class), //
		AFTER(AfterLog.class), //
		EXCEPTION(ExceptionLog.class), //
		BEFORE_AFTER(BeforeAfterLog.class), //
		GLOBAL(GlobalLog.class), //
		UNKNOWN(null); // Valor padrão para casos não mapeados

		@Getter
		private final Class<? extends Annotation> value;

		private AnnotationLog(Class<? extends Annotation> value) {
			this.value = value;
		}

		public static AnnotationLog getType(Class<? extends Annotation> value) {
			for (AnnotationLog type : values()) {
				if (type.value == value) {
					return type;
				}
			}
			return UNKNOWN;
		}
	}

}
