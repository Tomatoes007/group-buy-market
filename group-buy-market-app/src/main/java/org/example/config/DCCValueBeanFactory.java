package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.types.annotations.DCCValue;
import org.example.types.common.Constants;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DCCValueBeanFactory  implements BeanPostProcessor {

    private final static String BASE_CONFIG_PATH = "group_buy_market_dcc_";

    private final RedissonClient redisson;

    private final Map<String,Object> dccObject=new HashMap<>();

    public DCCValueBeanFactory(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Bean("dccTopic")
    public RTopic testRedisTopicListener(RedissonClient redissonClient) {
        RTopic topic = redissonClient.getTopic("group_buy_market_dcc");
        topic.addListener(String.class, (charSequence, s) -> {
            String[] split = s.split(Constants.SPLIT);

            // 获取值
            String attribute = split[0];
            String key = BASE_CONFIG_PATH + attribute;
            String value = split[1];

            // 设置值
            RBucket<String> bucket = redissonClient.getBucket(key);
            boolean exists = bucket.isExists();
            if (!exists) return;
            bucket.set(value);

            Object objBean = dccObject.get(key);
            if (null == objBean) return;

            Class<?> objBeanClass = objBean.getClass();
            // 检查 objBean 是否是代理对象
            if (AopUtils.isAopProxy(objBean)) {
                // 获取代理对象的目标对象
                objBeanClass = AopUtils.getTargetClass(objBean);
            }

            try {
                // 1. getDeclaredField 方法用于获取指定类中声明的所有字段，包括私有字段、受保护字段和公共字段。
                // 2. getField 方法用于获取指定类中的公共字段，即只能获取到公共访问修饰符（public）的字段。
                Field field = objBeanClass.getDeclaredField(attribute);
                field.setAccessible(true);
                field.set(objBean, value);
                field.setAccessible(false);

                log.info("DCC 节点监听，动态设置值 {} {}", key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return topic;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject=bean;

        if(AopUtils.isAopProxy(bean)){
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBeanObject= AopProxyUtils.getSingletonTarget(bean);
        }

        Field[] feilds = targetBeanClass.getDeclaredFields();
        for (Field f : feilds) {
            if(!f.isAnnotationPresent(DCCValue.class)){
                continue;
            }

            DCCValue dccValue = f.getAnnotation(DCCValue.class);
            String value = dccValue.value();

            if(StringUtils.isBlank(value)){
                throw new RuntimeException("...");
            }

            String[] split=value.split(":");
            String key = BASE_CONFIG_PATH.concat(split[0]);
            String defautValue=split.length==2?split[1]:null;
            String setValue=defautValue;

            try {
                if(StringUtils.isBlank(defautValue)){
                    throw new RuntimeException("...");
                }

                RBucket<String> bucket = redisson.getBucket(key);
                boolean isExist=bucket.isExists();
                if(!isExist){
                    bucket.set(defautValue);
                }else{
                    setValue=bucket.get();
                }
                f.setAccessible(true);
                f.set(targetBeanObject,setValue);
                f.setAccessible(false);

            }catch (Exception e){
                throw new RuntimeException("...");
            }
            dccObject.put(key,targetBeanObject);
        }
        return bean;
    }
}
