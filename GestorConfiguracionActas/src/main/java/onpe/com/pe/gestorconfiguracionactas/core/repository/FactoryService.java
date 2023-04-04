package onpe.com.pe.gestorconfiguracionactas.core.repository;

import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.MongoServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.SectionsServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.SettingServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.Tess4jServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.UserServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.MongoService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.SectionsService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.SettingService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.Tess4jService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.UserService;

/**
 *
 * @author NHuaman
 */
public class FactoryService {

    private static FactoryService instanceFactory;
    private final MongoService mongoService;
    private final SettingService settingService;
    private final UserService userService;
    private final Tess4jService tess4jService;
    private final SectionsService sectionsService;

    private FactoryService() {
        this.mongoService = new MongoServiceImpl();
        this.settingService = new SettingServiceImpl();
        this.userService = new UserServiceImpl();
        this.tess4jService = new Tess4jServiceImpl();
        this.sectionsService = new SectionsServiceImpl();
    }

    public static FactoryService getInstance() {
        if (instanceFactory == null) {
            instanceFactory = new FactoryService();
        }
        return instanceFactory;
    }

    public MongoService MongoService() {
        return this.mongoService;
    }

    public SettingService SettingService() {
        return this.settingService;
    }

    public UserService UserService() {
        return this.userService;
    }

    public Tess4jService Tess4jService() {
        return this.tess4jService;
    }

    public SectionsService SectionsService() {
        return this.sectionsService;
    }
}
