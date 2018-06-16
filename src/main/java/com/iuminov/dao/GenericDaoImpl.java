package com.iuminov.dao;

import com.iuminov.annotations.Id;
import com.iuminov.annotations.Name;
import com.iuminov.annotations.Price;
import com.iuminov.model.Product;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenericDaoImpl<T, ID> extends AbstractDao<T, ID> implements GenericDao<T, ID>{

    public GenericDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public T create(T t) throws Exception {
        String query = "INSERT INTO PRODUCTS (NAME, PRICE) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Annotation[] annotations = f.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Name) {
                    preparedStatement.setObject(1, f.get(t));
                }
                if (annotation instanceof Price) {
                    preparedStatement.setObject(2, f.get(t));
                }
            }
        }

        preparedStatement.execute();
        return t;
    }

    @Override
    public T read(ID id) throws Exception{
        String query = "SELECT ID, NAME, PRICE FROM PRODUCTS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        rs.first();
        T instanceT = (T) this.getTypeOfParameter().newInstance();

        Field[] fields = instanceT.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Id) {
                    field.set(instanceT, rs.getLong("ID"));
                }
                if (annotation instanceof Name) {
                    field.set(instanceT, rs.getString("NAME"));
                }
                if (annotation instanceof Price) {
                    field.set(instanceT, rs.getDouble("PRICE"));
                }
            }
        }

        return instanceT;
    }

    @Override
    public T update(T t) throws Exception{
        String query = "UPDATE PRODUCTS SET NAME = ?, PRICE = ? WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Annotation[] annotations = f.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Name) {
                    preparedStatement.setObject(1, f.get(t));
                }
                if (annotation instanceof Price) {
                    preparedStatement.setObject(2, f.get(t));
                }
                if (annotation instanceof Id) {
                    preparedStatement.setObject(3, f.get(t));
                }
            }
        }

        preparedStatement.execute();

        return t;
    }

    @Override
    public void delete(ID id) throws Exception{
        String query = "DELETE FROM PRODUCTS WHERE ID = ?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<T> readAll() throws Exception{

        String query = "SELECT ID, NAME, PRICE FROM PRODUCTS";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        List<T> list = new ArrayList<>();
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()) {
            T instanceT = (T) this.getTypeOfParameter().newInstance();

            Field[] fields = instanceT.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Id) {
                        field.set(instanceT, rs.getLong("ID"));
                    }
                    if (annotation instanceof Name) {
                        field.set(instanceT, rs.getString("NAME"));
                    }
                    if (annotation instanceof Price) {
                        field.set(instanceT, rs.getDouble("PRICE"));
                    }
                }
            }

            list.add(instanceT);
        }

        return list;
    }

    private Class<?> getTypeOfParameter() {
        Class actualType = this.getClass();
        ParameterizedType typeOfSuperclass = (ParameterizedType) actualType.getGenericSuperclass();
        Type[] typesOfArguments = typeOfSuperclass.getActualTypeArguments();
        return  (Class<T>) typesOfArguments[0];
    }
}
