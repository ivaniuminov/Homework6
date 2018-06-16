package com.iuminov;

import com.iuminov.dao.GenericDao;
import com.iuminov.model.AnotherProduct;
import com.iuminov.model.Product;

public class SimpleTest {

    public static void main(String[] args) throws Exception {
        Product product = new Product(13L, "IPhone", 799.99);
        AnotherProduct anotherProduct = new AnotherProduct(14L, "IPhone", 799.99);

        GenericDao dao = Factory.getGenericDao();
        dao.create(product);
        dao.create(anotherProduct);

        anotherProduct = new AnotherProduct(8L, "Windows", 1900d);
        dao.update(anotherProduct);


    }
}
