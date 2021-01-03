package com.wx.watersupplierservice.handle;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBatisJsonHandler<T> extends BaseTypeHandler<T> {
    private Class<T> clazz;

    public MyBatisJsonHandler(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        String json = JSON.toJSONString(t);
        preparedStatement.setString(i, json);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseJson(resultSet.getString(s));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseJson(resultSet.getString(i));
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseJson(callableStatement.getString(i));
    }

    private T parseJson(String json) {
        return JSON.parseObject(json, clazz);
    }
}
