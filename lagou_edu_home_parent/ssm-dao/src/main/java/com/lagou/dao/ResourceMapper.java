package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {


    /*
        资源分页以及多条件查询
     */
    public List<Resource> findAllResourceByPage(ResourceVO resourceVO);

    /*
        添加资源信息
     */
    public void saveResource(Resource resource);

    /*
        回显资源信息
     */
    public Resource findResourceById(Integer id);

    /*
        修改资源信息
     */
    public void updateResource(Resource resource);

    /*
        删除资源信息
     */
    public void deleteResource(Integer id);

}
