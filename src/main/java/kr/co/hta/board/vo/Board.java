package kr.co.hta.board.vo;

import java.util.Date;

public class Board {

	private int no;
	private String title;
	private String nick;
	private String contentes;
	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", nick=" + nick + ", contentes=" + contentes + ", likes="
				+ likes + ", filename=" + filename + ", creatDate=" + creatDate + "]";
	}
	private int likes;
	private String filename;
	private Date creatDate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getContentes() {
		return contentes;
	}
	public void setContentes(String contentes) {
		this.contentes = contentes;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
}
