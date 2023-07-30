package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.session.SqlSession;
import cn.hutool.core.lang.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {
    /**
     * 将已添加的mapper代理加入到HashMap的缓存中
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception exception) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + exception, exception);
        }
    }

    public <T> void addMapper(Class<T> type) {
        // Mapper必须是接口才可能被注册。
        if (!type.isInterface()) {
            return;
        }
        // 如果重复添加，直接报错
        if (hasMapper(type)) {
            throw new RuntimeException("Type " + type + "is already known to the MapperRegistry.");
        }
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }
}
