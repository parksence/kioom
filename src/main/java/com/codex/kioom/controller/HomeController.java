package com.codex.kioom.controller;

import com.codex.kioom.common.Pagination;
import com.codex.kioom.config.security.auth.PrincipalDetails;
import com.codex.kioom.dao.UserDAO;
import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView home(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser
                           , @RequestParam Map<String, Object> param, @RequestParam(defaultValue = "1") int page) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", authUser.getUsername().toString());
        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());

        if(authUser.getRoleNm().equals("최고관리자")) {

            // 총 게시물 수
            int totalCnt = userService.getHospitalListCnt(param);

            // 생성인자로 총 게시물 수, 현재 페이지를 전달
            Pagination pagination = new Pagination(totalCnt, page);

            // DB select start index
            int startIndex = pagination.getStartIndex();
            System.out.println("startIndex = " + startIndex);
            // 페이지 당 보여지는 게시글의 최대 개수
            int pageSize = pagination.getPageSize();
            System.out.println("pageSize = " + pageSize);

            param.put("startIndex", startIndex);
            param.put("pageSize", pageSize);

            // 병원 목록 조회
            modelView.addObject("hospital_list", userService.getHospitalList(param));
            modelView.addObject("pagination", pagination);
            modelView.addObject("page_active", page);

            modelView.setViewName("/web/user/dashboard");
        } else {
            modelView.setViewName("/web/user/hospital");
        }

        return modelView;
    }

    @RequestMapping(value = "/layout", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView layout(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/layout");

        return modelView;
    }

    @RequestMapping(value = "/siteMap", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView siteMap(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/siteMap");

        return modelView;
    }

    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView login(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/login");

        return modelView;
    }

    @RequestMapping(value = "/user/account", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView account(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", authUser.getUsername().toString());
        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());

        modelView.setViewName("/web/user/account");
        return modelView;
    }

    @RequestMapping(value = "/user/patient", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView patient(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", authUser.getUsername().toString());
        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());

        modelView.setViewName("/web/user/patient");
        return modelView;
    }

    @RequestMapping(value = "/user/hospital", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView hospital(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", authUser.getUsername().toString());
        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());

        modelView.setViewName("/web/user/hospital");
        return modelView;
    }

    @RequestMapping(value = "/hospital/list", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ModelAndView getHospital(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser
                                  , @RequestParam Map<String, Object> param, @RequestParam(defaultValue = "1") int page) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", authUser.getUsername().toString());
        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());

        // 총 게시물 수
        int totalCnt = userService.getHospitalListCnt(param);

        // 생성인자로 총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalCnt, page);

        // DB select start index
        int startIndex = pagination.getStartIndex();
        System.out.println("startIndex = " + startIndex);
        // 페이지 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();
        System.out.println("pageSize = " + pageSize);

        param.put("startIndex", startIndex);
        param.put("pageSize", pageSize);

        // 병원 목록 조회
        modelView.addObject("hospital_list", userService.getHospitalList(param));
        modelView.addObject("pagination", pagination);
        modelView.addObject("page_active", page);

        modelView.setViewName("/web/user/dashboard");
        return modelView;
    }


}
