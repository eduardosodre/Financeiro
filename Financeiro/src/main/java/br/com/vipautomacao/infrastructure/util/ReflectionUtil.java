package br.com.vipautomacao.infrastructure.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

	public static <T> T clone(T object) {
		T clone = null;
		try {
			clone = (T) object.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		for (Class obj = object.getClass(); !obj.equals(Object.class); obj = obj.getSuperclass()) {
			for (Field field : obj.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					field.set(clone, field.get(object));
				} catch (Throwable t) {
				}
			}
		}
		return clone;
	}
}
