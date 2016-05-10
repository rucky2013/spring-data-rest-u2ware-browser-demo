package io.github.u2ware.browser.demo.onetoone.foo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import io.github.u2ware.browser.demo.basic.manual.Manual;

@Entity
public class Foo {

	
	@Id
	public Long id;
	
	public String fooValue;
	
	
	@OneToOne
	public Manual manual;
}
