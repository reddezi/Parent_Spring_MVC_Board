package net.my.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.my.user.User;
import net.my.user.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	private UserService userService;
	
	//Get방식
    @RequestMapping(value="/signUp", method=RequestMethod.GET)
    public String signUp() {
        return "users/signUp"; //가입 Form 이동
    }
   
    //Post방식
    @RequestMapping(value="/signUp", method=RequestMethod.POST)
    public String signUp(User user) throws Exception {
 
        return "users/welcome";  //가입 후 환영페이지 이동
    }
    
    //로그인 페이지 
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        return "users/login";
    }
   
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(String email, String passwd, HttpSession session) throws Exception {
        User loginUser = userService.login(email, passwd);
        if (loginUser != null) {
            session.setAttribute("check", loginUser);
            return "users/changePasswd";
        } else {
            return "users/login";
        }
    }
    
    @RequestMapping(value="/editAccount", method=RequestMethod.GET)
    public String editAccount() {
        return "users/editAccount";
    }
   
    @RequestMapping(value="/editAccount", method=RequestMethod.POST)
    public String editAccount(User user, HttpSession session) throws Exception {
        User loginUser = (User) session.getAttribute("check");
        String email = loginUser.getEmail(); //세션에 저장된 사용자 정보로부터 이메일을 알아낸다.
       
        if (user.getName() == null) {
            user.setName(loginUser.getName());
        }
        if (user.getMobile() == null) {
            user.setMobile(loginUser.getMobile());
        }
       
        user.setEmail(email);
        int check = userService.modify(user);
        if (check == 1) {
            session.setAttribute("check",user);
        }
       
        return "users/changePasswd";
       
    }
   
    @RequestMapping(value="/changePasswd", method=RequestMethod.GET)
    public String changePasswd() {
        return "users/changePasswd";
    }
   
    @RequestMapping(value="/changePasswd", method=RequestMethod.POST)
    public String changePasswd(String passwd, HttpSession session) throws Exception {
        String email = ((User)session.getAttribute("check")).getEmail();
       
        User user = new User();
        user.setEmail(email);
        user.setPasswd(passwd);
       
        userService.changePasswd(user);
       
        return "users/confirm";
    }
   
    @RequestMapping(value="/bye", method=RequestMethod.GET)
    public String bye(HttpSession session) {
        User user = (User)session.getAttribute("check");
        userService.byeMember(user);
        session.invalidate();
       
        return "users/login";
    }
   
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("check");
 
        return "users/login";
 
    }
 
    
	
}
