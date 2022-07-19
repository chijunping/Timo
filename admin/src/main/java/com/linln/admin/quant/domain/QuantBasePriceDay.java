package com.linln.admin.quant.domain;

import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cjp
 * @date 2022/07/19
 */
@Data
@Entity
@Table(name="quant_quant_base_price_day")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.NOT_DELETE)
public class QuantBasePriceDay implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // 标题
    private String trade_date;
    // 备注
    private String stock_code;
    // 创建时间
    private String stock_name;
    // 更新时间
    private Double close;
    private Integer status;
}