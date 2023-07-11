package com.oms.saas.commodity.Controller.Goods;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.oms.saas.commodity.Vo.Export.GoodsVO;
import com.oms.saas.commodity.api.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 商品导入
     * @param file
     * @return
     */
    @PostMapping(value = "/export")
    @ResponseBody
    public Result export(@RequestParam(value = "file", required = true) MultipartFile file) {
        ImportParams params = new ImportParams();

        try {
            List<GoodsVO> goodsList = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    GoodsVO.class, params);
            return Result.success(goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failed("导入失败！");
        }

    }
}
