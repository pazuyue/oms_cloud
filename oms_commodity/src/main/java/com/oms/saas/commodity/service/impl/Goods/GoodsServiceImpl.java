package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Vo.Export.GoodsVO;
import com.oms.saas.commodity.mapper.Goods.GoodsSkuSnInfoTmpMapper;
import com.oms.saas.commodity.service.Goods.GoodsColorService;
import com.oms.saas.commodity.service.Goods.GoodsService;
import com.oms.saas.commodity.service.Goods.GoodsSizeService;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsSkuSnInfoTmpMapper,GoodsSkuSnInfoTmp> implements GoodsService {

    @Resource
    GoodsColorService goodsColorService;

    @Resource
    GoodsSizeService goodsSizeService;

    @Override
    public boolean export(List<GoodsVO> list) {
        return this.saveGoodsSkuSnInfoTmp(list);
    }

    @Override
    public boolean export(List<GoodsVO> list, String importBatch) {
        if (!ObjectUtil.isEmpty(importBatch))
        {
            QueryWrapper<GoodsSkuSnInfoTmp> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("import_batch",importBatch);
            this.remove(queryWrapper);
        }
        return this.saveGoodsSkuSnInfoTmp(list);
    }

    @Override
    public Page<GoodsSkuSnInfoTmp> exportList(String importBatch,Integer page,Integer pageSize) {
        QueryWrapper<GoodsSkuSnInfoTmp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("import_batch",importBatch);
        return this.page(new Page<>(page, pageSize),queryWrapper);
    }

    @Override
    public boolean toExamine(String importBatch) {
        QueryWrapper<GoodsSkuSnInfoTmp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notes","正常");
        GoodsSkuSnInfoTmp infoTmp = this.getOne(queryWrapper);
        if (!ObjectUtil.isEmpty(infoTmp)){
            throw new RuntimeException("审核失败，请先处理异常导入信息");
        }
        List<GoodsSkuSnInfoTmp> list = this.list(queryWrapper);
        for(GoodsSkuSnInfoTmp tmp:list) {
            Integer colorCode = goodsColorService.selectOrSaveByColorName(tmp.getColorCode());
            if (ObjectUtil.isEmpty(colorCode))
                throw new RuntimeException("审核失败，色号处理异常");
            Integer sizeCode = goodsSizeService.selectOrSaveBySizeName(tmp.getSizeCode());
            if (ObjectUtil.isEmpty(sizeCode))
                throw new RuntimeException("审核失败，尺码处理异常");

        }
        return false;
    }



    public boolean saveGoodsSkuSnInfoTmp(List<GoodsVO> list)
    {
        List<GoodsSkuSnInfoTmp> goodsSkuSnInfoTmpList = new ArrayList<>();
        String importBatch = IdUtil.simpleUUID();
        list.forEach(vo->{
            String s = this.checkGoodsVO(vo);
            GoodsSkuSnInfoTmp goodsSkuSnInfoTmp = new GoodsSkuSnInfoTmp();
            goodsSkuSnInfoTmp.setImportBatch(importBatch);
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
