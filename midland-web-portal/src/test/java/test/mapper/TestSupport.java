package test.mapper;

import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * TestSupport : Spring测试支持,用于测试由Spring 管理的bean,编写测试类时,继承该类
 *
 * @author 
 * @since 2016年5月18日 下午2:28:58
 */
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestSupport extends AbstractJUnit4SpringContextTests {
	
    private long beginTime;
    private long endTime;
    
    @Before
    public void testBefore(){
    	beginTime = System.currentTimeMillis();
    	logger.info("begin time: "+System.currentTimeMillis());
    }
    @After
    public void testAfter(){
    	endTime = System.currentTimeMillis();
    	logger.info("end time: "+endTime);
    	logger.info("run time: "+(System.currentTimeMillis()-beginTime));
    }
    
}