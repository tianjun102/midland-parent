import com.alibaba.fastjson.JSONArray;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.midland.config.MidlandConfig;
import com.midland.core.util.HttpUtils;
import com.midland.web.api.ApiHelper;
import com.midland.web.api.SmsSender.SmsClient;
import com.midland.web.api.SmsSender.SmsModel;
import com.midland.web.commons.core.util.ConstantUtils;
import com.midland.web.model.WebUser;
import com.midland.web.model.remote.Agent;
import com.midland.web.model.user.Agenter;
import com.midland.web.service.AppointmentService;
import com.midland.web.util.MidlandHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
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
import java.util.concurrent.TimeUnit;

/**
 * Created by 'ms.x' on 2017/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("all")
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
public class PTest {

	@Resource
	private RedisTemplate<String, WebUser> userSessionRedisTemplate;

	@Autowired
	private AppointmentService appointmentServiceImpl;
	@Autowired
	private MidlandConfig midlandConfig;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private ApiHelper apiHelper;
	
	@Test
	public void dsfs(){
		Map map1 = new HashMap();
		map1.put("pageSize","5");
		map1.put("pageNo","1");
		String data = HttpUtils.get(midlandConfig.getAgentPage(), map1);
		List result = MidlandHelper.getAgentPojoList(data, Agent.class);

		Map map = new HashMap();
		map1.put("id","000c372c-b14f-48e1-a4ae-ba4d5f8d6771");
		String data1 = HttpUtils.get(midlandConfig.getAgentDetail(), map);
		List resul1t = MidlandHelper.getAgentPojoList(data1, Agent.class);
	}
	
	@Test
	public void test() throws IOException {
		String sb ="http://www.bizapp.com/api/SmsService/SendSMS?webKey=eb043d9ddf9e1a275755da3307cf80fe58bdeff635a595eb89293b8367989935&sessionId=&bizAppId=F0000006&customerId=test&sendType=1&userId=admin&phones=12467686&cont=NTQ2NTQ2NDU2&type=13&ucCode=";
		URL url = new URL(sb);
		StringBuffer sbs= new StringBuffer();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String line=null;
			while((line = in.readLine()) != null){
				sbs.append(line);
			}
			System.out.println(sbs.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
				connection.disconnect();
			}
		}
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


	@Test
	public void sendTxSms() throws Exception {

		/*try {
			SmsSingleSender sender = new SmsSingleSender(1400048150, "cf34fd4134eb80f7accac8a37329a873");
			ArrayList<String> params = new ArrayList<String>();
			params.add("1234");
			params.add("2");
			SmsSingleSenderResult   result = sender.sendWithParam("86", "13600158343", 54711, params, "", "", "");
			System.out.println(result.errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}*/


		try {
			SmsSingleSender sender = new   SmsSingleSender(1400048150, "cf34fd4134eb80f7accac8a37329a873");
			SmsSingleSenderResult result = sender.send(1, "86", "13600158343", "【美联物业】验证码测试1234", "", "");
			System.out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sefdxsde(){
		try {
			userSessionRedisTemplate.opsForValue().set(ConstantUtils.USER_SESSION+"123",new WebUser());
			userSessionRedisTemplate.expire(ConstantUtils.USER_SESSION+"123",5,TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
