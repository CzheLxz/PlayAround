package com.czhe.sysmanage.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/16 15:15
 * @description
 **/
public class ObjectCopy {

    private static final String CLASS = "class";


    /**
     * @param source           源对象
     * @param target           目标
     * @param ignoreProperties 忽略的属性
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) throws Exception {
        if (source.getClass().isInterface() || target.getClass().isInterface()) {
            throw new Exception("source和target不能为接口");
        }
        BeanWrapper sourceWrap = createBeanWrap(source);
        BeanWrapper targetWrap = createBeanWrap(target);
        PropertyDescriptor[] sourcePds = sourceWrap.getPropertyDescriptors();
        PropertyDescriptor[] targetPds = targetWrap.getPropertyDescriptors();
        //根据名称分组减少一层循环
        Map<String, List<PropertyDescriptor>> map = Arrays.asList(sourcePds).parallelStream().filter(o -> !StringUtils.equals(CLASS, o.getName())).collect(Collectors.groupingBy(o -> o.getName()));
        for (int i = 0; i < targetPds.length; i++) {
            PropertyDescriptor tpd = targetPds[i];
            //去掉class属性和忽略的属性
            if (!StringUtils.equals(CLASS, tpd.getName()) && !Arrays.asList(ignoreProperties).contains(tpd.getName())) {
                List<PropertyDescriptor> list = map.getOrDefault(tpd.getName(), null);
                if (Objects.nonNull(list)) {
                    Method writeMethod = tpd.getWriteMethod();
                    Method readMethod = list.get(0).getReadMethod();
                    if (Objects.isNull(writeMethod) || Objects.isNull(readMethod)) {
                        throw new Exception("属性必须有get和set方法");
                    }
                    Object o = readMethod.invoke(source);
                    writeMethod.invoke(target, o);
                }
            }
        }

    }

    public static BeanWrapper createBeanWrap(Object o) {
        return new BeanWrapperImpl(o);
    }
}