package br.com.pegasus.module.smartmonitor.infra.monitor.context.factory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import br.com.pegasus.module.smartmonitor.infra.monitor.context.SmartMonitorInterceptor;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.AfterLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.BeforeAfterLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.BeforeLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.ExceptionLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.GlobalLog;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior.AfterLogBehavior;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior.BeforeAfterLogBehavior;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior.BeforeLogBehavior;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior.ExceptionLogBehavior;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior.GlobalLogBehavior;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.plugin.AfterLogPlugin;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.plugin.BeforeLogPlugin;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.plugin.ExceptionLogPlugin;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.type.AttributeLogType;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.util.LogMethodUtil;

public final class LogPluginFactory {

	public static void createBeforeMethodLogPlugin(SmartMonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> map = LogMethodUtil.getMethodMap(methodList, BeforeLog.class, AttributeLogType.BEFORE);
		monitorInterceptor.add(new BeforeLogPlugin(bean, new BeforeLogBehavior(), map));
	}

	public static void createAfterMethodLogPlugin(SmartMonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> map = LogMethodUtil.getMethodMap(methodList, AfterLog.class, AttributeLogType.AFTER);
		monitorInterceptor.add(new AfterLogPlugin(bean, new AfterLogBehavior(), map));
	}

	public static void createExceptionMethodLogPlugin(SmartMonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> map = LogMethodUtil.getMethodMap(methodList, ExceptionLog.class,
				AttributeLogType.EXCEPTION);
		monitorInterceptor.add(new ExceptionLogPlugin(bean, new ExceptionLogBehavior(), map));
	}

	public static void createBeforeAfterMethodLogPlugin(SmartMonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		Map<Method, String> beMap = LogMethodUtil.getMethodMap(methodList, BeforeAfterLog.class, BeforeLog.class,
				AttributeLogType.BEFORE);
		Map<Method, String> afMap = LogMethodUtil.getMethodMap(methodList, BeforeAfterLog.class, AfterLog.class,
				AttributeLogType.AFTER);
		monitorInterceptor.add(new BeforeLogPlugin(bean, new BeforeAfterLogBehavior(), beMap));
		monitorInterceptor.add(new AfterLogPlugin(bean, new BeforeAfterLogBehavior(), afMap));
	}

	public static void createAllMethodLogPlugin(SmartMonitorInterceptor monitorInterceptor, Object bean,
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
