package com.ilab.mockito.example;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * Created by cuijfboy on 15/11/14.
 */
@PrepareForTest({Example.class})
@RunWith(PowerMockRunner.class)
public class ExampleTest {

    @Test
    public void stubAndVerifyPublicFiled() throws Exception {
        // prepare
        Object object = new Object();
        Example example = Mockito.mock(Example.class);

        // stub
        example.publicFiled = object;

        // verify
        org.junit.Assert.assertThat(example.publicFiled, CoreMatchers.equalTo(object));
    }

    @Test
    public void stubAndVerifyPrivateField() throws Exception {
        // prepare
        Object object = new Object();
        Example example = Mockito.mock(Example.class);

        // stub
        Whitebox.setInternalState(example, "privateField", object);

        // verify
        Object output = Whitebox.getInternalState(example, "privateField");
        Assert.assertThat(output, CoreMatchers.equalTo(object));

    }

    @Test
    public void stubAndVerifyPublicConstructor() throws Exception {
        // prepare
        Object object = new Object();

        // PrepareForText and @RunWith(PowerMockRunner.class) annotations are needed.
        Example example = Mockito.mock(Example.class);
        // also
        // Example example = Mockito.spy(Whitebox.newInstance(Example.class));

        // stub
        PowerMockito.whenNew(Example.class).withArguments(Matchers.anyObject()).thenReturn(example);

        // act
        Example output = new Example(object);

        // assert
        Assert.assertThat(output, CoreMatchers.equalTo(example));

        // verify
        PowerMockito.verifyNew(Example.class).withArguments(object);
    }

    @Test
    public void stubAndVerifyPublicMethod() throws Exception {
        // prepare
        Object object = new Object();

        Example example = Mockito.spy(Whitebox.newInstance(Example.class));
        // also
        // Example example = Mockito.mock(Example.class);

        // stub
        Mockito.doNothing().when(example).publicMethod(Matchers.anyObject());

        // act
        example.publicMethod(object);

        // verify
        Mockito.verify(example).publicMethod(object);
    }

    @Test
    public void stubAndVerifyPrivateMethod() throws Exception {
        // prepare
        Object object = new Object();

        // PrepareForText and @RunWith(PowerMockRunner.class) annotations are needed.
        Example example = PowerMockito.spy(Whitebox.newInstance(Example.class));
        // also
        // Example example = PowerMockito.mock(Example.class);

        // stub
        PowerMockito.doNothing().when(example, "privateMethod", Matchers.anyObject());

        // act
        Whitebox.invokeMethod(example, "privateMethod", object);

        // verify
        PowerMockito.verifyPrivate(example).invoke("privateMethod", object);
    }

    @Test
    public void stubAndVerifyPublicMethodThatReturns() throws Exception {
        // prepare
        Object object = new Object();

        Example example = Mockito.spy(Whitebox.newInstance(Example.class));
        // also
        // Example example = Mockito.mock(Example.class);

        // stub
        Mockito.doReturn(object).when(example).publicMethodThatReturns(Matchers.anyObject());
        // but not
        // Mockito.when(example.publicMethodThatReturns(Matchers.anyObject())).thenReturn(object);

        // action
        example.publicMethodThatReturns(object);

        // verify
        Mockito.verify(example).publicMethodThatReturns(object);
    }

    @Test
    public void stubAndVerifyPrivateMethodThatReturns() throws Exception {
        // prepare
        Object object = new Object();

        // PrepareForText and @RunWith(PowerMockRunner.class) annotations are needed.
        Example example = PowerMockito.spy(Whitebox.newInstance(Example.class));
        // also
        // Example example = PowerMockito.mock(Example.class);

        // stub
        PowerMockito.doReturn(object).when(example, "privateMethodThatReturns", Matchers.anyObject());
        // but not
        // PowerMockito.when(example, "privateMethodThatReturns", Matchers.anyObject()).thenReturn(object);

        // act
        Whitebox.invokeMethod(example, "privateMethodThatReturns", object);

        // verify
        PowerMockito.verifyPrivate(example).invoke("privateMethodThatReturns", object);
    }
}