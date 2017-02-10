package cn.paypalm.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.paypalm.sse.config.SseConfig;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月8日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月8日	新建文件.
 * 
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SseConfig.class})
public class TestJunit {
	
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext wac;

	@Autowired
	MockHttpSession session;
	
	@Autowired
	MockHttpServletRequest request;
	
	@Before
	public void setup(){
		mockMvc=MockMvcBuilders.standaloneSetup(wac).build();
	}
	
	@Test
	public void testNomal() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/index"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("index"))
		.andExpect(MockMvcResultMatchers.forwardedUrl("/index.jsp"))
		.andExpect(MockMvcResultMatchers.model().attribute("msg", ""));
	}
}
