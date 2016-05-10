package io.github.u2ware.browser.demo.onetomany.mail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class MyMail {

	@Id @GeneratedValue
	public Long id;
	public String mailFrom;
	public String mailTo;
	public String mailBody;
	
	
	@OneToMany(mappedBy="id.myMail", fetch=FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval=true)
	private List<AttachedFile> attachedFiles = new ArrayList<AttachedFile>();

	public List<AttachedFile> getAttachedFiles() {
		return attachedFiles;
	}
	public void setAttachedFiles(List<AttachedFile> attachedFiles) {
		if(attachedFiles == null) return;		
		removeAttechementFiles();
		for(AttachedFile attachedFile : attachedFiles){
			addAttachedFile(attachedFile);
		}
	}
	
	public void addAttachedFile(AttachedFile attachedFile) {
		if(attachedFile == null) return;
		if(attachedFile.id == null){
			attachedFile.id = new AttachedFileId();
		}
		attachedFile.id.id = new Long(this.attachedFiles.size());
		attachedFile.id.myMail = this;
		this.attachedFiles.add(attachedFile);
	}
	public void removeAttechementFiles() {
		this.attachedFiles.clear();
	}

	@PrePersist
	private void prePersist() throws JsonProcessingException{
		System.out.println(new ObjectMapper().writeValueAsString(this));
		System.out.println(new ObjectMapper().writeValueAsString(this));
	}
	@PostPersist
	private void postPersist() throws JsonProcessingException{
		System.out.println(new ObjectMapper().writeValueAsString(this));
		System.out.println(new ObjectMapper().writeValueAsString(this));
	}
	@PreUpdate
	private void preUpdate() throws JsonProcessingException{
		System.out.println(new ObjectMapper().writeValueAsString(this));
		System.out.println(new ObjectMapper().writeValueAsString(this));
		System.out.println(new ObjectMapper().writeValueAsString(this));
	}
	
	
	
	@Override
	public String toString() {
		return "MyMail [id=" + id + ", mailFrom=" + mailFrom + ", mailTo=" + mailTo + ", mailBody=" + mailBody
				+ ", attachedFiles=" + attachedFiles + "]";
	}
}
