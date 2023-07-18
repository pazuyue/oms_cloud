package com.oms.saas.commodity.service.Warehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;

public interface OwnerInfoService  extends IService<OwnerInfo> {

    public boolean save(OwnerInfoVO vo);

    public OwnerInfo findOneByOwnerInfoVO(OwnerInfoVO vo);

    public Page<OwnerInfo> list(String owner_code,Integer page,Integer pageSize);
}
