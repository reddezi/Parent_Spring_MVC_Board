package net.my.user;

public interface UserService {
	
	public int insert(User user); //회원가입 정보입력
	public User login(String email, String passwd);
    public int modify(User user);
    public void changePasswd(User user);
    public void byeMember(User user);
}
