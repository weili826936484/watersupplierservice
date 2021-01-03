package com.wx.watersupplierservice.handle;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisJsonListHandler<T> extends BaseTypeHandler<List<T>> {
    private Class<T> clazz;
    private TypeReference<ArrayList<T>> typeRef;

    public MyBatisJsonListHandler(Class<T> clazz) {
        this.clazz = clazz;
        this.typeRef = new TypeReference<ArrayList<T>>(){};
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<T> ts, JdbcType jdbcType) throws SQLException {
        String json = JSON.toJSONString(ts);
        preparedStatement.setString(i, json);
    }

    @Override
    public List<T> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseJson(resultSet.getString(s));
    }

    @Override
    public List<T> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseJson(resultSet.getString(i));
    }

    @Override
    public List<T> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseJson(callableStatement.getString(i));
    }

    private List<T> parseJson(String json) {
        return JSON.parseArray(json, clazz);
    }
}
