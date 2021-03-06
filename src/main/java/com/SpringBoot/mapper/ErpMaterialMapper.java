package com.SpringBoot.mapper;

import java.util.List;

import com.SpringBoot.bean.ErpMaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 材料Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface ErpMaterialMapper extends BaseMapper<ErpMaterial>
{
    /**
     * 查询材料
     * 
     * @param id 材料ID
     * @return 材料
     */
    public ErpMaterial selectErpMaterialById(Long id);

    /**
     * 查询材料列表
     * 
     * @param erpMaterial 材料
     * @return 材料集合
     */
    public List<ErpMaterial> selectErpMaterialList(ErpMaterial erpMaterial);

    /**
     * 新增材料
     * 
     * @param erpMaterial 材料
     * @return 结果
     */
    public int insertErpMaterial(ErpMaterial erpMaterial);

    /**
     * 修改材料
     * 
     * @param erpMaterial 材料
     * @return 结果
     */
    public int updateErpMaterial(ErpMaterial erpMaterial);

    /**
     * 删除材料
     * 
     * @param id 材料ID
     * @return 结果
     */
    public int deleteErpMaterialById(Long id);

    /**
     * 批量删除材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpMaterialByIds(String[] ids);
}
