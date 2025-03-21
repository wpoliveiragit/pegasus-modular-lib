package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.factory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.MonitorInterceptor;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior.AfterLogBehavior;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior.BeforeAfterLogBehavior;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior.BeforeLogBehavior;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior.ExceptionLogBehavior;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior.GlobalLogBehavior;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.AfterLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeAfterLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.ExceptionLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.GlobalLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin.AfterLogPlugin;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin.BeforeLogPlugin;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin.ExceptionLogPlugin;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.type.AttributeLogType;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.util.LogMethodUtil;

public final class LogPluginFactory {

	public static void createBeforeMethodLogPlugin(MonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> map = LogMethodUtil.getMethodMap(methodList, BeforeLog.class, AttributeLogType.BEFORE);
		monitorInterceptor.add(new BeforeLogPlugin(bean, new BeforeLogBehavior(), map));
	}

	public static void createAfterMethodLogPlugin(MonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> map = LogMethodUtil.getMethodMap(methodList, AfterLog.class, AttributeLogType.AFTER);
		monitorInterceptor.add(new AfterLogPlugin(bean, new AfterLogBehavior(), map));
	}

	public static void createExceptionMethodLogPlugin(MonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> map = LogMethodUtil.getMethodMap(methodList, ExceptionLog.class, AttributeLogType.EXCEPTION);
		monitorInterceptor.add(new ExceptionLogPlugin(bean, new ExceptionLogBehavior(), map));
	}

	public static void createBeforeAfterMethodLogPlugin(MonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> beMap = LogMethodUtil.getMethodMap(methodList, BeforeAfterLog.class, BeforeLog.class,
				AttributeLogType.BEFORE);
		Map<Method, String> afMap = LogMethodUtil.getMethodMap(methodList, BeforeAfterLog.class, AfterLog.class,
				AttributeLogType.AFTER);
		monitorInterceptor.add(new BeforeLogPlugin(bean, new BeforeAfterLogBehavior(), beMap));
		monitorInterceptor.add(new AfterLogPlugin(bean, new BeforeAfterLogBehavior(), afMap));
	}

	public static void createAllMethodLogPlugin(MonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> beMap = LogMethodUtil.getMethodMap(methodList, GlobalLog.class, BeforeLog.class,
				AttributeLogType.BEFORE);
		Map<Method, String> afMap = LogMethodUtil.getMethodMap(methodList, GlobalLog.class, AfterLog.class,
				AttributeLogType.AFTER);
		Map<Method, String> exMap = LogMethodUtil.getMethodMap(methodList, GlobalLog.class, ExceptionLog.class,
				AttributeLogType.EXCEPTION);
		monitorInterceptor.add(new BeforeLogPlugin(bean, new GlobalLogBehavior(), beMap));
		monitorInterceptor.add(new AfterLogPlugin(bean, new GlobalLogBehavior(), afMap));
		monitorInterceptor.add(new ExceptionLogPlugin(bean, new GlobalLogBehavior(), exMap));
	}

}
