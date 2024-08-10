package com.example.shopweb.controller;

import com.example.shopmodel.dto.ResponseDTO;
import com.example.shopmodel.dto.UserLoginDTO;
import com.example.shopmodel.dto.UserRegisterDTO;
import com.example.shopmodel.exception.MyException;
import com.example.shopmodel.model.User;
import com.example.shopmodel.model.UserQuery;
import com.example.shopmodel.properties.JwtProperties;
import com.example.shopmodel.utils.JwtUtil;
import com.example.shopmodel.vo.UserLoginVO;
import com.example.shopweb.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    private JwtProperties jwtProperties;

    public UserController(UserService userService, JwtProperties jwtProperties) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/insertUser")
    public int insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        if (StringUtils.isBlank(userLoginDTO.getPassword()) && StringUtils.isBlank(userLoginDTO.getPassword())) {
            return ResponseDTO.error(400, "手机号或密码为空");
        }

        ResponseDTO login = userService.login(userLoginDTO);
        if (login.getData() == null) {
            return ResponseDTO.error(400, login.getMessage());
        }
        UserQuery user = (UserQuery) login.getData();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();
        return ResponseDTO.success(userLoginVO);
    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody UserRegisterDTO userRegisterDTO) {
        if (StringUtils.isBlank(userRegisterDTO.getTel()) && StringUtils.isBlank(userRegisterDTO.getPassword())) {
            return ResponseDTO.error(500, "手机号或密码为空");
        }

        return userService.register(userRegisterDTO);
    }

    @PutMapping("/updateUser")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser")
    public int softDeleteUser(Integer id) {
        return userService.softDeleteUser(id);
    }

    @GetMapping("/fuzzySelectUser")
    public List<User> fuzzySelectUser(@RequestBody User user) {
        return userService.fuzzySelectUser(user);
    }

    @GetMapping("/uniqueSelectUser")
    public UserQuery uniqueSelectUser(@RequestBody UserQuery userQuery) {
        return userService.uniqueSelectUser(userQuery);
    }

}
