package com.zdjy.bigdata.antun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.domain.ProductExample;
import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.domain.ProductExample.Criteria;
import com.zdjy.bigdata.antun.mapper.ProductMapper;

@Service
public class IProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public Product faindbyproductcode(String productCode) {
		// TODO Auto-generated method stub
		ProductExample productExample=new ProductExample();
		Criteria criteria=productExample.createCriteria();
		criteria.andCodeEqualTo(productCode);
		List<Product>list=productMapper.selectByExample(productExample);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
