package com.zdjy.bigdata.antun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.service.IAreaService;
import com.zdjy.bigdata.antun.web.response.BaseResponse;

@RestController
@RequestMapping("/areas")
public class AreaController {

	@Autowired
	private IAreaService areaService;
	
	@RequestMapping(value="/{parentId}",method=RequestMethod.GET)
	public BaseResponse findByParentId(@PathVariable Long id){
		List<Area>list=areaService.findByParentId(id);
		return null;
		
	}
}
