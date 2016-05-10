package io.github.u2ware.browser.demo.basic.manual;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manual {

	@Id
	public Long id;
	
	public String name;
	public String value;
	
}
