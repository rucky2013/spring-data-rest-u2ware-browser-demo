package io.github.u2ware.browser.demo.onetomany.simple;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SimpleMany {

	@Id @GeneratedValue
	private Long id;
	private String attr;
	private String prop;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private SimpleOne simpleOne;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	public SimpleOne getSimpleOne() {
		return simpleOne;
	}
	public void setSimpleOne(SimpleOne simpleOne) {
		this.simpleOne = simpleOne;
	}
	@Override
	public String toString() {
		return "SampleMany [id=" + id + ", attr=" + attr + ", prop=" + prop +  "]";
	}
}