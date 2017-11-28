package com.zdjy.bigdata.antun.remot;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.apache.xalan.xsltc.compiler.util.NamedMethodGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.scheduker.PAJKScheduler;
import com.zdjy.bigdata.antun.service.IUserService;

@Component
public class PAJKSender {
	private Logger logger=LoggerFactory.getLogger(PAJKScheduler.class);

	
	@Autowired IUserService userService;
private	RestTemplate rt=new RestTemplate();
	public int send(User user) {
		try {
			StringBuilder bu=new StringBuilder("http://47.94.250.65/pinganjiekou/user/add?");
			bu.append("userName="+user.getName()+"&");
			bu.append("userSex="+user.getSex()+"&");
			bu.append("userBirth="+user.getBirth()+"&");
			bu.append("userPhone="+user.getPhone()+"&");
			bu.append("userIdNo="+user.getIdNo()+"&");
			bu.append("province="+user.getProvince()+"&");
			bu.append("city="+user.getCity()+"&");
			bu.append("town="+user.getTown()+"&");
			bu.append("channelCode=afangti_huhuabo_001&");
			bu.append("channelToken=-67-75-49-31-96-89-29-1191749116-528-4926-43&");
			bu.append("productCode="+user.getProductCode());
			
			logger.info("【send】"+bu.toString());
			PAJK pa=rt.getForObject(bu.toString(), PAJK.class);
			logger.info("【response】"+new Gson().toJson(pa));
			if(pa.getCode()==200){
				user.setStutus(2);
			}else{
				user.setStutus(3);
			}
			user.setSendCode(pa.getCode());
			user.setSendResult(pa.getResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user.setStutus(1);
		}
		int i=userService.updateUser(user);
		return i;
	}
}
