package com.zelinskiyrk.store;

import com.zelinskiyrk.store.base.config.SessionEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(
		basePackageClasses =
				{
						SessionEventListener.class
				}
)
@SpringBootApplication
public class StoreRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreRestApiApplication.class, args);
	}

}
