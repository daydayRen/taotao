package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;

public class TestItemParam {

	@Test
	public void test() {
		// 创建spring容器
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		// 从spring容器中获得mapper的代理对象
		TbItemParamMapper mapper = applicationContext.getBean(TbItemParamMapper.class);
		// 执行查询 并分页
		TbItemParamExample example = new TbItemParamExample();
		// 分页处理
		PageHelper.startPage(1, 10);
		// 取商品列表
		List<TbItemParam> list = mapper.selectByExample(example);

		for (TbItemParam tbItemParam : list) {
			System.out.println(tbItemParam.getId());
		}

		// 取分 页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品：" + total);
	}

}
