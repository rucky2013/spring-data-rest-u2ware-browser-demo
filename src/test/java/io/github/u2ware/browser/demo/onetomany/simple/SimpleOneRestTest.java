package io.github.u2ware.browser.demo.onetomany.simple;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.github.u2ware.browser.demo.DemoApplication;
import io.github.u2ware.browser.demo.onetomany.simple.SimpleMany;
import io.github.u2ware.browser.demo.onetomany.simple.SimpleOne;
import io.github.u2ware.browser.demo.onetomany.simple.SimpleOneRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class SimpleOneRestTest {

	private Log logger = LogFactory.getLog(getClass());
	
	private @Autowired WebApplicationContext context;
	private MockMvc mvc;
	@Autowired
	private SimpleOneRepository sampleOneRest;
	
	@Before
	public void setUp(){
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build() ;
		
		
		
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void test1() throws Exception {


		SimpleMany many1 = new SimpleMany();
		many1.setAttr("test11");

		SimpleMany many2 = new SimpleMany();
		many2.setAttr("test12");

		SimpleOne one = new SimpleOne();
		one.setName("test1");
		one.addSimpleMany(many1);
		one.addSimpleMany(many2);
	
		sampleOneRest.save(one);
		
		
		
	}
	@Test
	@Transactional
	public void test2() throws Exception {

		Iterable<SimpleOne> it = sampleOneRest.findAll();
		for(SimpleOne i : it){
			logger.debug("test2 ## "+i);
		}
	}
	
	@Test
	public void test3() throws Exception {

		this.mvc.perform(
				get("/profile/simpleOnes")
				.header("Accept", "application/schema+json")
			).andDo(
				print()
			).andExpect(
				status().is2xxSuccessful()
			);
		
		this.mvc.perform(
				get("/profile/simpleOnes")
			).andDo(
				print()
			).andExpect(
				status().is2xxSuccessful()
			);
		

		this.mvc.perform(
				get("/simpleOnes/1")
			).andDo(
				print()
			).andExpect(
				status().is2xxSuccessful()
			);
	}
	
}
