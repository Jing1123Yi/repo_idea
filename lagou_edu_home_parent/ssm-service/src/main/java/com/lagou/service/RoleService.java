package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色&条件进行查询
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色id查询该角色关联的菜单信息id [1 2 3 4]
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        为角色分配菜单信息
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /*
        删除角色
     */
    public void deleteRole(Integer roleId);



}
