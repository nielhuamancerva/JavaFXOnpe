package negocio;

import com.mycompany.loging.score.Repository.FactoryServiciosExternos;
import com.mycompany.loging.score.negocio.NegocioServiceImpl;
import com.mycompany.loging.score.util.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

/**
 *
 * @author NHuaman
 */
@ExtendWith(ApplicationExtension.class)
public class NegocioServiceImplTest {

    private NegocioServiceImpl negocioServiceImpl;
    
    @Test
    public void consultaUsuarioDbTest() throws Exception {
        
             negocioServiceImpl.consultaUsuarioDb("test", "test");
    }
}
