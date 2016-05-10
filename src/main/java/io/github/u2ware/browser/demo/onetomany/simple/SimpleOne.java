package io.github.u2ware.browser.demo.onetomany.simple;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class SimpleOne {

	@Id @GeneratedValue
	private Long id;
	private String name;
	private String value;
	
	@OneToMany(mappedBy="simpleOne", fetch=FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval=true)
	private Set<SimpleMany> simpleManies = new HashSet<SimpleMany>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Set<SimpleMany> getSimpleManies() {
		return simpleManies;
	}
	public void setSimpleManies(Set<SimpleMany> simpleManies) {
		if(simpleManies == null) return;		
		removeSimpleManies();
		for(SimpleMany simpleMany : simpleManies){
			addSimpleMany(simpleMany);
		}
	}
	
	public void addSimpleMany(SimpleMany simpleMany) {
		if(simpleMany == null) return;
		simpleMany.setSimpleOne(this);
		this.simpleManies.add(simpleMany);
	}
	public void removeSimpleManies() {
		this.simpleManies.clear();
	}
	
	
	@PrePersist
	private void prePersist() throws JsonProcessingException{
		System.out.println(new ObjectMapper().writeValueAsString(this));
	}
	@PreUpdate
	private void preUpdate() throws JsonProcessingException{
		System.out.println(new ObjectMapper().writeValueAsString(this));
	}
	@Override
	public String toString() {
		return "SampleOne [id=" + id + ", name=" + name + ", value=" + value + ", simpleManies=" + simpleManies + "]";
	}
	
}
