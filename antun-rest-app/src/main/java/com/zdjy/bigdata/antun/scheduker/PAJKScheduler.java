package com.zdjy.bigdata.antun.scheduker;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.remot.PAJKSender;
import com.zdjy.bigdata.antun.service.IUserService;



@Component
public class PAJKScheduler {
	
	private Logger logger=LoggerFactory.getLogger(PAJKScheduler.class);

	@Autowired
private	PAJKSender pajksender;
	@Autowired 
	private IUserService userSercive;
	@Scheduled(cron="* * * * * ?")
	public void send(){
		logger.info("start*****");
		List<User> list=userSercive.findbyStatus(0);
		logger.info(list.size()+"条数据待发送");
		if (list.isEmpty()) {
			return;
		}
		
		for(User user:list){
			pajksender.send(user);
			
		}
		logger.info("end*******");
	}
	

}
