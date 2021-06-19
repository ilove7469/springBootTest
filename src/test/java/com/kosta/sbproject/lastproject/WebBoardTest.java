package com.kosta.sbproject.lastproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import com.kosta.sbproject.model.PageVO;
import com.kosta.sbproject.model.WebBoard;
import com.kosta.sbproject.model.WebBoardReply;
import com.kosta.sbproject.persistence.WebBoardRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

//@Commit
@Log
@SpringBootTest
public class WebBoardTest {
	
	@Autowired
	WebBoardRepository repo;  //repo로 인서트
	
	
	
	
	
	//@Test  //동적조회 페이지 vo사용
	public void conditionRetrieve11() {
		//null테스트는 괄호안에 null, null 넣으면된다.
		Predicate p = repo.makePredicate1("writer","2");  //writer에 2가 포함된걸 찾아라~
		
		PageVO pvo = new PageVO();
		Pageable pageable = pvo.makePaging(0, "bno");
		
		Page<WebBoard> result = repo.findAll(p, pageable);
		List<WebBoard> boardlist = result.getContent();
		boardlist.forEach(b->{
			System.out.println(b);
		});
		System.out.println("한페이지의 사이즈"+result.getSize());
		System.out.println("전체 페이지"+result.getTotalPages());
	}
	
	
	
	
	//@Test  //동적조회
	public void conditionRetrieve1() {
		//null테스트는 괄호안에 null, null 넣으면된다.
		Predicate p = repo.makePredicate1("writer","2");  //writer에 2가 포함된걸 찾아라~
	
		//도메인에 있음
		Pageable pageable = PageRequest.of(0, 3);
		
		Page<WebBoard> result = repo.findAll(p, pageable);
		List<WebBoard> boardlist = result.getContent();
		boardlist.forEach(b->{
			System.out.println(b);
		});
		System.out.println("한페이지의 사이즈"+result.getSize());
		System.out.println("전체 페이지"+result.getTotalPages());
	}
	
	
	
	
	//@Test  //0보다 큰거(조건조회)를 페이징 처리해서 출력
	public void conditionRetrieve() {
		Predicate p = repo.makePredicate();
		//도메인에 있음
		Pageable pageable = PageRequest.of(0, 3);
		
		Page<WebBoard> result = repo.findAll(p, pageable);
		List<WebBoard> boardlist = result.getContent();
		boardlist.forEach(b->{
			System.out.println(b);
		});
		System.out.println("한페이지의 사이즈"+result.getSize());
		System.out.println("전체 페이지"+result.getTotalPages());
	}
	
	
	
	
	//@Transactional 직접 조인해서 안넣어줘도됨  연관관계 이런걸로 하지 않았기때문...
	//@Test //모든 게시물 댓글 몇개냐?
	public void boardReplyCount2() {
		repo.getBoardReplyCount().forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
	}
	
	
	
	//@Transactional
	//@Test  //게시물에 댓글이 몇개냐??
	public void boardReplyCount() {
		repo.findById(426L).ifPresent(b->{
			System.out.println(b.getReplies().size());
		});
	}
	
	//@Transactional
	//@Test   //보드에 인서트
	public void insertReply() {
		repo.findById(890L).ifPresent(b->{
			List<WebBoardReply> replies = b.getReplies();  //이거에 리턴값이 보드에 리턴값..리턴값을 리스트 타입으로 받는다..
			b.setTitle("금요일좋아");
			IntStream.range(6, 50).forEach(i->{
				WebBoardReply wreply= WebBoardReply.builder()
						.reply("댓글!!..." + i)
						.replyer("작성자" + i)
						.board(b) //어느 보드인가?.....
						.build();
				replies.add(wreply);
			});
			repo.save(b);
		});
	}
	//입력이 안된다면 웹보드에서 eager로 바꾸던지 지금 테스트에서 커밋,트랜잭셔널 쓰던지.. 둘중에 하나해야한다....
	
	@Test   //보드에 인서트
	public void insertBoard() {
		IntStream.range(1, 300).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("test" + i)
					.content("content" + i)
					.writer("글쓴이" + i%3)
					.build();
			repo.save(board);
		});
		
	}

}
