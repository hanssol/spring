package kr.or.ddit.board.dao;

public class BoardDao implements IBoardDao{
	
	private IBoardDao boardDao;
	
	@Override
	public String sayHello() {
		return "boardDao sayHello";
	}

}
