/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onpe.com.pe.transmisionscore.core.repository;

//import com.mycompany.loging.score.Repository.implementacion.ActaServiceImpl;
//import com.mycompany.loging.score.Repository.implementacion.ConexionMongoImpl;
import onpe.com.pe.transmisionscore.core.negocio.service.ActaService;
import onpe.com.pe.transmisionscore.core.repository.service.SettingService;
import onpe.com.pe.transmisionscore.score.repository.implementacion.ActaServiceImpl;
import onpe.com.pe.transmisionscore.score.repository.implementacion.ConexionMongoImpl;
import onpe.com.pe.transmisionscore.score.repository.implementacion.SettingServiceImpl;
//import com.mycompany.loging.score.Repository.implementacion.ImageServiceImpl;
//import com.mycompany.loging.score.Repository.implementacion.SettingServiceImpl;
//import com.mycompany.loging.score.Repository.implementacion.UserServiceImpl;
//import com.mycompany.loging.score.Repository.implementacion.Tess4jServiceImpl;
//import com.mycompany.loging.score.Repository.implementacion.TransmisionServiceImpl;
//import com.mycompany.loging.score.Repository.service.ActaService;
//import com.mycompany.loging.score.Repository.service.ConexionMongo;
//import com.mycompany.loging.score.Repository.service.ImageService;
//import com.mycompany.loging.score.Repository.service.SettingService;
//import com.mycompany.loging.score.Repository.service.UserService;
//import com.mycompany.loging.score.Repository.service.Tess4jService;
//import com.mycompany.loging.score.Repository.service.TransmisionService;

/**
 *
 * @author NHuaman
 */
public class FactoryServiciosExternos {

    private static FactoryServiciosExternos instanceFactory;
    private final ConexionMongo conexionMongo;
//    private final UserService userService;
//    private final Tess4jService tess4jService;
//    private final ImageService imageService;
//    private final TransmisionService transmisionService;
    private final ActaService actaService;
   private final SettingService settingService;

    private FactoryServiciosExternos() {
        this.conexionMongo = new ConexionMongoImpl();
//        this.userService = new UserServiceImpl();
//        this.tess4jService = new Tess4jServiceImpl();
//        this.imageService = new ImageServiceImpl();
//        this.transmisionService = new TransmisionServiceImpl();
        this.actaService = new ActaServiceImpl();
       this.settingService = new SettingServiceImpl();
    }

    public static FactoryServiciosExternos getInstance() {
        if (instanceFactory == null) {
            instanceFactory = new FactoryServiciosExternos();
        }
        return instanceFactory;
    }

    public ConexionMongo MongoService() {
        return this.conexionMongo;
    }


    public ActaService ActaServiceImpl() {
        return this.actaService;
    }

    public SettingService SettingService() {
        return this.settingService;
    }
}
