package com.cg.tms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.cg.tms.packagems.ui.*;

@SpringBootApplication
public class TmsApplication {

	private static final Logger Log = LoggerFactory.getLogger(TmsApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(TmsApplication.class, args);
		PackageUi packageUi = context.getBean(PackageUi.class);
		packageUi.start();

		Log.debug("i am logged using debug level");
		Log.info("i am logged using info level");
		Log.error("i am logged using error level");
	}

}
