package com.codex.kioom.controller;

import com.codex.kioom.config.security.auth.PrincipalDetails;
import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/checkUserId.ajax", method = RequestMethod.POST)
    public int checkUser(@RequestParam Map<String, Object> param) {
        return userService.getUserIdYn(param);
    }

    // 병원 등록
    @RequestMapping(value = "/account_proc", method = { RequestMethod.GET, RequestMethod.POST })
    public void account_proc(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        // 사용자 등록
        userService.insertUser(param);

        response.sendRedirect("/");
    }

    // 병원 정보 수정
    @RequestMapping(value = "/update_proc", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public int update_proc(@RequestParam Map<String, Object> param) {
        return userService.updateUser(param);
    }

    // 나의 정보 관리
    @RequestMapping(value = "/myData", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView myDataView(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                   ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) {

        if (authUser == null) {
            modelView.setViewName("redirect:/");
            return modelView;
        }

        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());
        // 로그인한 사용자 아이디
        modelView.addObject("user_id", authUser.getUsername().toString());

        // 로그인 정보 전달
        Map<String, String> hm = new HashMap();
        hm.put("h_id", authUser.getUsername());
        hm.put("email", authUser.getEmail());
        hm.put("h_name", authUser.getName());
        hm.put("h_location", authUser.getLocation());
        hm.put("h_phone", authUser.getPhone());
        hm.put("h_tel", authUser.getTel());
        hm.put("h_manager", authUser.getManager());
        hm.put("h_fax", authUser.getFax());

        modelView.addObject("user_info", hm);

        modelView.setViewName("/web/user/myData");
        return modelView;
    }

    @RequestMapping(value = "/delete_proc", method = { RequestMethod.GET, RequestMethod.POST })
    public void delete_proc(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                            ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        // 병원 삭제
        userService.deleteHospitalList(param);

        response.sendRedirect("/");
    }

    @RequestMapping(value = "/myData/{h_id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView myDataUserView(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                   ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser, @PathVariable String h_id) throws IOException {

        // 최고관리자가 아니면 메인 페이지로 이동
        if(!authUser.getRoleCd().toString().equals("999")) {
            modelView.setViewName("redirect:/");
            return modelView;
        }

        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());
        // 로그인한 사용자 아이디
        modelView.addObject("user_id", authUser.getUsername().toString());
        // 선택한 유저 아이디 전달
        param.put("h_id", h_id);

        // 로그인 정보 전달
        Map<String, Object> hm = userService.selectUserInfo(param);

        Map<String, String> user_info = new HashMap();
        user_info.put("h_id", hm.get("H_ID").toString());
        user_info.put("email", hm.get("EMAIL").toString());
        user_info.put("h_name", hm.get("H_NAME").toString());
        user_info.put("h_location", hm.get("H_LOCATION").toString());
        user_info.put("h_phone", hm.get("H_PHONE").toString());
        user_info.put("h_tel", hm.get("H_TEL").toString());
        user_info.put("h_manager", hm.get("H_MANAGER").toString());
        user_info.put("h_fax", hm.get("H_FAX").toString());

        modelView.addObject("user_info", user_info);

        modelView.setViewName("/web/user/myData");
        return modelView;
    }

    @RequestMapping(value = "/hospital/{h_id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView hospitalManagement(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                       ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser, @PathVariable String h_id) throws IOException {

        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());
        // 로그인한 사용자 아이디
        modelView.addObject("user_id", authUser.getUsername().toString());
        // 선택한 유저 아이디 전달
        param.put("h_id", h_id);

        // 로그인 정보 전달
        Map<String, Object> hm = userService.selectUserInfo(param);

        Map<String, String> user_info = new HashMap();
        user_info.put("h_id", hm.get("H_ID").toString());
        user_info.put("email", hm.get("EMAIL").toString());
        user_info.put("h_name", hm.get("H_NAME").toString());
        user_info.put("h_location", hm.get("H_LOCATION").toString());
        user_info.put("h_phone", hm.get("H_PHONE").toString());
        user_info.put("h_tel", hm.get("H_TEL").toString());
        user_info.put("h_manager", hm.get("H_MANAGER").toString());
        user_info.put("h_fax", hm.get("H_FAX").toString());

        modelView.addObject("user_info", user_info);

        modelView.setViewName("/web/user/hospital");
        return modelView;
    }

}
