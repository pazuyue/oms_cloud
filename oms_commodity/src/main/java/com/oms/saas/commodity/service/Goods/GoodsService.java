package com.oms.saas.commodity.service.Goods;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Vo.Export.GoodsVO;

import java.util.List;

public interface GoodsService extends IService<GoodsSkuSnInfoTmp> {
    /**
     * 导入
     * @param list
     * @return
     */
    public boolean export(List<GoodsVO> list);

    /**
     * 重新导入
     * @param list
     * @param importBatch
     * @return
     */
    public boolean export(List<GoodsVO> list,String importBatch);

    /**
     * 获取列表
     * @param importBatch
     * @return
     */
    public Page<GoodsSkuSnInfoTmp> exportList(String importBatch,Integer page,Integer pageSize);

    /**
     * 审核
     * @param importBatch
     * @return
     */
    public boolean toExamine(String importBatch);

}
