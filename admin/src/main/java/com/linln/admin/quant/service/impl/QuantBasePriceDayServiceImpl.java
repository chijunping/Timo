package com.linln.admin.quant.service.impl;

import com.linln.admin.quant.domain.QuantBasePriceDay;
import com.linln.admin.quant.repository.QuantBasePriceDayRepository;
import com.linln.admin.quant.service.QuantBasePriceDayService;
import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjp
 * @date 2022/07/19
 */
@Service
public class QuantBasePriceDayServiceImpl implements QuantBasePriceDayService {

    @Autowired
    private QuantBasePriceDayRepository quantBasePriceDayRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    public QuantBasePriceDay getById(Long id) {
        return quantBasePriceDayRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<QuantBasePriceDay> getPageList(Example<QuantBasePriceDay> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return quantBasePriceDayRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param quantBasePriceDay 实体对象
     */
    @Override
    public QuantBasePriceDay save(QuantBasePriceDay quantBasePriceDay) {
        return quantBasePriceDayRepository.save(quantBasePriceDay);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return quantBasePriceDayRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}