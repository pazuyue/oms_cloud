package com.saas.common.security.until;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WebUntils {

    /**
     * 将字符串渲染返回到前端
     * @param response
     * @param string
     * @return
     */
    public static String renderString(HttpServletResponse response,String string){
       try {
           response.setStatus(200);
           response.setContentType("application/json");
           response.setCharacterEncoding("utf-8");
           response.getWriter().print(string);
       }catch (IOException exception){
           exception.printStackTrace();
       }
       return null;
    }
}
