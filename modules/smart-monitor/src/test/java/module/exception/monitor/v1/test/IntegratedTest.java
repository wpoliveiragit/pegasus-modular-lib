package module.exception.monitor.v1.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import module.exception.monitor.v1.project.AnyAdvice;
import module.exception.monitor.v1.project.AnyService;
import module.exception.monitor.v1.project.MainAnyApplication;

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

}
