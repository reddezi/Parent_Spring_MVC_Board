package net.my.board;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.my.mybatis.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {

	    @Autowired
	    private BoardMapper boardMapper;
	   
	    /*
	     * 게시판 목록
	     */
	    public ArrayList<Article> getArticleList(String boardCd) {
	 
	        HashMap<String, String> hashmap = new HashMap<String, String>();
	        hashmap.put("boardCd", boardCd);
	   
	        return boardMapper.getArticleList(hashmap);
	    }
	   
	    /*
	     * 새로운 게시글  추가
	     */
	    public int insert(Article article) {
	        return boardMapper.insert(article);
	    }
	   
	    /*
	     * 게시글 수정
	     */
	    public void update(Article article) {
	        boardMapper.update(article);
	    }
	   
	    /*
	     * 게시글 삭제
	     */
	    public void delete(int articleNo) {
	        boardMapper.delete(articleNo);
	    }

	    /*
	     * 게시판 이름(종류)
	     */
	    public String getBoardNm(String boardCd){
	        return boardMapper.getBoardNm(boardCd);
	    }

		@Override
		public void increaseHit(int articleNo) {
			 boardMapper.increaseHit(articleNo);
			
		}

		@Override
		public Article getArticle(int articleNo) {
			return boardMapper.getArticle(articleNo);
		}

		@Override
		public Article getPrevArticle(int articleNo, String boardCd) {
			HashMap<String, String> hashmap = new HashMap<String, String>();
			Integer no = articleNo;
			hashmap.put("articleNo", no.toString());
			hashmap.put("boardCd", boardCd);
			return boardMapper.getPrevArticle(hashmap);
		}

		@Override
		public Article getNextArticle(int articleNo, String boardCd) {
			HashMap<String, String> hashmap = new HashMap<String, String>();
			Integer no = articleNo;
			hashmap.put("articleNo", no.toString());
			hashmap.put("boardCd", boardCd);
			return boardMapper.getNextArticle(hashmap);
		}   

}
