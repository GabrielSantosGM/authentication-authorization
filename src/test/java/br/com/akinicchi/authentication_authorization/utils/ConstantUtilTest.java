package br.com.akinicchi.authentication_authorization.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConstantUtilTest {

    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException {
        Constructor<ConstantUtil> constructor = ConstantUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
}