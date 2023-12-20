package com.codex.kioom.dao;

import com.codex.kioom.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface UserDAO {

    Map<String, Object> userChk(Map<String, Object> param);
    ArrayList<UserDTO> findByUserID(String username);

}
