package com.lumar.googlecloud.service;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import com.lumar.googlecloud.service.GoogleSpringService;

public class ServletInitializer extends SpringBootServletInitializer {
	
	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(GoogleSpringService.class);
	  }

}
