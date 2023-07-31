package cn.bugstack.mybatis.transaction;

import cn.bugstack.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

public interface TransactionFactory {
    /**
     * 根据Connection创建Transaction
     * @param conn Existing database connection
     * @return {@link Transaction}
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据数据库和事务隔离级别创建Transaction
     * @param dataSource DataSource to take the connection from
     * @param level Desired isolation level
     * @param autoCommit Desired autocommit
     * @return {@link Transaction}
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
