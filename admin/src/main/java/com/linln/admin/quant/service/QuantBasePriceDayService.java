package com.linln.admin.quant.service;

import com.linln.admin.quant.domain.QuantBasePriceDay;
import com.linln.common.enums.StatusEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjp
 * @date 2022/07/19
 */
public interface QuantBasePriceDayService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<QuantBasePriceDay> getPageList(Example<QuantBasePriceDay> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    QuantBasePriceDay getById(Long id);

    /**
     * 保存数据
     * @param quantBasePriceDay 实体对象
     */
    QuantBasePriceDay save(QuantBasePriceDay quantBasePriceDay);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}