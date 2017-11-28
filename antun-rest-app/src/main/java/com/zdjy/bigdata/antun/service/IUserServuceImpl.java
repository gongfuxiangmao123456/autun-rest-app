package com.zdjy.bigdata.antun.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.domain.UserExample;
import com.zdjy.bigdata.antun.domain.UserExample.Criteria;
import com.zdjy.bigdata.antun.mapper.UserMapper;
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.model.UserUpdate;

@Service
public class IUserServuceImpl implements IUserService {

	
	@Autowired
	private UserMapper userMapper;
	@Override
	public User findByPhone(String phone) {
		// TODO Auto-generated method stub
		UserExample userExample=new UserExample();
		Criteria criteria=userExample.createCriteria();
		
		criteria.andPhoneEqualTo(phone);
		List<User>list=userMapper.selectByExample(userExample);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<User> findall() {
		// TODO Auto-generated method stub
		return userMapper.selectByExample(null);
	}

	@Override
	public int addUser(UserAdd userAdd) {
		// TODO Auto-generated method stub
		User user=TransferUtil.transfer(userAdd, User.class);
		user.setGmtCreate(new Date());
		return userMapper.insertSelective(user);
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		
		
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteUser(Long id) {
		// TODO Auto-generated method stub
		
		
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateUser(Long id, UserUpdate userUpdate) {
		// TODO Auto-generated method stub
		User user=TransferUtil.transfer(userUpdate, User.class);
		user.setId(id);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> findbyStatus(int i) {
		// TODO Auto-generated method stub
		UserExample userExample= new  UserExample();
		Criteria criteria=userExample.createCriteria();
		criteria.andStutusEqualTo(i);
		
		return userMapper.selectByExample(userExample);
	}

}
