package com.SpringBoot.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.SpringBoot.bean.ErpMaterial;
import com.SpringBoot.bean.User;
import com.SpringBoot.mapper.ErpMaterialMapper;
import com.SpringBoot.service.IErpMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 材料Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class ErpMaterialServiceImpl extends ServiceImpl<ErpMaterialMapper, ErpMaterial> implements IErpMaterialService
{
    @Autowired
    private ErpMaterialMapper erpMaterialMapper;

    /**
     * 查询材料
     * 
     * @param id 材料ID
     * @return 材料
     */
    @Override
    public ErpMaterial selectErpMaterialById(Long id)
    {
        ErpMaterial erpMaterial = this.getBaseMapper().selectById(id);
        return erpMaterial;
    }

    /**
     * 查询材料列表
     * 
     * @param erpMaterial 材料
     * @return 材料
     */
    @Override
    public List<ErpMaterial> selectErpMaterialList(ErpMaterial erpMaterial)
    {
        return erpMaterialMapper.selectErpMaterialList(erpMaterial);
    }

    /**
     * 新增材料
     * 
     * @param erpMaterial 材料
     * @return 结果
     */
    @Override
    public int insertErpMaterial(ErpMaterial erpMaterial)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpMaterial.setCreateBy(user.getName());
        erpMaterial.setCreateTime(new Date());
        // 生成材料编号
        StringBuilder sb = new StringBuilder("CL");
        String id = IdUtil.objectId();
        sb.append(id);
        erpMaterial.setCode(sb.toString());
        return this.getBaseMapper().insert(erpMaterial);
    }

    /**
     * 修改材料
     * 
     * @param erpMaterial 材料
     * @return 结果
     */
    @Override
    public int updateErpMaterial(ErpMaterial erpMaterial)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpMaterial.setUpdateBy(user.getName());
        erpMaterial.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpMaterial);
    }

    /**
     * 删除材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpMaterialByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除材料信息
     * 
     * @param id 材料ID
     * @return 结果
     */
    @Override
    public int deleteErpMaterialById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }
}
