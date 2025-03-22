package br.com.pegasus.module.smartmonitor.resource.logger.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import br.com.pegasus.module.smartmonitor.resource.logger.behavior.LogBehavior;
import br.com.pegasus.module.smartmonitor.resource.logger.log.AfterLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.BeforeAfterLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.BeforeLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.ExceptionLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.GlobalLog;
import br.com.pegasus.module.smartmonitor.resource.logger.util.LogMsgPatternUtil;

public abstract class BaseLogPlugin {

	protected final Map<Method, String> methodMap;
	protected final LogBehavior methodLog;
	protected final Logger log;

	public BaseLogPlugin(Map<Method, String> methodMap, LogBehavior methodLog, Logger log) {

		checkMethod(methodMap.keySet());
		this.methodMap = methodMap;
		this.methodLog = methodLog;
		this.log = log;
	}

	// verifica redundancias
	private static void checkMethod(Set<Method> methodSet) {
		methodSet.forEach(method -> {

			boolean isGlobalLogPresent = method.isAnnotationPresent(GlobalLog.class);
			boolean isBeforeAfterLogPresent = method.isAnnotationPresent(BeforeAfterLog.class);
			boolean isBeforeLogPresent = method.isAnnotationPresent(BeforeLog.class);
			boolean isAfterLogPresent = method.isAnnotationPresent(AfterLog.class);
			boolean isExceptionLogPresent = method.isAnnotationPresent(ExceptionLog.class);

			// CHECK GLOBAL LOG
			if (isGlobalLogPresent && (isBeforeLogPresent || isAfterLogPresent || isExceptionLogPresent)) {
				throw new RuntimeException(LogMsgPatternUtil.CHECK_METHOD_GLOBAL.getFormat(method.getName(),
						GlobalLog.class.getSimpleName(), BeforeLog.class.getSimpleName(),
						AfterLog.class.getSimpleName(), ExceptionLog.class.getSimpleName()));
			}
			// CHECK BEFORE AFTER
			if (isBeforeAfterLogPresent && (isBeforeLogPresent || isAfterLogPresent)) {
				throw new RuntimeException(LogMsgPatternUtil.CHECK_METHOD_BEFORE_AFTER.getFormat(method.getName(),
						BeforeAfterLog.class.getSimpleName(), BeforeLog.class.getSimpleName(),
						AfterLog.class.getSimpleName()));
			}
		});

	}

}
