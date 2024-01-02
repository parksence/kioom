package com.codex.kioom.dao;

import com.codex.kioom.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {

    Map<String, Object> userChk(Map<String, Object> param);
    ArrayList<UserDTO> findByUserID(String username);
    int getUserIdYn(Map<String, Object> param);
    void insertUser(Map<String, Object> param);
    void insertExcelUser(Map<String, Object> param);
    int updateUser(Map<String, Object> param);
    List<Map<String, Object>> getHospitalList(Map<String, Object> param);
    void deleteHospitalList(Map<String, Object> param);
    int getHospitalListCnt(Map<String, Object> param);
    Map<String, Object> selectUserInfo(Map<String, Object> param);
    List<String> selectUserId();
}
