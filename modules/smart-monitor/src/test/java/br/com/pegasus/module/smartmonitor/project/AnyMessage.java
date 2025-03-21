package br.com.pegasus.module.smartmonitor.project;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnyMessage {

	private final AnyService anyService;

	@Scheduled(fixedRate = 500)
	public void monitoring() throws Exception {
		anyService.monitorMethod();
	}

}