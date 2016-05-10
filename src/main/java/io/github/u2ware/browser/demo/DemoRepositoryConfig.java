package io.github.u2ware.browser.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import io.github.u2ware.browser.demo.basic.manual.Manual;
import io.github.u2ware.browser.demo.onetoone.foo.Foo;

@Configuration
public class DemoRepositoryConfig extends RepositoryRestMvcConfiguration{

	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Manual.class);
		config.exposeIdsFor(Foo.class);
		//config.setBasePath("/apis");
	}
	
}
