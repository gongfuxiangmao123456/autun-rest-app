package com.zdjy.bigdata.antun.test;

import java.io.Closeable;
import java.io.IOException;
import java.util.regex.*;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.zdjy.bigdata.antun.remot.PAJK;
import com.zdjy.bigdata.antun.util.IsthingUtil;

import bsh.This;

public class test {

	@Test
	public void test001() throws Exception{
		
		//创建客户端
		CloseableHttpClient build=HttpClientBuilder.create().build();
		String url="http://47.94.250.65/pinganjiekou/area/0";
		
		HttpGet httpget=new HttpGet(url);
		
		CloseableHttpResponse exe=build.execute(httpget);
		HttpEntity entiy=exe.getEntity();
		
		String string=EntityUtils.toString(entiy);
		System.out.println(string);
		
		
		
		
		
	}
	
	@Test
	public void test002(){
		RestTemplate rt=new RestTemplate();
		StringBuilder bu=new StringBuilder("http://47.94.250.65/pinganjiekou/user/add?");
		bu.append("userName=张三哥&");
		bu.append("userSex=1&");
		bu.append("userBirth=1995-12-03&");
		bu.append("userPhone=18815215451&");
		bu.append("userIdNo=32012519947894561x&");
		bu.append("province=110000&");
		bu.append("city=110100&");
		bu.append("town=110101&");
		bu.append("channelCode=afangti_huhuabo_001&");
		bu.append("channelToken=-67-75-49-31-96-89-29-1191749116-528-4926-43&");
		bu.append("productCode=PA000000CXSF-CXWY-01");
		PAJK pa=rt.getForObject(bu.toString(), PAJK.class);
		if(pa.getCode()==200){
			System.out.println("success");
		}else{
			System.out.println(pa.getCode()+pa.getResult());
		}
	}
	
	@Test
	public void  test003(){
		System.out.println(IsthingUtil.IsIDcard("32512319551132222"));
	}
	/**
	 * 13 0 1 3 2 4 5 6 7 8 9
	 * 14 7
	 * 15 0 1 2 3 5 6 7 8 9
	 * 18 0 2 5 6 7 8 9
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
	  
	  
	 @Test
	 public void test004() throws  Exception {
		// TODO Auto-generated method stub
		 RestTemplate rt=new RestTemplate();
		 String url="http://47.94.250.65/pinganjiekou/area/0";
		ResponseEntity<AreaResponse[]>foResponseEntity=rt.getForEntity(url, AreaResponse[].class);
			for (AreaResponse area : foResponseEntity.getBody() ) {
				System.out.println(area.getAreaName()+":"+area.getAreaId());
				String url2="http://47.94.250.65/pinganjiekou/area/"+area.getAreaId();
				ResponseEntity<AreaResponse[]>foResponseEntity2=rt.getForEntity(url2, AreaResponse[].class);
					for (AreaResponse area2 : foResponseEntity2.getBody() ) {
						System.out.println(area2.getAreaName()+":"+area2.getAreaId());
						String url23="http://47.94.250.65/pinganjiekou/area/"+area2.getAreaId();
						ResponseEntity<AreaResponse[]>foResponseEntity23=rt.getForEntity(url23, AreaResponse[].class);
							for (AreaResponse area23 : foResponseEntity23.getBody() ) {
								System.out.println(area23.getAreaName()+":"+area23.getAreaId());
								
							}
					}
			}
			
	}
}
