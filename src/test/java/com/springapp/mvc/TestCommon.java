package com.springapp.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 不带spring容器的测试公共基类
 * @author yhm
 *
 */
public abstract class TestCommon {
	//日志处理：一般在子类当中使用
	protected static Logger logger= LoggerFactory.getLogger(TestCommon.class);
}
