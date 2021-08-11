package com.lagou.service.iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO) {

        // 分页查询
        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());

        List<Resource> resource = resourceMapper.findAllResourceByPage(resourceVO);

        PageInfo<Resource> pageInfo = new PageInfo<>(resource);

        return pageInfo;
    }

    @Override
    public void saveResource(Resource resource) {
        resourceMapper.saveResource(resource);
    }

    @Override
    public Resource findResourceById(Integer id) {
        Resource resourceById = resourceMapper.findResourceById(id);
        return resourceById;
    }

    @Override
    public void updateResource(Resource resource) {
        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }
}
