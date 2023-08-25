package com.codeforyou.springsession.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class sessionController {

    @GetMapping("/storedata")
    public String sessionstoredata(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("token", "qwert0987");
        return "Token Stored Successfully in Session";
    }

    @GetMapping("/getToken")
    public Map<String, Object> getToken(HttpSession httpSession,HttpServletRequest request) {
        Map<String, Object> mapdata = new HashMap<>();
        Object token = httpSession.getAttribute("token");
        HttpSession session = request.getSession(false);
        if(session ==null || request.isRequestedSessionIdValid()){
            mapdata.put("Token", token);
        }else
            mapdata.put("Status","Your Session expired");
        return mapdata;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "Successfully Logout";
    }

}
