package com.hnust.personnelmanagement.controller;


import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.dto.User;
import com.hnust.personnelmanagement.bean.vo.LoginVO;
import com.hnust.personnelmanagement.bean.vo.Result;
import com.hnust.personnelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/pmsys")
public class UserController {

    @Autowired
    private UserService userService;




    /**
     * 用户登录
     * @param loginVO 登录信息，包含username和password
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVO loginVO) {
        // 调用service层进行用户验证
        User user = userService.login(loginVO.getUsername(), loginVO.getPassword());

        // 如果用户不存在或密码错误，返回错误信息
        if (user == null) {
            return Result.error(400, "用户名或密码错误");
        }

        // 登录成功，返回用户信息
        user.setPassword(null); //密码不返回
        return Result.success("登录成功", user);
    }
}
