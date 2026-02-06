package com.smart.helper;


import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class MessageHelper {

    public static void removeMessage(){
        System.out.println("Removing Meesgae");
try{
    
        HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("message");

}catch(Exception e){
    e.printStackTrace();
}

    }

}
