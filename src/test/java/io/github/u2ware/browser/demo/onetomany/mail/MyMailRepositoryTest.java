package io.github.u2ware.browser.demo.onetomany.mail;

import java.io.IOException;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.u2ware.browser.demo.DemoApplication;
import io.github.u2ware.browser.demo.onetomany.mail.AttachedFile;
import io.github.u2ware.browser.demo.onetomany.mail.MyMail;
import io.github.u2ware.browser.demo.onetomany.mail.MyMailRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@Transactional
@Rollback(false)
public class MyMailRepositoryTest {

	private Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private MyMailRepository mailRepository;

	private ObjectMapper mapper = new ObjectMapper();
	@Test
	public void test1() throws IOException {
	

		AttachedFile f1 = new AttachedFile();
		f1.path = "f11";

		AttachedFile f2 = new AttachedFile();
		f2.path = "f12";

		MyMail m = new MyMail();
		m.mailFrom = "from1";
		m.mailTo = "to1";
		m.mailBody = "body1";
		
		m.addAttachedFile(f1);
		m.addAttachedFile(f2);
	
		logger.debug(mapper.writeValueAsString(m));

		mailRepository.save(m);

		Iterable<MyMail> it = mailRepository.findAll();
		for(MyMail i : it){
			logger.debug("test1 ## "+i);
			logger.debug(mapper.writeValueAsString(i));
		}
	}
	
	
	@Test
	public void test2() throws IOException {
		
		String content = "{\"mailFrom\":\"from2\",\"mailTo\":\"to2\",\"mailBody\":\"body2\",\"attachedFiles\":[{\"path\":\"f21\"},{\"path\":\"f22\"}]}";
	
		MyMail m = mapper.readValue(content, MyMail.class);

		mailRepository.save(m);

		Iterable<MyMail> it = mailRepository.findAll();
		for(MyMail i : it){
			logger.debug("test2 ## "+i);
		}
	}
	
	@Test
	public void test3() throws IOException {

		
		AttachedFile f3 = new AttachedFile();
		f3.path = "f31";

		AttachedFile f4 = new AttachedFile();
		f4.path = "f32";

		MyMail m = mailRepository.findOne(new Long(1));
		m.mailBody = "body3";
		m.removeAttechementFiles();
		m.addAttachedFile(f3);
		m.addAttachedFile(f4);

		logger.debug(mapper.writeValueAsString(m));
		mailRepository.save(m);
		

		
		Iterable<MyMail> it = mailRepository.findAll();
		for(MyMail i : it){
			logger.debug("test3 ## "+i);
		}

	}
	
	@Test
	public void test4() throws IOException {

		MyMail m = mailRepository.findOne(new Long(2));
		
		mailRepository.delete(m);
		
		Iterable<MyMail> it = mailRepository.findAll();
		for(MyMail i : it){
			logger.debug("test4 ## "+i);
		}
	}
	
	
}