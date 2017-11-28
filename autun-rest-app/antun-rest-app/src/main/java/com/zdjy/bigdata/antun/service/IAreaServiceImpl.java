package com.zdjy.bigdata.antun.service;

import java.security.KeyStore.PrivateKeyEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.mapper.AreaMapper;

@Service
public class IAreaServiceImpl implements IAreaService{

	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public Area findByID(Long town) {
		// TODO Auto-generated method stub
		return areaMapper.selectByPrimaryKey(town);
	}

}
