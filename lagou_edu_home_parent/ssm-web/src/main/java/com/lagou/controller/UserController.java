package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {

        PageInfo pageInfo = userService.findAllUserByPage(userVO);

        return new ResponseResult(true,200,"分页多条件查询成功",pageInfo);
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status) {

        if ("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }

        userService.updateUserStatus(id,status);

        return new ResponseResult(true,200,"用户状态设置成功",status);
    }

    /*
        用户登录（根据用户名查询具体的用户信息）
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user2 = userService.login(user);

        if (user2 != null){

            // 保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user2.getId());

            // 将查询出来的信息 响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user2.getId());

            // 将查询出来的user，也存到map中
            map.put("uset",user2);


            return new ResponseResult(true,1,"登录成功",map);

        } else {
            return new ResponseResult(true,400,"用户名或密码错误",null);
        }

    }

    /*
        根据用户id查询关联的角色信息
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id) {

        List<Role> list = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"根据用户id查询关联的角色信息成功",list);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO) {

        userService.userContextRole(userVO);

       return new ResponseResult(true,200,"分配角色成功",null);
    }


    /*
        获取用户权限 进行菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        // 2.获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3.判断token是否一致
        if (header_token.equals(session_token)){
            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            // 调用service 进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {
            return new ResponseResult(true,400,"获取菜单信息失败",null);
        }
    }










}
