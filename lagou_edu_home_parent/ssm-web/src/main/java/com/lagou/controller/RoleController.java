package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /*
        查询所有角色&条件进行查询
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {

        List<Role> allRole = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询成功", allRole);
        return responseResult;

    }

    /*
        查询所有父子菜单信息(分配菜单的第一个接口)
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid() {


        // -1表示查询所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        // 响应数据
        HashMap<Object, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"查询成功",map);

    }

    /*
        根据角色id查询关联的菜单信息id
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"根据角色id查询成功",menuByRoleId);
    }

    /*
        为角色分配菜单信息
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO) {

        roleService.roleContextMenu(roleMenuVO);

        return new ResponseResult(true,200,"为角色分配菜单信息成功",null);
    }

    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {

        roleService.deleteRole(id);

        return new ResponseResult(true,200,"删除角色成功",null);
    }

    /*
        添加&修改角色
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {


        Date date = new Date();
        if (role.getId() == null){
            role.setCreatedTime(date);
            role.setUpdatedTime(date);
            roleService.saveRole(role);
            return new ResponseResult(true,200,"添加角色成功",null);
        } else {
            role.setUpdatedTime(date);
            roleService.updateRole(role);
            return new ResponseResult(true,200,"修改角色成功",null);
        }
    }






}
