package com.zdjy.bigdata.antun.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.service.IUserService;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.model.UserUpdate;
import com.zdjy.bigdata.antun.web.response.BaseResponse;
import com.zdjy.bigdata.antun.web.vaildation.UserValidation;



@RestController
@RequestMapping("/users")
public class UserController extends BaseResponse{

	@Autowired
	private IUserService userService;
	@Autowired
	private UserValidation userValidation;
	
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public BaseResponse findall(){
		List<User> findall=userService.findall();
		return successResponse("查询成功").data(findall);
		
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse addUser(UserAdd userAdd){
		//验证
		String msg =userValidation.adduserValidation(userAdd);
		if (msg!=null) {
			return errorResponse(msg);
		}
		int addUser=userService.addUser(userAdd);
		return successResponse("入库成功，数量："+addUser);
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public BaseResponse getUser(@PathVariable Long id){
		User user=userService.getUser(id);
		return successResponse("查询成功").data(user);
	}
	
	/**
	 * 修改
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public BaseResponse updateUser(@PathVariable Long id,UserUpdate userUpdate){
		//参数验证
		String msg=userValidation.updateUserValidation(userUpdate);
		if(msg!=null)
			return errorResponse(msg);
		//执行业务员
		int updateUser = userService.updateUser(id,userUpdate);
		
		return successResponse("修改成功，数量："+updateUser);
	}
	/**
	 * 删除id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public BaseResponse deleteUser(@PathVariable Long id){
		int deleteUser = userService.deleteUser(id);
		return successResponse("删除成功，数量："+deleteUser);
	}
	
	
}
