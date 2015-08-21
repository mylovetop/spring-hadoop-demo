package com.springapp.mvc;

import org.junit.*;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class NoticeControllerTest extends TestAbstract {

	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(mockMvc);
	}

	@After
	public void tearDown() throws Exception {
	}
	/**
	 * 测试控制器请求
	 * @throws Exception
	 */
	@Test
	public void testSetReaded1() throws Exception {
		mockMvc.perform((post("/noticeManage.html").param("memberId", "").param("noticeId", "")))  
        	   .andExpect(status().isOk()).andDo(print());
	}
	/**
	 * 测试控制器请求
	 * Servlet/JSP Mock、MockMvcBuilder、MockMvc、RequestBuilder、ResultMatcher、ResultHandler、MvcResult等。
	 * 另外提供了几个静态工厂方法便于测试：在使用时请使用静态方法导入方便测试
	 * MockMvcBuilders
	 * MockMvcRequestBuilders
	 * MockMvcResultMatchers
	 * MockMvcResultHandlers
	 * DefaultMvcResult
	 * 	1、mockMvc.perform执行一个请求；
		2、MockMvcRequestBuilders.get("/user/1")构造一个请求
		3、ResultActions.andExpect添加执行完成后的断言
		4、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
		5、ResultActions.andReturn表示执行完成后返回相应的结果。
	 * @throws Exception
	 */
	@Test
	public void testSetReaded2() throws Exception {
		MvcResult result=mockMvc.perform((get("/noticeManage.html").param("memberId", "1").param("noticeId", "1")))
        	   .andExpect(status().isOk()).andDo(print())
        	   //.andExpect(view().name("user/view"))  
        	   //.andExpect(model().attributeExists("user"))  
        	   //.andExpect(jsonPath("{}").exists())
        	   .andReturn();
		assertNotNull(result);
	}
}
