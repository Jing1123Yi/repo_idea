package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceService {

    /*
        资源分页以及多条件查询
    */
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);


    /*
        添加&资源信息
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
