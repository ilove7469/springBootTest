package com.kosta.sbproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.sbproject.model.MemberDTO;

public interface MemberRepository extends CrudRepository<MemberDTO, String>{

	
	//JPQL문법 : entity이름에 주의한다.
	@Query(value="select m.mid, count(p) "
			+ "from MemberDTO m left outer join ProfileDTO p on (m.mid = p.member)\r\n"
			+ "group by m.mid")
	public List<Object[]> getMemberWithProfileCount2();
	
	
	//nativeQuery = true : db문법
	@Query(value="select m.mid, count(p.fno)\r\n"
			+ "from tbl_members m left outer join tbl_profile p on (m.mid = p.member_mid)\r\n"
			+ "group by m.mid", nativeQuery = true)
	public List<Object[]> getMemberWithProfileCount();
	
	
}
