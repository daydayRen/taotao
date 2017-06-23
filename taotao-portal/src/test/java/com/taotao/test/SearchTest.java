package com.taotao.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;

public class SearchTest {

	
	
	@Test
	public void search() {
		
		//调用taotao-search服务
		//查询参数
//		Map<String, String> param=new HashMap<>();
//		param.put("q", "手机");
		//param.put("page", 1+"");
//		try {
			String json=HttpClientUtil.doGet("http://localhost:8083/search/query?q=手机");
			
			TaotaoResult taotaoResult=TaotaoResult.formatToPojo(json, SearchResult.class);
			
			if (taotaoResult.getStatus() == 200) {
				SearchResult result = (SearchResult) taotaoResult.getData();
				
				System.out.println(result.getItemList().get(0).getPrice());
//				System.out.println(result.getCurPage());
		}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
