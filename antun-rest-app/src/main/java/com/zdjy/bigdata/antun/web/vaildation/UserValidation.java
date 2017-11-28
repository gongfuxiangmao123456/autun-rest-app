package com.zdjy.bigdata.antun.web.vaildation;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.service.IAreaService;
import com.zdjy.bigdata.antun.service.IChannelService;
import com.zdjy.bigdata.antun.service.IProductService;
import com.zdjy.bigdata.antun.service.IUserService;
import com.zdjy.bigdata.antun.util.IsthingUtil;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.model.UserUpdate;

import bsh.StringUtil;
@Component
public class UserValidation extends IsthingUtil{

	@Autowired
	private IChannelService channelService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IUserService userService;
	private String name_pattern="[\\u4e00-\\u9fa5]{2,5}";
	public String adduserValidation(UserAdd userAdd){
		if (StringUtils.isBlank(userAdd.getName())) {
			return empty("姓名");
		}
		if (!userAdd.getName().matches(name_pattern)) {
			return "姓名不符合要求";
		}
		if (StringUtils.isBlank(userAdd.getPhone())) {
			return empty("电话");
		}
		if (!isphone(userAdd.getPhone())) {
			return "手机号码格式不对";
		}
		if (userAdd.getSex()==null) {
			return "性别为空";
		}
		if (userAdd.getSex()!=1 &&userAdd.getSex()!=0) {
			return "性别只能为0或1";
		}
		if (StringUtils.isBlank(userAdd.getBirth())) {
			return empty("生日");
		}
		
		
		if (!isDate(userAdd.getBirth())) {
			return "生日格式不对，不是YY-mm-dd";
		}
		
		if (StringUtils.isBlank(userAdd.getIdNo())) {
			return empty("身份证");
		}
		
		if (!IsIDcard(userAdd.getIdNo())) {
			return "身份证格式不对";
		}
		
		
		if (userAdd.getProvince()==null) {
			return empty("省份");
		}
		if (userAdd.getCity()==null) {
			return empty("市");
		}if (userAdd.getTown()==null) {
			return empty("县区");
		}
		
		if (userAdd.getChannelCode()==null) {
			return empty("渠道码");
		}
		if (userAdd.getProductCode()==null) {
			return empty("产品码");
		}
		
		
		Area area=areaService.findByID(userAdd.getTown());
		if (area==null) {
			return "不存在";
		}
		if (area.getParentId().longValue()!=userAdd.getCity().longValue()) {
			return "区县和城市不匹配";
		}
		Area area2=areaService.findByID(userAdd.getCity());
		if (area2==null) {
			return "不存在";
		}
		if (area2.getParentId().longValue()!=userAdd.getProvince().longValue()) {
			return "城市和省份不匹配";
		}
		
		Channel channel =channelService.faindbyChannelcode(userAdd.getChannelCode()); 
		if (channel==null) {
			return "渠道不存在";
		}
		if (channel.getStatus()!=1) {
			return "渠道已禁用";
		}
		
		Product product =productService.faindbyproductcode(userAdd.getProductCode()); 
		if (product==null) {
			return "产品不存在";
		}
		if (product.getStatus()!=1) {
			return "产品已下线";
		}
		User user=userService.findByPhone(userAdd.getPhone());
		if (user!=null) {
			return "手机号重复";
		}
		
		return null;
		
	}
	private boolean isbirth(String birth) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	   * 手机号:目前全国有27种手机号段。
	    * 移动有16个号段：134、135、136、137、138、139、147、150、151、152、157、158、159、182、187、188。其中147、157、188是3G号段，其他都是2G号段。
	   * 联通有7种号段：130、131、132、155、156、185、186。其中186是3G（WCDMA）号段，其余为2G号段。
	   * 电信有4个号段：133、153、180、189。其中189是3G号段（CDMA2000），133号段主要用作无线网卡号。
	   * 150、151、152、153、155、156、157、158、159 九个;
	   * 130、131、132、133、134、135、136、137、138、139 十个;
	   * 180、182、185、186、187、188、189 七个;
	   * 13、15、18三个号段共30个号段，154、181、183、184暂时没有，加上147共27个。
	   */
	  public boolean isphone(String tel){
	    Pattern p = Pattern.compile("^((13\\d{9}$)|(15[0,1,2,3,5,6,7,8,9]\\d{8}$)|(18[0,2,5,6,7,8,9]\\d{8}$)|(147\\d{8})$)");
	    Matcher m = p.matcher(tel);
	    return m.matches();
	  }
	public String empty(String str){
		return str+"为空";
	}
	public String updateUserValidation(UserUpdate userUpdate) {
		// TODO Auto-generated method stub
		return null;
	}
}
