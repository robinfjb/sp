package chao.java.tools.servicepool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author luqin
 * @since 2019-09-17
 */
public abstract class AbsInitServiceManager extends DefaultService implements InitServiceManager {

    private List<Class<? extends IInitService>> initServices;

    public AbsInitServiceManager() {
        initServices = new ArrayList<>();
    }

    @Override
    public void addInitService(Class<? extends IInitService> initService) {
        initServices.add(initService);
    }

    @Override
    public void initService() {
        Collections.sort(initServices, new Comparator<Class<? extends IInitService>>() {
            @Override
            public int compare(Class<? extends IInitService> aClass, Class<? extends IInitService> t1) {
                return 0;
            }
        });
        for (Class<? extends IInitService> clazz: initServices) {
            //getService会唤起Service的init
            ServicePool.getService(clazz);
        }
    }

    @Override
    public int scope() {
        return Scope.once;
    }
}
