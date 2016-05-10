package io.github.u2ware.browser.demo.onetoone.bar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import io.github.u2ware.browser.demo.basic.auto.Auto;

@Entity
public class Bar {

	
	@Id @GeneratedValue
	public Long id;

	public String barValue;
	
	@OneToOne
	public Auto auto;
	
}
