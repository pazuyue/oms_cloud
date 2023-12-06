
import com.oms.saas.commodity.OmsCommodityApplication;
import com.oms.saas.commodity.dto.Store.OwnerInfoDto;
import com.oms.saas.commodity.dto.Store.SimulationStoreInfoDto;
import com.oms.saas.commodity.mapper.Warehouse.OwnerInfoMapper;
import com.oms.saas.commodity.mapper.Warehouse.WmsSimulationStoreInfoMapper;
import com.oms.saas.commodity.service.impl.Warehouse.WmsSimulationStoreInfoServiceImpl;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OmsCommodityApplication.class})
public class MapperTest {

    @Resource
    private WmsSimulationStoreInfoMapper simulationStoreInfoMapper;
    @Resource
    private OwnerInfoMapper ownerInfoMapper;

    @Test
    public void testLeftJoin(){
       try {
           //OwnerInfoDto ownerInfo = ownerInfoMapper.selectOwnerInfoByOwnerCodeWithRealStore("XHS01");
           SimulationStoreInfoDto simulationStoreInfo = simulationStoreInfoMapper.selectSimulationStoreInfoWtihOwnerInfo("VC0001");
           System.out.println(simulationStoreInfo.getOwnerInfo().getRealStoreInfo());
       }catch (Throwable exception){
           System.out.println("fail"+exception.getCause().getMessage());
       }
    }
}
