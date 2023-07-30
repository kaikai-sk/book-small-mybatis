package cn.bugstack.mybatis.session;

import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话接口
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface SqlSession {
    /**
     * 根据指定的sqlId获取一条记录的封装对象.
     *
     * @param statement sqlId
     * @return 封装之后的对象
     * @param <T>
     */
    <T> T selectOne(String statement);

    /**
     * 根据sqlId获取一条记录的封装对象。此方法允许为sql传递一些参数。
     * 在实际使用时，这个参数传递的一般是pojo、Map或者ImmutableMap。
     *
     * @param statement Unique identifier matching the statement to use。
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object.
     * @param <T> the returned object type.
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * Retrieves a mapper.
     * 使用泛型来保证类型安全。
     *
     * @param type mapper interface class
     * @return a mapper bounded to this SqlSession.
     * @param <T> the mapper type
     */
    <T> T getMapper(Class<T> type);

    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object parameter);

    void close();
}
