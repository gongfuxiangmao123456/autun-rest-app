package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.model.UserUpdate;

public interface IUserService {

	User findByPhone(String phone);

	List<User> findall();

	int addUser(UserAdd userAdd);

	User getUser(Long id);

	int deleteUser(Long id);

	int updateUser(Long id, UserUpdate userUpdate);

	int updateUser(User user);

	List<User> findbyStatus(int i);

}
