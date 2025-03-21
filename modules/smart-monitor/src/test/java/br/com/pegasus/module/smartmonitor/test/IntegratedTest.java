package br.com.pegasus.module.smartmonitor.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pegasus.module.smartmonitor.project.AnyAdvice;
import br.com.pegasus.module.smartmonitor.project.AnyService;
import br.com.pegasus.module.smartmonitor.project.MainAnyApplication;

@SpringBootTest(classes = MainAnyApplication.class)
public class IntegratedTest {

	@Test
	@DisplayName("TESTE DE INTEGRADO")
	public void test01(@Autowired AnyService anyService, @Autowired AnyAdvice anyAdvice) throws Exception {
		anyService.getCountDownLatch().await();
		for (boolean b : anyAdvice.getCheck()) {
			assertTrue(b);
		}
	}

//	@Test
//	@DisplayName("TESTE DE COBERTURA")
//	public void test02(@Autowired DefaultListableBeanFactory defaultListableBeanFactory) throws Exception {
//		//TESTE DE COBERTURA
//		new MonitorUtil();
//		assertDoesNotThrow(() -> MonitorUtil.invokeMethod(new Exception(), null, IntegratedTest.class.getMethods()[0]));
//		assertThrows(Exception.class, () -> MonitorUtil.createBean(new TestClass(), defaultListableBeanFactory));
//	}
//
//	@MonitorClassBean(IntegratedTest.class)
//	static class TestClass {
//	}

}
