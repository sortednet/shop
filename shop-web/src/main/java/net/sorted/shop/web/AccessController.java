package net.sorted.shop.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessController {
    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        return "access/login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        return "access/denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        String message = "Login Failure!";
        return "redirect:shop/login.do?message=" + message;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        String message = "You have logged out!";
        session.invalidate();
        return "redirect:/shop/login.do?message=" + message;
    }
    
    @RequestMapping(value = "/logout/success")
    public String logoutSuccess(HttpSession session) {
        String message = "Logout Success!";
        session.invalidate();
        return "redirect:/shop/login.do?message=" + message;
    }
}
