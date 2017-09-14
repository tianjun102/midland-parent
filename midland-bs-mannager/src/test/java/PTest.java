import com.alibaba.fastjson.JSONArray;
import com.midland.config.MidlandConfig;
import com.midland.web.model.Appointment;
import com.midland.web.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 'ms.x' on 2017/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("all")
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
public class PTest {
	
	@Autowired
	private AppointmentService appointmentServiceImpl;
	@Autowired
	private MidlandConfig midlandConfig;
	
	@Test
	public void dsfs(){
		System.out.println(midlandConfig.getLoginUrl());
		System.out.println("1111111111111111111111111111");
	}
}
