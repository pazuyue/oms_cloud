package com.oms.saas.commodity.Controller.Goods;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.oms.saas.commodity.Vo.Export.Goods;
import com.oms.saas.commodity.api.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @PostMapping(value = "/export")
    @ResponseBody
    public Result export(@RequestParam(value = "file", required = true) MultipartFile file) {
        ImportParams params = new ImportParams();

        try {
            List<Goods> goodsList = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    Goods.class, params);
            return Result.success(goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failed("导入失败！");
        }

    }
}
