package com.springapp.mvc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * 带启动spring容器的测试类的公共基类主要用于以下用途
 * 1.测试类需要使用spring容器注入对象时必须加载spring容器
 * 2.在测试类中需要使用日志工具输出提示信息
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:application-context.xml", "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
@SuppressWarnings("SpringJavaAutowiringInspection")
public abstract class TestAbstract extends TestCommon {
	
	//Web上下文环境
	@Autowired
	private WebApplicationContext wac;
	
	//mvc模拟对象
	protected MockMvc mockMvc;

	@Autowired
	MockServletContext mockServletContext;

	@Autowired
	MockServletContext servletContext; // cached

	@Autowired
	MockHttpSession session;

	@Autowired
	MockHttpServletRequest request;

	@Autowired
	MockHttpServletResponse response;


	@Autowired
	ServletWebRequest webRequest;

	@Before
	public void beforeClass() throws Exception {
		assertNotNull(wac);
		logger.debug("测试结果");
		mockMvc = webAppContextSetup(wac).build();
	}
}
