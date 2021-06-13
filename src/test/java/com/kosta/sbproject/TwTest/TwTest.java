package com.kosta.sbproject.TwTest;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.sbproject.model.Tw_test;
import com.kosta.sbproject.persistence.TwTestRepository;

@SpringBootTest
public class TwTest {

	@Autowired
	TwTestRepository twtestRepo;
	
	@Test
	public void insertBoard() {
		IntStream.range(3, 100).forEach(i->{
			Tw_test board = Tw_test.builder()
					.num(i)
					.name("content" + i)
					.build();
			twtestRepo.save(board);
		});
	}
}
