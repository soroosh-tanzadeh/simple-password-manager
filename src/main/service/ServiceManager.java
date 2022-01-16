package main.service;

import main.dependencyInjection.DependencyInjector;
import main.dependencyInjection.DependencyInjectorFramework;
import main.dependencyInjection.module.Impl.AbstractModule;
import main.service.dataAccess.UserDataAccessService;
import main.service.dataAccess.database.DatabaseInterface;
import main.service.dataAccess.database.FileDatabaseService;

public class ServiceManager extends AbstractModule {
    private static DependencyInjectorFramework framework = null;

    @Override
    public void configure() {
        this.createMapping(UserDataAccessService.class, UserDataAccessService.class);
        this.createMapping(AuthenticationService.class, AuthenticationService.class);

        // Set default database service to FileDatabaseService
        this.createMapping(DatabaseInterface.class, FileDatabaseService.class);
    }

    private static DependencyInjectorFramework getFramework(){
        if(framework == null){
            framework = DependencyInjector.getFramework(new ServiceManager());
        }
        return framework;
    }

    public static <T extends ApplicationService> T getService(Class<? extends ApplicationService> service) throws Exception {
        return (T) getFramework().inject(service);
    }

}
