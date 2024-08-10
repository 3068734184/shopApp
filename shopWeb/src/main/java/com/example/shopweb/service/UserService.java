package com.example.shopweb.service;

import com.example.shopmodel.dto.ResponseDTO;
import com.example.shopmodel.dto.UserLoginDTO;
import com.example.shopmodel.dto.UserRegisterDTO;
import com.example.shopmodel.exception.MyException;
import com.example.shopmodel.model.User;
import com.example.shopmodel.model.UserQuery;
import com.example.shopweb.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int softDeleteUser(Integer id) {
        return userMapper.softDeleteUser(id);
    }

    public List<User> fuzzySelectUser(User user) {
        return userMapper.fuzzySelectUser(user);
    }

    public UserQuery uniqueSelectUser(UserQuery userQuery) {
        return userMapper.uniqueSelectUser(userQuery);
    }

    public ResponseDTO login(UserLoginDTO userLoginDTO) {
        UserQuery userQuery = new UserQuery();
        userQuery.setTel(userLoginDTO.getTel());
        UserQuery uniqueSelectUser = userMapper.uniqueSelectUser(userQuery);
        if (uniqueSelectUser == null) {
            return ResponseDTO.error(500, "未找到该用户");
        }

        if (userLoginDTO.getPassword().equals(uniqueSelectUser.getPassword())) {
            uniqueSelectUser.setPassword(null);
            return ResponseDTO.success(uniqueSelectUser);
        }else {
            return ResponseDTO.error(500, "密码错误");
        }
    }

    public ResponseDTO register(UserRegisterDTO userRegisterDTO) {
        UserQuery userQuery = new UserQuery();
        userQuery.setTel(userRegisterDTO.getTel());
        UserQuery uniqueSelectUser = userMapper.uniqueSelectUser(userQuery);
        if (uniqueSelectUser != null) {
            return ResponseDTO.error(400, "该手机号已注册");
        }
        User user = new User();
        user.setTel(userRegisterDTO.getTel());
        user.setPassword(userRegisterDTO.getPassword());
        user.setUsername(generateRandomUsername());
        user.setRegisterTime(LocalDateTime.now());
        int insertUser = userMapper.insertUser(user);
        if (insertUser > 0) {
            return ResponseDTO.success("注册成功");
        }
        return ResponseDTO.error(400, "未知错误");
    }

    private String generateRandomUsername() {
        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 8; // 假设用户名长度为8
        StringBuilder sb = new StringBuilder(length+3);
        sb.append("jd_");
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(letters.length());
            sb.append(letters.charAt(index));
        }
        return sb.toString();
    }
}
