import com.midland.config.MidlandConfig;
import com.midland.web.SmsSender.SmsClient;
import com.midland.web.SmsSender.SmsModel;
import com.midland.web.service.AppointmentService;
import org.jdom.JDOMException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

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
	@Autowired
	private SmsClient smsClient;
	
	@Test
	public void dsfs(){
		SmsModel smsModel = new SmsModel();
		smsModel.setSendType(1);
		smsModel.setCont("dsfdf");
		smsModel.setPhones("13489475845");
		try {
			smsClient.execute(smsModel);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
