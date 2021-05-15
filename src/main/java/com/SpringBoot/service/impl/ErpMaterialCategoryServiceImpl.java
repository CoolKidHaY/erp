package com.SpringBoot.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import com.SpringBoot.bean.ErpMaterialCategory;
import com.SpringBoot.bean.User;
import com.SpringBoot.mapper.ErpMaterialCategoryMapper;
import com.SpringBoot.service.IErpMaterialCategoryService;
import com.SpringBoot.utils.Ztree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 材料类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
public class ErpMaterialCategoryServiceImpl extends ServiceImpl<ErpMaterialCategoryMapper, ErpMaterialCategory> implements IErpMaterialCategoryService
{
    @Autowired
    private ErpMaterialCategoryMapper erpMaterialCategoryMapper;

    /**
     * 查询材料类别
     * 
     * @param id 材料类别ID
     * @return 材料类别
     */
    @Override
    public ErpMaterialCategory selectErpMaterialCategoryById(Long id)
    {
        ErpMaterialCategory erpMaterialCategory = this.getBaseMapper().selectById(id);
        // 查询父菜单名称
        ErpMaterialCategory one = this.query().eq("id", erpMaterialCategory.getParentId()).select("name").one();
        erpMaterialCategory.setParentName(one.getName());
        return erpMaterialCategory;
    }

    /**
     * 查询材料类别列表
     * 
     * @param erpMaterialCategory 材料类别
     * @return 材料类别
     */
    @Override
    public List<ErpMaterialCategory> selectErpMaterialCategoryList(ErpMaterialCategory erpMaterialCategory)
    {
        return erpMaterialCategoryMapper.selectErpMaterialCategoryList(erpMaterialCategory);
    }

    /**
     * 新增材料类别
     * 
     * @param erpMaterialCategory 材料类别
     * @return 结果
     */
    @Override
    public int insertErpMaterialCategory(ErpMaterialCategory erpMaterialCategory)
    {
        // 获取当前账户登陆用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpMaterialCategory.setCreateBy(user.getName());
        erpMaterialCategory.setCreateTime(new Date());
        erpMaterialCategory.setDeleteFlag(0);
        return this.getBaseMapper().insert(erpMaterialCategory);
    }

    /**
     * 修改材料类别
     * 
     * @param erpMaterialCategory 材料类别
     * @return 结果
     */
    @Override
    public int updateErpMaterialCategory(ErpMaterialCategory erpMaterialCategory)
    {
        // 获取当前账户登陆用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpMaterialCategory.setUpdateBy(user.getName());
        erpMaterialCategory.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpMaterialCategory);
    }

    /**
     * 删除材料类别对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpMaterialCategoryByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除材料类别信息
     * 
     * @param id 材料类别ID
     * @return 结果
     */
    @Override
    public int deleteErpMaterialCategoryById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }

    /**
     * 查询材料类别树列表
     *
     * @return 所有材料类别信息
     */
    @Override
    public List<Ztree> selectErpMaterialCategoryTree()
    {
        List<ErpMaterialCategory> erpMaterialCategoryList = erpMaterialCategoryMapper.selectErpMaterialCategoryList(new ErpMaterialCategory());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (ErpMaterialCategory erpMaterialCategory : erpMaterialCategoryList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(erpMaterialCategory.getId());
            ztree.setpId(erpMaterialCategory.getParentId());
            ztree.setName(erpMaterialCategory.getName());
            ztree.setTitle(erpMaterialCategory.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * id查询种类名称
     * @param id
     * @return
     */
    @Override
    public String getNameById(Long id) {
        return erpMaterialCategoryMapper.getNameById(id);
    }
}
