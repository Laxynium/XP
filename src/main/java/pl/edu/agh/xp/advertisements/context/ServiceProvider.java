package pl.edu.agh.xp.advertisements.context;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    private static Map<Class<?>, Object> services = new HashMap<>();

    public static void addService(Class<?> type, Object instance){
        services.put(type, instance);
    }

    public static Object getService(Class<?> type){
        return services.get(type);
    }

}
