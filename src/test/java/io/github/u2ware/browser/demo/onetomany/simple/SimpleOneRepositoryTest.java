package io.github.u2ware.browser.demo.onetomany.simple;

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
import io.github.u2ware.browser.demo.onetomany.simple.SimpleMany;
import io.github.u2ware.browser.demo.onetomany.simple.SimpleOne;
import io.github.u2ware.browser.demo.onetomany.simple.SimpleOneRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@Transactional
@Rollback(false)
public class SimpleOneRepositoryTest {

	private Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SimpleOneRepository sampleOneRest;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test1() throws IOException {
	

		SimpleMany many1 = new SimpleMany();
		many1.setAttr("test11");

		SimpleMany many2 = new SimpleMany();
		many2.setAttr("test12");

		SimpleOne one = new SimpleOne();
		one.setName("test1");
		one.addSimpleMany(many1);
		one.addSimpleMany(many2);
	
		logger.debug(mapper.writeValueAsString(one));
		sampleOneRest.save(one);

		Iterable<SimpleOne> it = sampleOneRest.findAll();
		for(SimpleOne i : it){
			logger.debug("test1 ## "+i);
			logger.debug(mapper.writeValueAsString(i));
		}
		
	}
	
	@Test
	public void test2() throws IOException {
		
		String content = "{\"name\":\"test2\",\"simpleManies\":[{\"attr\":\"test21\"},{\"attr\":\"test22\"}]}";
		SimpleOne one = mapper.readValue(content, SimpleOne.class);

		sampleOneRest.save(one);

		Iterable<SimpleOne> it = sampleOneRest.findAll();
		for(SimpleOne i : it){
			logger.debug("test2 ## "+i);
		}
	}
	
	
	
	@Test
	public void test3() throws IOException {

		
		SimpleMany many1 = new SimpleMany();
		many1.setProp("test31");

		SimpleMany many2 = new SimpleMany();
		many2.setProp("test32");

		SimpleOne one = sampleOneRest.findOne(new Long(1));
		one.removeSimpleManies();
		one.addSimpleMany(many1);
		one.addSimpleMany(many2);
		
		
		one.setValue("test3");

		logger.debug(mapper.writeValueAsString(one));
		sampleOneRest.save(one);
		
		
		
		Iterable<SimpleOne> it = sampleOneRest.findAll();
		for(SimpleOne i : it){
			logger.debug("test3 ## "+i);
		}
	}

	@Test
	public void test4() throws IOException {

		SimpleOne one = sampleOneRest.findOne(new Long(2));
		
		sampleOneRest.delete(one);
		
		Iterable<SimpleOne> it = sampleOneRest.findAll();
		for(SimpleOne i : it){
			logger.debug("test3 ## "+i);
		}
	}
	
	
}