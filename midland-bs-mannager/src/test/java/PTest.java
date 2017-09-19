import com.alibaba.fastjson.JSONArray;
import com.midland.config.MidlandConfig;
import com.midland.core.util.HttpUtils;
import com.midland.web.SmsSender.SmsClient;
import com.midland.web.SmsSender.SmsModel;
import com.midland.web.SmsSender.SmsResult;
import com.midland.web.model.remote.Agent;
import com.midland.web.service.AppointmentService;
import com.midland.web.util.MidlandHelper;
import org.jdom.JDOMException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	
	@Test
	public void dsfs(){
		SmsModel smsModel = new SmsModel();
		smsModel.setPhones("13600158343");
		smsModel.setFields("11||11||111");
		smsModel.setTpId("2029157");
		try {
			SmsResult reuslt = smsClient.execute(smsModel);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(JSONArray.toJSONString(reuslt));
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
