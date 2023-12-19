package com.codex.kioom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {
    private String H_ID;
    private String EMAIL;
    private String H_PW;
    private String H_NAME;
    private String H_LOCATION;
    private String H_MANAGER;
    private String H_PHONE;
    private String H_TEL;
    private String H_FAX;
    private String CREATE_DATE;
    private String UPDATE_DATE;

    private UserDTO userDTO;
    private List<UserDTO> userDTOList;
}
