package com.wyj.ytyn.utils;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * spring 工具类，便于非spring管理环境获取Bean
 */
@Component
public class SpringUtils implements BeanFactoryPostProcessor {


    /**
     * spring应用的上下文
     */
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtils.beanFactory = configurableListableBeanFactory;
    }

    /**
     * 获取对象
     *
     * @param name beanName
     * @param <T>  type
     * @return bean名实例
     * @throws BeansException e
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取指定类型对象
     *
     * @param clz class
     * @param <T> type
     * @return bean
     * @throws BeansException e
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        return beanFactory.getBean(clz);
    }

    /**
     * 获取aop代理对象
     *
     * @param invoker invoker
     * @param <T>     type
     * @return proxy
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }
}
