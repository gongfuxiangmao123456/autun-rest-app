package com.zdjy.bigdata.antun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.domain.ChannelExample;
import com.zdjy.bigdata.antun.domain.ChannelExample.Criteria;
import com.zdjy.bigdata.antun.mapper.ChannelMapper;

@Service
public class IChannelServiceImpl implements IChannelService{

	@Autowired
	private ChannelMapper channelMapper;
	@Override
	public Channel faindbyChannelcode(String channelCode) {
		// TODO Auto-generated method stub
		ChannelExample channelExample=new ChannelExample();
		Criteria criteria=channelExample.createCriteria();
		criteria.andCodeEqualTo(channelCode);
		List<Channel>list=channelMapper.selectByExample(channelExample);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
