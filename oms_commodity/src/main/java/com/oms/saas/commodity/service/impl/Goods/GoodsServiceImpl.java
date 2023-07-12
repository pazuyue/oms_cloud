package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.lang.Console;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Vo.Export.GoodsVO;
import com.oms.saas.commodity.mapper.Goods.GoodsSkuSnInfoTmpMapper;
import com.oms.saas.commodity.service.Goods.GoodsService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsSkuSnInfoTmpMapper,GoodsSkuSnInfoTmp> implements GoodsService {

    public boolean export(List<GoodsVO> list) {
        List<GoodsSkuSnInfoTmp> goodsSkuSnInfoTmpList = new ArrayList<>();
        list.forEach(vo->{
            String s = this.checkGoodsVO(vo);
            GoodsSkuSnInfoTmp goodsSkuSnInfoTmp = new GoodsSkuSnInfoTmp();
            goodsSkuSnInfoTmp.setSkuSn(vo.getGoodsSn());
            goodsSkuSnInfoTmp.setGoodsSn(vo.getGoodsSn());
            goodsSkuSnInfoTmp.setBarcodeSn(vo.getBarcodeSn());
            goodsSkuSnInfoTmp.setGoodsName(vo.getGoodsName());
            goodsSkuSnInfoTmp.setCategoryCode(vo.getCategoryName());
            goodsSkuSnInfoTmp.setColorCode(vo.getColorName());
            goodsSkuSnInfoTmp.setSizeCode(vo.getSizeName());
            goodsSkuSnInfoTmp.setMarketPrice(vo.getMarketPrice());
            goodsSkuSnInfoTmp.setGoodsDesc(vo.getDescription());
            goodsSkuSnInfoTmp.setNotes(s);
            goodsSkuSnInfoTmpList.add(goodsSkuSnInfoTmp);
        });
        Console.log(goodsSkuSnInfoTmpList);
        return this.saveBatch(goodsSkuSnInfoTmpList);
    }


    /**
     * 校验导入文件
     * @param vo
     * @return
     */
    public String checkGoodsVO(GoodsVO vo)
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<GoodsVO>> violations = validator.validate(vo);
        Console.log(violations);
        if (!violations.isEmpty()) {
            ArrayList<String> messages = new ArrayList<>();
            for (ConstraintViolation<GoodsVO> violation : violations) {
                String message = violation.getMessage(); // 获取错误消息
                messages.add(message);
            }
            return messages.toString();
        }
        return "正常";
    }
}
