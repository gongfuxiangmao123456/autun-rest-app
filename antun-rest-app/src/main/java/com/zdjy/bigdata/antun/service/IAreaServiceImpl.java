package com.zdjy.bigdata.antun.service;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.domain.AreaExample;
import com.zdjy.bigdata.antun.domain.AreaExample.Criteria;
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

	@Override
	public List<Area> findByParentId(Long id) {
		// TODO Auto-generated method stub
		AreaExample areaExample=new AreaExample();
		Criteria criteria=areaExample.createCriteria();
		criteria.andParentIdEqualTo(id);
		
		return areaMapper.selectByExample(areaExample);
	}

}
