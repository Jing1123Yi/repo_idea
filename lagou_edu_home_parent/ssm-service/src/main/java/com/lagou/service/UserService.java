package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {

    /*
        用户分页以及多条件查询
     */
    public PageInfo findAllUserByPage(UserVO userVO);

    /*
        用户状态设置
     */
    public void updateUserStatus(Integer id,String status);

    /*
        用户登录（根据用户名查询具体的用户信息）
     */
    public User login(User user) throws Exception;

    /*
        根据用户id查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
        用户关联角色
     */
    public void userContextRole(UserVO userVO);

    /*
        获取用户权限 进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userId);

}
