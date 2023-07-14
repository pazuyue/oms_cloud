package com.oms.saas.commodity.service.Goods;

public interface GoodsSizeService {

    /**
     * 根据尺码名称返回尺码CODE，如果不存在，新增
     * @param size_name
     * @return
     */
    public Integer selectOrSaveBySizeName(String size_name);
}
