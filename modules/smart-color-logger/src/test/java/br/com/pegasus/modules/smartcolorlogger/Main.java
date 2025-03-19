package br.com.pegasus.modules.smartcolorlogger;

import br.com.pegasus.modules.smartcolorlog.logger.SmartColorLogger;

public class Main {

	public static void main(String[] args) {
		SmartColorLogger log = new SmartColorLogger();
		log.info("Mensagem de INFO");
		log.warn("Mensagem de WARNING");
		log.error("Mensagem de ERROR");
	}

}
