
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsSimulationStoreInfo;
import com.oms.saas.commodity.OmsCommodityApplication;
import com.oms.saas.commodity.dto.SimulationStoreInfoDto;
import com.oms.saas.commodity.mapper.Warehouse.OwnerInfoMapper;
import com.oms.saas.commodity.mapper.Warehouse.WmsSimulationStoreInfoMapper;
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
           //OwnerInfo ownerInfo = ownerInfoMapper.selectOwnerInfoByOwnerCode("XHS02");
           SimulationStoreInfoDto simulationStoreInfo = simulationStoreInfoMapper.selectSimulationStoreInfoWtihOwnerInfo(1);
           System.out.println("ok");
           System.out.println(simulationStoreInfo);
       }catch (Throwable exception){
           System.out.println("fail");
           System.out.println(exception.getCause().getMessage());
       }
    }
}
