import com.midland.config.MidlandConfig;
import com.midland.core.util.HttpUtils;
import com.midland.web.api.ApiHelper;
import com.midland.web.api.SmsSender.SmsClient;
import com.midland.web.api.SmsSender.SmsModel;
import com.midland.web.model.remote.Agent;
import com.midland.web.service.AppointmentService;
import com.midland.web.util.MidlandHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private ApiHelper apiHelper;
	
	@Test
	public void dsfs(){
		String SGIN = null;
		try {
			SGIN = URLEncoder.encode("美联房产查档","GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List list = new ArrayList();
		list.add("sdfef");
		list.add("dfef");
		list.add(SGIN);
		SmsModel smsModel = new SmsModel("18218727210","2029157",list);

		apiHelper.smsSender("dsfs",smsModel);
	}
	
	@Test
	public void test() throws IOException {
		String sb ="http://www.bizapp.com/api/SmsService/SendSMS?webKey=eb043d9ddf9e1a275755da3307cf80fe58bdeff635a595eb89293b8367989935&sessionId=&bizAppId=F0000006&customerId=test&sendType=1&userId=admin&phones=12467686&cont=NTQ2NTQ2NDU2&type=13&ucCode=";
		URL url = new URL(sb);
		StringBuffer sbs= new StringBuffer();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String line=null;
		while((line = in.readLine()) != null){
			sbs.append(line);
		}
		
		connection.disconnect();
		System.out.println(sbs.toString());
	}
	
	@Test
	public void getAgentFromRemote(){
		Map map = new HashMap<>();
		map.put("pageSize","10");
		map.put("pageNo","1");
		String data = HttpUtils.get(midlandConfig.getAgentPage(), map);
		List<Agent> list = MidlandHelper.getPojoList(data, Agent.class);
		System.out.println(data);
		
	}
	
	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("3332932@qq.com");
		message.setTo("977543176@qq.com");
		message.setSubject("主题：简单邮件1");
		message.setText("简单邮件内容1");
		apiHelper.emailSender("sendSimpleMail",message);
	}
	
}
