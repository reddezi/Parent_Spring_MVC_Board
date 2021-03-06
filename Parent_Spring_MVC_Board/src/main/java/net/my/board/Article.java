package net.my.board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	
	private int articleNo;
    private String boardCd;
    private String title;
    private String content;
    private String email;
    private int hit;
    private Date regdate;
    private int attachFileNum; // 파일첨부
    private int commentNum; // 댓글 개수
    
    
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getAttachFileNum() {
		return attachFileNum;
	}
	public void setAttachFileNum(int attachFileNum) {
		this.attachFileNum = attachFileNum;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getBoardCd() {
		return boardCd;
	}
	public void setBoardCd(String boardCd) {
		this.boardCd = boardCd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
    public String getWriteDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(regdate);
    }
    public String getWriteDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(regdate);
    }
    // 아래코드를 적용하여 view.jsp 에서 ${getHtmlContent}로 사용
    public static final String ENTER = System.getProperty("line.separator");
    public String getHtmlContent() {
        if (content != null) {
            return content.replaceAll(ENTER, "<br />");
        }
        return null;  
    }
	
	
}
