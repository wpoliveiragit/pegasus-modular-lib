//package br.com.pegasus.module.toolkit;
//
//import java.util.Arrays;
//import java.util.Map;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@RequiredArgsConstructor
//@Component
//public class StartupLogger implements ApplicationRunner {
//
//	private final Environment env;
//	private final ApplicationContext applicationContext;
//	private String LOCALHOST = "localhost";
//
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//
//		// IMPRESSÃO DA TABELA
//		final boolean isLocalHost = (LOCALHOST.equals(env.getProperty("server.address", LOCALHOST)));
//
//		log.info("# ## INFO ##");
//		logGeral(isLocalHost, args);
//		logController(isLocalHost);
//		logDataBase(isLocalHost);
//		log.info("#");
//		log.info("# ##########");
//	}
//
//	private void logGeral(boolean isLocalHost, ApplicationArguments args) {
//		if (isLocalHost) {
//			String port = env.getProperty("server.port", "8080");
//			log.info("#");
//			log.info("# [GERAL]");
//			log.info("# - Porta {}", port);
//			log.info("# - Protocolo {}", env.getProperty("server.address", LOCALHOST));
//			args.getNonOptionArgs().forEach(e -> log.info("# - NonOptionalArgs: " + e));
//			args.getOptionNames().forEach(e -> log.info("# - OptionalNames: " + e));
//			Arrays.stream(args.getSourceArgs()).forEach(e -> log.info("# - SourceArgs: " + e));
//		}
//	}
//
//	private void logController(boolean isLocalHost) {
//		if (isLocalHost) {
//			Map<RequestMappingInfo, HandlerMethod> handlerMethods = applicationContext
//					.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
//			if (!handlerMethods.isEmpty()) {
//				log.info("#");
//				log.info("# [Controller]");
//				handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
//					log.info("# - Endpoint: " + requestMappingInfo);
//					log.info("# - Handler: " + handlerMethod);
//				});
//			}
//		}
//	}
//
//	private void logDataBase(boolean isLocalHost) {
//		if (isLocalHost) {
//			final String address = env.getProperty("server.address", LOCALHOST);
//			String porta = env.getProperty("server.port", "8080");
//			String path = env.getProperty("spring.h2.console.path", "/h2-console");
//
//			String username = env.getProperty("spring.datasource.username");
//			String password = env.getProperty("spring.datasource.password");
//			String url = env.getProperty("spring.datasource.url");
//			log.info("#");
//			log.info("# [BANCO]");
//			log.info("# - {}:{}{}", address, porta, path);
//			log.info("# - Url {{}}", url);
//			log.info("# - Username {{}}", username);
//			log.info("# - Password {{}}", password);
//		}
//	}
//
//}
