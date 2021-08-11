package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /*
        资源分页以及多条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVO resourceVO) {

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVO);
        return new ResponseResult(true,200,"资源信息分页多条件查询成功",pageInfo);
    }

    /*
        新建&修改资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        Date date = new Date();
        if (resource.getId() == null){

            resource.setCreatedTime(date);
            resource.setUpdatedTime(date);

            resourceService.saveResource(resource);

            return new ResponseResult(true,200,"新建资源信息成功",null);

        } else {

            resource.setUpdatedTime(date);
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改资源信息成功",null);
        }

    }

    /*
        删除资源信息
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除资源信息成功",null);
    }


}
