package io.github.u2ware.browser.demo.basic.auto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Auto {

	
	@Id @GeneratedValue
	public Long id;
	
	public String name;
	public String value;
	
	
}
