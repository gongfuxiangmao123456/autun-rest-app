package com.zdjy.bigdata.antun.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 类转换器
 * @author zdjy
 *
 */
public class TransferUtil {
	/**
     * 类型转换（处理异常）
     *
     * @param dest
     * @param orig
     */
    public static void transfer(Object dest, Object orig) {
        try {
            PropertyUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 类型转换（不处理异常）
     *
     * @param dest
     * @param orig
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void transferThrow(Object dest, Object orig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PropertyUtils.copyProperties(dest, orig);
    }
    
    public static <T> T transfer(Object object,Class<T> class1) {
    	try {
			T newInstance = class1.newInstance();
			transfer(newInstance, object);
			return newInstance;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
}
