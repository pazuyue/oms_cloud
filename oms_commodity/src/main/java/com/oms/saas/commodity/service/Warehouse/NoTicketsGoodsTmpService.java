package com.oms.saas.commodity.service.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.NoTicketsGoodsTmp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Vo.Export.NoTicketsGoodsTmpVO;

import java.util.List;

/**
 * <p>
 * 入库通知单明细-未送审 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-08-01
 */
public interface NoTicketsGoodsTmpService extends IService<NoTicketsGoodsTmp> {

    public boolean save(List<NoTicketsGoodsTmpVO> list,String no_sn);

}
