package org.jiabin.security.authorization.server.controller;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * @author jiabin.yu
 * @description 用户信息管理Controller
 * @date 2023/12/22
 * 
 */
@Controller
public class UserController {

    @ResponseBody
    @GetMapping("/user")
    public Map<String,Object> user(Principal principal) {
//        if (!(principal instanceof JwtAuthenticationToken token)) {
        if (!(principal instanceof JwtAuthenticationToken)) {
            return Collections.emptyMap();
        }
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        return token.getToken().getClaims();
    }
}
