package net.my.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.my.board.Article;
import net.my.board.BoardService;

	@Controller
	@RequestMapping("/bbs")
	public class BbsController {
	 
	    @Autowired
	    private BoardService boardService;
	   
	    @RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	    public String list(String boardCd, Model model) throws Exception{
	               
	        if (boardCd == null) boardCd = "free";
	       
	        ArrayList<Article> list = boardService.getArticleList(boardCd);
	        String boardNm = boardService.getBoardNm(boardCd);
	       
	        model.addAttribute("list", list);
	        model.addAttribute("boardNm", boardNm);
	        model.addAttribute("boardCd", boardCd); // 게시판 종류에 대한 정보 보냄
 	       
	        return "bbs/list"; 
	    }
	    
	    @RequestMapping(value="/write", method=RequestMethod.GET)
	    public String write(String boardCd, Model model) throws Exception {
	       
	        //게시판 이름
	        String boardNm = boardService.getBoardNm(boardCd);
	        model.addAttribute("boardNm", boardNm);
	       
	        return "bbs/writeform";
	    }
	   
	    @RequestMapping(value="/write", method=RequestMethod.POST)
	    public String write(Article article) {
	        article.setEmail("비회원"); //임시
	        boardService.insert(article);
	        return "redirect:/bbs/list?boardCd=" + article.getBoardCd();
	    }
	    
	    //글 상세보기
	    @RequestMapping(value="view", method=RequestMethod.GET)
	    public String view(int articleNo, String boardCd, Model model){
	       
	        boardService.increaseHit(articleNo);
	       
	        //상세보기
	        Article thisArticle = boardService.getArticle(articleNo);
	        Article prevArticle = boardService.getPrevArticle(articleNo, boardCd); //이전페이지
	        Article nextArticle = boardService.getNextArticle(articleNo, boardCd); //다음페이지 
	 
	        ArrayList<Article> list = boardService.getArticleList(boardCd);
	        String boardNm = boardService.getBoardNm(boardCd);
	       
	        model.addAttribute("thisArticle", thisArticle);

	        model.addAttribute("list", list);
	        model.addAttribute("prevArticle", prevArticle);
	        model.addAttribute("nextArticle", nextArticle);
	        model.addAttribute("boardNm", boardNm);
	        model.addAttribute("boardCd", boardCd);
	       
	        return "bbs/view";
	    }
	    
	    
}
