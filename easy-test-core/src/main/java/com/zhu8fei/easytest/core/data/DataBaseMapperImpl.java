package com.zhu8fei.easytest.core.data;

import com.zhu8fei.easytest.core.bean.SimpleTable;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * 默认 jdbc 处理
 * Created by zhu8fei on 2017/7/24.
 */
public class DataBaseMapperImpl implements DataBaseMapper {
    private Logger logger = LoggerFactory.getLogger(DataBaseMapperImpl.class);

    private Connection connection;
    /**
     * 数据源，static
     */
    private DataSource dataSource;

    /**
     * 默认的构造函数
     */
    public DataBaseMapperImpl() {
        initDataSource();
        connection = getConnection();
    }

    @Override
    public void insert(SimpleTable simpleTable) {
        PreparedStatement prepareStatement = null;
        try {
            StringBuilder sql = new StringBuilder("insert into ");
            sql.append(simpleTable.getTableName());

            sql.append("(");
            StringBuilder insert = new StringBuilder();
            for (String column : simpleTable.getColumns()) {
                sql.append(column).append(",");
                insert.append("?").append(",");
            }
            insert.deleteCharAt(insert.length() - 1);
            sql.deleteCharAt(sql.length() - 1);
            sql.append(") values ( ").append(insert).append(")");
            prepareStatement = connection.prepareStatement(sql.toString());
            List<String> columns = simpleTable.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                prepareStatement.setObject(i + 1, columns.get(i));
            }
            int result = prepareStatement.executeUpdate();
            logger.debug("insert into {} line", result);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            safeClose(prepareStatement);
            safeClose(connection);
        }
    }

    @Override
    public List<Map<String, String>> select(SimpleTable simpleTable) {

        StringBuilder sql = new StringBuilder("select ");
        List<String> columns = simpleTable.getColumns();
        for (String column : columns) {
            sql.append(column).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from ").append(simpleTable.getTableName());


        Map<String, Object> params = simpleTable.getParam();
        if (params.size() > 0) {
            sql.append(" where ");
        }
        Set<String> keys = params.keySet();

        for (String key : keys) {
            Object param = params.get(key);
            if (param instanceof Object[]) {
                sql.append(" in (");
                Object[] paramArr = (Object[]) param;
                for (Object aParamArr : paramArr) {
                    sql.append("'").append(aParamArr).append("',");
                }
                sql.deleteCharAt(sql.length() - 1);
                sql.append(" )  and");

            } else if (param instanceof String && ((String) param).contains("%")) {
                sql.append(key).append(" like '").append(param).append("'  and");
            } else {
                sql.append(key).append("='").append(param).append("'  and");
            }
        }
        sql.deleteCharAt(sql.length() - 3);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql.toString());
            ResultSetMetaData metaData = rs.getMetaData();
            List<Map<String, String>> table = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= columns.size(); i++) {
                    String column = metaData.getColumnName(i);
                    row.put(column, (String) rs.getObject(column));
                }
                table.add(row);
            }
            return table;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            safeClose(rs);
            safeClose(stmt);
            safeClose(connection);
        }

        return null;
    }

    private void safeClose(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            // do
        }
    }

    /**
     * 从数据源获得一个连接
     */
    private Connection getConnection() {
        if (dataSource != null) {
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return connection;
    }


    /**
     * 指定所有参数连接数据源
     */
    private void initDataSource() {
        /**
         * @param connectURI    数据库
         * @param username      用户名
         * @param password      密码
         * @param driverClass   数据库连接驱动名
         * @param initialSize   初始连接池连接个数
         * @param maxTotal      最大活动连接数
         * @param maxIdle       最大连接数
         * @param maxWaitMillis 获得连接的最大等待毫秒数
         * @param minIdle       最小连接数
         */
        BasicDataSource basicDataSource = new BasicDataSource();
        String connectURI = System.getProperty("easy.test.base.db.url");
        if (StringUtils.isNotEmpty(connectURI)) {
            connectURI = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&aut";
        }
        basicDataSource.setUrl(connectURI);
        String username = System.getProperty("easy.test.base.db.username");
        if (StringUtils.isNotEmpty(username)) {
            username = "root";
        }
        basicDataSource.setUsername(username);
        String password = System.getProperty("easy.test.base.db.password");
        if (StringUtils.isNotEmpty(password)) {
            password = "";
        }
        basicDataSource.setPassword(password);
        String driverClass = System.getProperty("easy.test.base.db.driver");
        if (StringUtils.isNotEmpty(driverClass)) {
            driverClass = "com.mysql.jdbc.Driver";
        }
        basicDataSource.setDriverClassName(driverClass);
        int initialSize = 5;
        String initialSizeStr = System.getProperty("easy.test.base.db.initSize");
        if (StringUtils.isNotEmpty(initialSizeStr)) {
            initialSize = Integer.parseInt(initialSizeStr);
        }
        basicDataSource.setInitialSize(initialSize); // 初始的连接数；
        int maxTotal = 10;
        String maxTotalStr = System.getProperty("easy.test.base.db.maxTotal");
        if (StringUtils.isNotEmpty(maxTotalStr)) {
            maxTotal = Integer.parseInt(maxTotalStr);
        }
        basicDataSource.setMaxTotal(maxTotal);
        int maxIdle = 5;
        String maxIdleStr = System.getProperty("easy.test.base.db.maxIdle");
        if (StringUtils.isNotEmpty(maxIdleStr)) {
            maxIdle = Integer.parseInt(maxIdleStr);
        }
        basicDataSource.setMaxIdle(maxIdle);
        int maxWaitMillis = 5;
        String maxWaitMillisStr = System.getProperty("easy.test.base.db.maxWaitMillis");
        if (StringUtils.isNotEmpty(maxWaitMillisStr)) {
            maxWaitMillis = Integer.parseInt(maxWaitMillisStr);
        }
        basicDataSource.setMaxWaitMillis(maxWaitMillis);
        int minIdle = 1;
        String minIdleStr = System.getProperty("easy.test.base.db.minIdle");
        if (StringUtils.isNotEmpty(minIdleStr)) {
            minIdle = Integer.parseInt(minIdleStr);
        }
        basicDataSource.setMinIdle(minIdle);
        dataSource = basicDataSource;
    }
}
