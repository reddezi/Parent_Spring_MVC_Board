package net.my.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.my.board.Article;
import net.my.board.BoardService;
import net.my.commons.PagingHelper;

@Controller
@RequestMapping("/bbs")
public class BbsController {

	 @Autowired
	 private BoardService boardService;
	 
	 @RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	 public String list(String boardCd,
	   Integer curPage,
	   String searchWord,
	   Model model) throws Exception{
	    
	  if (boardCd == null) boardCd = "free";
	  if (curPage == null) curPage = 1;
	  if (searchWord == null) searchWord = "";
	  
	  int numPerPage = 10;// 페이지당 레코드 수 지정
	  int pagePerBlock = 10;// 페이지 링크의 그룹(block)의 크기 지정
	  
	  int totalRecord = boardService.getTotalRecord(boardCd, searchWord);
	  
	  PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);  
	  boardService.setPagingHelper(pagingHelper);
	  
	  int start = pagingHelper.getStartRecord();
	  int end = pagingHelper.getEndRecord();

	  ArrayList<Article> list = boardService.getArticleList(boardCd, searchWord, start, end);
	  String boardNm = boardService.getBoardNm(boardCd);
	  Integer no = boardService.getListNo();
	  Integer prevLink = boardService.getPrevLink();
	  Integer nextLink = boardService.getNextLink();
	  Integer firstPage = boardService.getFirstPage();
	  Integer lastPage = boardService.getLastPage();
	  int[] pageLinks = boardService.getPageLinks();
	  
	  // 목록을 위한 데이터
	  model.addAttribute("list", list);
	  model.addAttribute("boardNm", boardNm);
	  model.addAttribute("boardCd", boardCd);//boardCd는 null 값이면 free로 만들어야 하므로
	  
	  model.addAttribute("no", no);
	  model.addAttribute("prevLink", prevLink);
	  model.addAttribute("nextLink", nextLink);
	  model.addAttribute("firstPage", firstPage);
	  model.addAttribute("lastPage", lastPage);
	  model.addAttribute("pageLinks", pageLinks);
	  model.addAttribute("curPage", curPage);//curPage는 null 값이면 1로 만들어야 하므로
	  
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
	 public String write(Article article) throws Exception {
	  article.setEmail("비회원"); //임시
	  boardService.insert(article);
	  return "redirect:/bbs/list?boardCd=" + article.getBoardCd();
	 }
	 
	 @RequestMapping(value="/view", method=RequestMethod.GET)
	 public String view(int articleNo,
	   String boardCd,
	   Integer curPage,
	   String searchWord,
	   Model model) throws Exception {
	  
	  int numPerPage = 10;// 페이지당 레코드 수 지정
	  int pagePerBlock = 10;// 페이지 링크의 그룹(block)의 크기 지정
	  if (searchWord == null) searchWord = ""; // 검색어가 null 이면 ""으로 변경 

	  //목록보기
	  int totalRecord = boardService.getTotalRecord(boardCd, searchWord);
	  System.out.println(curPage);
	  PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);  
	  boardService.setPagingHelper(pagingHelper);
	  
	  int start = pagingHelper.getStartRecord();
	  int end = pagingHelper.getEndRecord();

	  ArrayList<Article> list = boardService.getArticleList(boardCd, searchWord, start, end);
	  String boardNm = boardService.getBoardNm(boardCd);
	  Integer no = boardService.getListNo();
	  Integer prevLink = boardService.getPrevLink();
	  Integer nextLink = boardService.getNextLink();
	  Integer firstPage = boardService.getFirstPage();
	  Integer lastPage = boardService.getLastPage();
	  int[] pageLinks = boardService.getPageLinks();
	  
	  // 목록을 위한 데이터
	  model.addAttribute("list", list);
	  model.addAttribute("boardNm", boardNm);
	  
	  model.addAttribute("no", no);
	  model.addAttribute("prevLink", prevLink);
	  model.addAttribute("nextLink", nextLink);
	  model.addAttribute("firstPage", firstPage);
	  model.addAttribute("lastPage", lastPage);
	  model.addAttribute("pageLinks", pageLinks);
	  
	  boardService.increaseHit(articleNo);//조회수 증가
	  
	  //상세보기
	  Article thisArticle = boardService.getArticle(articleNo);
	  Article prevArticle = boardService.getPrevArticle(articleNo, boardCd, searchWord);
	  Article nextArticle = boardService.getNextArticle(articleNo, boardCd, searchWord);

	  model.addAttribute("thisArticle", thisArticle);
	  model.addAttribute("prevArticle", prevArticle);
	  model.addAttribute("nextArticle", nextArticle);
	  
	  return "bbs/view";
	 }
	 
	 @RequestMapping(value="/delete", method=RequestMethod.POST)
	 public String delete(int articleNo, 
	   String boardCd,
	   Integer curPage, 
	   String searchWord) throws Exception {
	  
	  boardService.delete(articleNo);
	  
	  return "redirect:/bbs/list?boardCd=" + boardCd + 
	  "&curPage=" + curPage + 
	  "&searchWord=" + searchWord;
	 } 
	 
	 @RequestMapping(value="/modify", method=RequestMethod.GET)
	 public String update(int articleNo,
	   String boardCd,
	   Model model) throws Exception {
	  
	  Article thisArticle = boardService.getArticle(articleNo);
	  String boardNm = boardService.getBoardNm(boardCd);
	      
	     //수정페이지에서 보일 게시글 정보
	     model.addAttribute("thisArticle", thisArticle);
	  model.addAttribute("boardNm", boardNm);
	  
	  return "bbs/modifyForm";
	 }
	 
	 @RequestMapping(value="/modify", method=RequestMethod.POST)
	 public String update(Article article,
	   Integer curPage,
	   String boardCd,
	   String searchWord,
	   Model model) throws Exception {

	  boardService.update(article);
	  
	  return "redirect:/bbs/view?articleNo=" + article.getArticleNo() + 
	    "&boardCd=" + article.getBoardCd() + 
	    "&curPage=" + curPage +
	    "&searchWord=" + searchWord;
	 }
}