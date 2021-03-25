package com.SpringBoot.service.impl;

import com.SpringBoot.bean.LogInfo;
import com.SpringBoot.mapper.LogInfoMapper;
import com.SpringBoot.service.LogInfoService;
import com.SpringBoot.vo.LogInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements LogInfoService {

	/**
	 * 查询日志
	 * @param vo
	 * @return
	 */
	@Override
	public IPage<LogInfo> selectLogInfo(LogInfoVo vo) {
		QueryWrapper<LogInfo> queryWrapper = new QueryWrapper<LogInfo>().like(vo.getLoginName() != null, "login_name", vo.getLoginName())
				.like(vo.getLoginIp() != null, "login_ip", vo.getLoginIp());
		return this.baseMapper.selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()), queryWrapper);
	}

	/**
	 * id删除日志
	 * @param id
	 * @return
	 */
	@Override
	public int deleteById(Long id) {

		return this.getBaseMapper().deleteById(id);
	}

	/**
	 * 批量删除
	 * @param vo
	 * @return
	 */
	@Override
	public int batchDeleteLogInfo(LogInfoVo vo) {
		List<Long> ids = Arrays.asList(vo.getIds());
		return this.getBaseMapper().deleteBatchIds(ids);
	}

	@Override
	public int insertLogIfo(LogInfo logInfo) {
		logInfo.setLoginTime(LocalDateTime.now());
		return this.baseMapper.insert(logInfo);
	}
}
