package com.taotao.rest.service.imp;


import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImp implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient JedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(Long contentCid) throws Exception {
		//添加缓存
		//从缓存中取内容
		try {
			String result=JedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			if(!StringUtils.isBlank(result)){
				//如果存在  转换为list
				List<TbContent> resultlist=JsonUtils.jsonToList(result, TbContent.class);
				return  resultlist;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example=new TbContentExample();
		Criteria criteria=example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list=contentMapper.selectByExample(example);
		
		try {
			//向缓存中添加数据
			String cacheString =JsonUtils.objectToJson(list);
			JedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid+"", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
