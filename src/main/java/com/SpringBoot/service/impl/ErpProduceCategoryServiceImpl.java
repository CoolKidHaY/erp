package com.SpringBoot.service.impl;

import cn.hutool.core.convert.Convert;
import com.SpringBoot.bean.ErpProduceCategory;
import com.SpringBoot.bean.User;
import com.SpringBoot.mapper.ErpProduceCategoryMapper;
import com.SpringBoot.service.IErpProduceCategoryService;
import com.SpringBoot.utils.Ztree;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 产品类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
public class ErpProduceCategoryServiceImpl extends ServiceImpl<ErpProduceCategoryMapper, ErpProduceCategory> implements IErpProduceCategoryService
{
    @Autowired
    private ErpProduceCategoryMapper erpProduceCategoryMapper;

    /**
     * 查询产品类别
     * 
     * @param id 产品类别ID
     * @return 产品类别
     */
    @Override
    public ErpProduceCategory selectErpProduceCategoryById(Long id)
    {
        return erpProduceCategoryMapper.selectErpProduceCategoryById(id);
    }

    /**
     * 查询产品类别列表
     * 
     * @param erpProduceCategory 产品类别
     * @return 产品类别
     */
    @Override
    public List<ErpProduceCategory> selectErpProduceCategoryList(ErpProduceCategory erpProduceCategory)
    {
        return erpProduceCategoryMapper.selectErpProduceCategoryList(erpProduceCategory);
    }

    /**
     * 新增产品类别
     * 
     * @param erpProduceCategory 产品类别
     * @return 结果
     */
    @Override
    public int insertErpProduceCategory(ErpProduceCategory erpProduceCategory)
    {
        // 获取当前账户登陆用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProduceCategory.setCreateBy(user.getName());
        erpProduceCategory.setCreateTime(new Date());
        erpProduceCategory.setDeleteFlag(0);
        return this.getBaseMapper().insert(erpProduceCategory);
    }

    /**
     * 修改产品类别
     * 
     * @param erpProduceCategory 产品类别
     * @return 结果
     */
    @Override
    public int updateErpProduceCategory(ErpProduceCategory erpProduceCategory)
    {
        // 获取当前账户登陆用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProduceCategory.setUpdateBy(user.getName());
        erpProduceCategory.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpProduceCategory);
    }

    /**
     * 删除产品类别对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpProduceCategoryByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除产品类别信息
     * 
     * @param id 产品类别ID
     * @return 结果
     */
    @Override
    public int deleteErpProduceCategoryById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }

    /**
     * 查询产品类别树列表
     *
     * @return 所有产品类别信息
     */
    @Override
    public List<Ztree> selectErpProduceCategoryTree()
    {
        List<ErpProduceCategory> erpProduceCategoryList = erpProduceCategoryMapper.selectErpProduceCategoryList(new ErpProduceCategory());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (ErpProduceCategory erpProduceCategory : erpProduceCategoryList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(erpProduceCategory.getId());
            ztree.setpId(erpProduceCategory.getParentId());
            ztree.setName(erpProduceCategory.getName());
            ztree.setTitle(erpProduceCategory.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public String getNameById(Long id) {

        return erpProduceCategoryMapper.getNameById(id);
    }
}
