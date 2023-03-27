package onpe.com.pe.gestorconfiguracionactas.core.repository;

import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.MongoServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.SettingServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.impl.UserServiceImpl;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.MongoService;
import onpe.com.pe.gestorconfiguracionactas.core.repository.service.SettingService;
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

    private FactoryService() {
        this.mongoService = new MongoServiceImpl();
        this.settingService = new SettingServiceImpl();
        this.userService = new UserServiceImpl();
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
}
