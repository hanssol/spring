package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

// spring bean 이름 : 인스턴스 생성규칙 --> 클래스명에서 첫글자를 소문자로 : boardDao
// spring bean 이름을 임의로 주고 싶은 경우 @Repository("bDao")
@Repository
public class BoardDao implements IBoardDao{
	
	private IBoardDao boardDao;
	
	@Override
	public String sayHello() {
		return "boardDao sayHello";
	}

}
