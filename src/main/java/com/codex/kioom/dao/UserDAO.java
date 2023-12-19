package com.codex.kioom.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDAO {

    Map<String, Object> userChk(Map<String, Object> param);

}
