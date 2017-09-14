package cn.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.uploadDao;
import cn.itcast.domain.quyu;

@Service
public class uploadServcie {
	@Autowired
	private uploadDao dao;
	
	public void upload(List<quyu> yuqus){
		long start = System.currentTimeMillis();
		dao.save(yuqus);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}
