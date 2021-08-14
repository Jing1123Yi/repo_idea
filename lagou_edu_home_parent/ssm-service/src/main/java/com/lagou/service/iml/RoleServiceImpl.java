package com.lagou.service.iml;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> allRole = roleMapper.findAllRole(role);

        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        // 1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        // 2.为角色分配菜单
        for (Integer mid : roleMenuVO.getMenuIdList()) {

            Role_menu_relation relation = new Role_menu_relation();
            relation.setMenuId(mid);
            relation.setRoleId(roleMenuVO.getRoleId());

            // 封装数据
            Date date = new Date();
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);

            relation.setCreatedBy("system");
            relation.setUpdatedby("system");


            roleMapper.roleContextMenu(relation);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {

        // 调用根据roleId清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleId);

        roleMapper.deleteRole(roleId);

    }

    @Override
    public void saveRole(Role role) {
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }
}
