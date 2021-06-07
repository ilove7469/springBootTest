package com.kosta.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.sbproject.model.TwTestVO;
import com.kosta.sbproject.persistence.TwRepository;

@SpringBootTest
public class TwTest {

	@Autowired
	TwRepository repo;
	
	@Test
	public void insertTest() {
		TwTestVO tt = new TwTestVO();
		tt.setNum(1);
		tt.setName("가나다");
		repo.save(tt);
	}
	
}
