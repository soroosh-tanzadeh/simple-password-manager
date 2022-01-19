package main.dependencyInjection;

import main.dependencyInjection.module.IModule;

public class DependencyInjector {
    private DependencyInjector() {
    }

    public static DependencyInjectorFramework getFramework(final IModule module) {
        module.configure();
        return new DependencyInjectorFramework(module);
    }
}
