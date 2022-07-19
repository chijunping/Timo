package com.linln.admin.quant.controller;

import com.linln.admin.quant.domain.QuantBasePriceDay;
import com.linln.admin.quant.service.QuantBasePriceDayService;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cjp
 * @date 2022/07/19
 */
@Controller
@RequestMapping("/quant/quantBasePriceDay")
public class QuantBasePriceDayController {

    @Autowired
    private QuantBasePriceDayService quantBasePriceDayService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("quant:quantBasePriceDay:index")
    public String index(Model model, QuantBasePriceDay quantBasePriceDay) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching();

        // 获取数据列表
        Example<QuantBasePriceDay> example = Example.of(quantBasePriceDay, matcher);
        Page<QuantBasePriceDay> list = quantBasePriceDayService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/quant/quantBasePriceDay/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("quant:quantBasePriceDay:add")
    public String toAdd() {
        return "/quant/quantBasePriceDay/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("quant:quantBasePriceDay:edit")
    public String toEdit(@PathVariable("id") QuantBasePriceDay quantBasePriceDay, Model model) {
        model.addAttribute("quantBasePriceDay", quantBasePriceDay);
        return "/quant/quantBasePriceDay/add";
    }



    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("quant:quantBasePriceDay:detail")
    public String toDetail(@PathVariable("id") QuantBasePriceDay quantBasePriceDay, Model model) {
        model.addAttribute("quantBasePriceDay",quantBasePriceDay);
        return "/quant/quantBasePriceDay/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("quant:quantBasePriceDay:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (quantBasePriceDayService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}