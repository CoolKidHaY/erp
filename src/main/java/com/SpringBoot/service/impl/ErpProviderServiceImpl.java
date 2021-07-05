package com.SpringBoot.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.SpringBoot.bean.ErpProvider;
import com.SpringBoot.bean.User;
import com.SpringBoot.mapper.ErpProviderMapper;
import com.SpringBoot.service.IErpProviderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 供应商Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
@Service
public class ErpProviderServiceImpl extends ServiceImpl<ErpProviderMapper, ErpProvider> implements IErpProviderService
{
    @Autowired
    private ErpProviderMapper erpProviderMapper;

    /**
     * 查询供应商
     * 
     * @param id 供应商ID
     * @return 供应商
     */
    @Override
    public ErpProvider selectErpProviderById(Long id)
    {
        return this.getBaseMapper().selectById(id);
    }

    /**
     * 查询供应商列表
     * 
     * @param erpProvider 供应商
     * @return 供应商
     */
    @Override
    public Page<ErpProvider> selectErpProviderList(ErpProvider erpProvider)
    {
        QueryWrapper<ErpProvider> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(erpProvider.getName()), "name", erpProvider.getName())
                .like(StrUtil.isNotBlank(erpProvider.getContacts()), "contacts", erpProvider.getContacts())
                .like(StrUtil.isNotBlank(erpProvider.getPhoneNum()), "phone_num", erpProvider.getPhoneNum())
                .like(StrUtil.isNotBlank(erpProvider.getAddress()), "address", erpProvider.getAddress())
                .eq("type", erpProvider.getType())
                .orderByDesc("create_time");
        return this.getBaseMapper().selectPage(new Page<>(erpProvider.getPageNum(), erpProvider.getPageSize()), queryWrapper);
    }

    /**
     * 新增供应商
     * 
     * @param erpProvider 供应商
     * @return 结果
     */
    @Override
    public int insertErpProvider(ErpProvider erpProvider)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProvider.setCreateBy(user.getName());
        erpProvider.setCreateTime(new Date());
        return this.getBaseMapper().insert(erpProvider);
    }

    /**
     * 修改供应商
     * 
     * @param erpProvider 供应商
     * @return 结果
     */
    @Override
    public int updateErpProvider(ErpProvider erpProvider)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProvider.setUpdateBy(user.getName());
        erpProvider.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpProvider);
    }

    /**
     * 删除供应商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpProviderByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除供应商信息
     * 
     * @param id 供应商ID
     * @return 结果
     */
    @Override
    public int deleteErpProviderById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }
}
