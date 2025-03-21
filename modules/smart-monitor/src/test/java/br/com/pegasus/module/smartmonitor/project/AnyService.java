package br.com.pegasus.module.smartmonitor.project;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Service;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.MonitorClassAdvice;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.MonitorMethodAdvice;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.AfterLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeAfterLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.ExceptionLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.GlobalLog;
import lombok.Getter;

@Service
@MonitorClassAdvice(AnyAdvice.class)
public class AnyService {

	private static final String MSG_EXCEPTION = "<monitor> - case %d> ERRO: %s";
	private @Getter CountDownLatch countDownLatch;
	private static int i = 0;

	public AnyService() {
		countDownLatch = new CountDownLatch(1);
	}

	@GlobalLog(//
			before = "Log: INICIO DO MÉTODO", //
			after = "Log: MÉTHODO FINALIZADO COM SUCESSO", //
			exception = "Log: OCORREU UMA FALHA NO PROCESSO")
	@MonitorMethodAdvice
	public void monitorMethod() throws Exception {

		switch (++i) {
		case 1 -> throw new Exception(sFormat(MSG_EXCEPTION, i, Exception.class));
		case 2 -> throw new RuntimeException(sFormat(MSG_EXCEPTION, i, RuntimeException.class));
		case 3 -> throw new IOException(sFormat(MSG_EXCEPTION, i, IOException.class));
		case 4 -> throw new AnyException(sFormat(MSG_EXCEPTION, i, AnyException.class));
		case 5 -> throw new AnyException(sFormat(MSG_EXCEPTION, i, AnyException.class), "");
		case 6 -> throw new ArithmeticException(sFormat(MSG_EXCEPTION, i, ArithmeticException.class));
		default -> countDownLatch.countDown();
		}
		System.out.println("\n\n");
		beforeMethod();

		System.out.println("\n\n");
		afterMethod();
		
		System.out.println("\n\n");
		beforeAfterMethod();
		
		System.out.println("\n\n");
		exception();
		i = 0;
		System.out.println("\n\n");
	}

	private static final String sFormat(String msg, int index, Class<?> clazz) {
		return String.format(msg, index, clazz.getSimpleName());
	}

	@BeforeLog
	public void beforeMethod() {
		System.out.println("beforeMethod");
	}

	@AfterLog
	public void afterMethod() {
		System.out.println("afterMethod");
	}

	@BeforeAfterLog
	public void beforeAfterMethod() {
		System.out.println("beforeAfterMethod");
	}

	@ExceptionLog
	public void exception() {
		throw new RuntimeException(sFormat(MSG_EXCEPTION, i, RuntimeException.class));
	}

}
