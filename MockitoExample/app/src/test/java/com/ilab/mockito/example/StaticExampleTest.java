package com.ilab.mockito.example;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * Created by cuijfboy on 15/11/14.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StaticExample.class})
public class StaticExampleTest {
    @Test
    public void stubAndVerifyPublicStaticFiled() throws Exception {
        // prepare
        Object object = new Object();

        // stub
        StaticExample.publicField = object;

        //assert
        Assert.assertThat(StaticExample.publicField, CoreMatchers.equalTo(object));
    }

    @Test
    public void stubAndVerifyPrivateStaticField() throws Exception {
        // prepare
        Object object = new Object();

        // stub
        Whitebox.setInternalState(StaticExample.class, "privateField", object);

        // assert
        Object output = Whitebox.getInternalState(StaticExample.class, "privateField");
        Assert.assertThat(output, CoreMatchers.equalTo(object));
    }

    @Test
    public void stubAndVerifyPublicStaticMethod() throws Exception {
        // prepare
        // PrepareForText and @RunWith(PowerMockRunner.class) annotations are needed
        Object object = new Object();

        PowerMockito.spy(StaticExample.class);
        // also
        // PowerMockito.mockStatic(StaticExample.class);

        // stub
        PowerMockito.doNothing().when(StaticExample.class);
        StaticExample.publicMethod(Matchers.anyObject());
        // also
        // PowerMockito.doNothing().when(StaticExample.class, "publicMethod", Matchers.anyObject());

        // act
        StaticExample.publicMethod(object);

        // verify
        PowerMockito.verifyStatic();
        StaticExample.publicMethod(object);
    }

    @Test
    public void stubAndVerifyPrivateStaticMethod() throws Exception {
        // prepare
        // PrepareForText and @RunWith(PowerMockRunner.class) annotations are needed
        Object object = new Object();

        PowerMockito.spy(StaticExample.class);
        // also
        // PowerMockito.mockStatic(StaticExample.class);

        // stub
        PowerMockito.doNothing().when(StaticExample.class, "privateMethod", Matchers.anyObject());

        // act
        Whitebox.invokeMethod(StaticExample.class, "privateMethod", object);

        // verify
        PowerMockito.verifyPrivate(StaticExample.class).invoke("privateMethod", object);
    }

    @Test
    public void stubAndVerifyPublicStaticMethodThatReturns() throws Exception {
        // prepare
        // PrepareForText and @RunWith(PowerMockRunner.class) annotations are needed.
        Object object = new Object();

        PowerMockito.spy(StaticExample.class);
        // also
        // PowerMockito.mockStatic(StaticExample.class);

        // stub
        PowerMockito.doReturn(object).when(StaticExample.class, "publicMethodThatReturns", Matchers.anyObject());
        // but not
        // PowerMockito.doReturn(object).when(StaticExample.publicMethodThatReturns(Matchers.anyObject()));
        // or
        // PowerMockito.when(StaticExample.publicMethodThatReturns(Matchers.anyObject())).thenReturn(object);
        // or
        // Mockito.doReturn(object).when(StaticExample.publicMethodThatReturns(Matchers.anyObject()));
        // or
        // Mockito.when(StaticExample.publicMethodThatReturns(Matchers.anyObject())).thenReturn(object);

        // act
        Object output = StaticExample.publicMethodThatReturns(object);

        // assert
        Assert.assertThat(output, CoreMatchers.equalTo(object));

        // verify
        PowerMockito.verifyStatic();
        StaticExample.publicMethodThatReturns(object);
    }

    @Test
    public void stubAndVerifyPrivateStaticMethodThatReturns() throws Exception {
        // prepare
        // PrepareForText and @RunWith(PowerMockRunner.class) annotations are needed.
        Object object = new Object();

        PowerMockito.spy(StaticExample.class);
        // also
        // PowerMockito.mockStatic(StaticExample.class);

        // stub
        PowerMockito.doReturn(object).when(StaticExample.class, "privateMethodThatReturns", Matchers.anyObject());
        // but not
        // PowerMockito.when(StaticExample.class, "privateMethodThatReturns", object).thenReturn(object);

        // act
        Object output = Whitebox.invokeMethod(StaticExample.class, "privateMethodThatReturns", object);

        // assert
        Assert.assertThat(output, CoreMatchers.equalTo(object));

        // verify
        PowerMockito.verifyPrivate(StaticExample.class).invoke("privateMethodThatReturns", object);
    }
}