package exercise;

import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
// CustomBeanPostProcessor is a class that allows to customize the initialization and destruction of beans
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Object.class);
    private Map<String, Class> beansToInspect = new HashMap<>();
    private Map<String, String> logLevels = new HashMap<>();

    // both methods take bean as an Object and name of the bean in the container as String

    @Override
    // method allows to modify the properties of a bean after ist intantiation
    // "Instantiated" (инстанцирование) means that the bean has been created in memory by the Spring container == create instance of an object
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) { // if the annotation is present in the class
            beansToInspect.put(name, bean.getClass()); // put the class of the bean into a map
            String level = bean.getClass().getAnnotation(Inspect.class).level(); // get 'level' attribute of the annotation
            logLevels.put(name, level); // put it to the map
        }
        return bean;
    }

    // creating dynamic proxies for certain beans in order to intercept method calls and log information about those calls
    @Override
    // method is called when the bean is ready for use after initialization, right after init()
    // provides an opportunity to perform additional processing on the fully initialized bean
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        Class beanClass = beansToInspect.get(name); // retrieve the class object from map of beans to inspect with name
        if (beanClass != null) { // if we find suitable bean
            // create dunamic proxy for the bean to implement the interfaces of the bean class and
            // delegate methods to the bean object
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(), // will be used to define the proxy class == will be responsible for loading the proxy class at runtime
                    beanClass.getInterfaces(), // set of interfaces that the proxy will implement on the getClassLoader()
                    (proxy, method, args) -> { // in which form we pass the data
                        String message = String.format( // message which method was called on the bean object
                                "Was called method: %s() with arguments: %s",
                                method.getName(),
                                Arrays.toString(args)
                        );
                        String logLevel = logLevels.get(name); // depending on the level send logger message
                        if (logLevel.equals("info")) {
                            LOGGER.info(message);
                        } else {
                            LOGGER.debug(message);
                        }
                        return method.invoke(bean, args); // invoke the method on the original bean object
                    }
            );
        }
        return bean;
    }
}
// END
