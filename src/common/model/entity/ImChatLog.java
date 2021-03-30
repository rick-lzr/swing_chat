package common.model.entity;

public class ImChatLog {
	private int chatid;
	private int fromuser;
	private int touser;
	private int chattime;
	public int getChatid() {
		return chatid;
	}
	public void setChatid(int chatid) {
		this.chatid = chatid;
	}
	public int getFromuser() {
		return fromuser;
	}
	public void setFromuser(int fromuser) {
		this.fromuser = fromuser;
	}
	public int getTouser() {
		return touser;
	}
	public void setTouser(int touser) {
		this.touser = touser;
	}
	public int getChattime() {
		return chattime;
	}
	public void setChattime(int chattime) {
		this.chattime = chattime;
	}
	public int getChat() {
		return chat;
	}
	public void setChat(int chat) {
		this.chat = chat;
	}
	public int getChatflag() {
		return chatflag;
	}
	public void setChatflag(int chatflag) {
		this.chatflag = chatflag;
	}
	private int chat;
	private int chatflag;
}
