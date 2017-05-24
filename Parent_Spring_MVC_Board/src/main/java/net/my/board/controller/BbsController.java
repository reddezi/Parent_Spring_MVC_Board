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
	       
	        return "bbs/list"; 
	    }
}
