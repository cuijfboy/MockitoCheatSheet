<a id="top"></a>
# Mockito Cheat Sheet

|stub & verify  |field          |constructor    |method         |
|:--------------|:-------------:|:-------------:|:-------------:|
|public         |[code](#pu_f)  |[code](#pu_c)  |[code](#pu_m)  |
|private        |[code](#pr_f)  |-              |[code](#pr_m)  |
|public  static |[code](#pus_f) |-              |[code](#pus_m) |
|private static |[code](#prs_f) |-              |[code](#prs_m) |

or

|stub & verify  |public         |private        |public static  |private static |
|:--------------|:-------------:|:-------------:|:-------------:|:-------------:|
|field          |[code](#pu_f)  |[code](#pr_f)  |[code](#pus_f) |[code](#prs_f) |
|constructor    |[code](#pu_c)  |-              |-              |-              |
|method         |[code](#pu_m)  |[code](#pr_m)  |[code](#pus_m) |[code](#prs_m) |

also

[class Example](#class_example)

[class StaticExample](#class_static_example)

[dependencies in build.gradle](#dependencies)

[ExampleTest.java](MockitoExample/app/src/test/java/com/ilab/mockito/example/ExampleTest.java)

[StaticExampleTest.java](MockitoExample/app/src/test/java/com/ilab/mockito/example/StaticExampleTest.java)

[Example.java](MockitoExample/app/src/main/java/com/ilab/mockito/example/Example.java)

[StaticExample.java](MockitoExample/app/src/main/java/com/ilab/mockito/example/StaticExample.java)

---

<a id="pu_f"></a>
## public field

[TOP](#top)

```java
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
```

<a id="pr_f"></a>
## private field

[TOP](#top)

```java
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
```

<a id="pus_f"></a>
## public static field

[TOP](#top)

```java
@Test
public void stubAndVerifyPublicStaticFiled() throws Exception {
    // prepare
    Object object = new Object();

    // stub
    StaticExample.publicField = object;

    //assert
    Assert.assertThat(StaticExample.publicField, CoreMatchers.equalTo(object));
}
```

<a id="prs_f"></a>
## private static field

[TOP](#top)

```java
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
```

<a id="pu_c"></a>
## public constructor

[TOP](#top)

```java
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
```

<a id="pu_m"></a>
## public method

[TOP](#top)

```java
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
```
```java
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
```

<a id="pr_m"></a>
## private method

[TOP](#top)

```java
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
```

```java
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
```

<a id="pus_m"></a>
## public static method

[TOP](#top)

```java
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
```

```java
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
```

<a id="prs_m"></a>
## private static method

[TOP](#top)

```java
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
```

```java
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
```

---

<a id="class_example"></a>
## Example.java

[TOP](#top)

```java
public class Example {

    public Object publicFiled;
    private Object privateField;

    public Example(Object privateField) {
        System.out.println("Here is Example.Constructor with one argument");
    }

    public void publicMethod(Object arg) {
        System.out.println("Here is Example.publicMethod");
    }

    private void privateMethod(Object arg) {
        System.out.println("Here is Example.publicMethod");

    }

    public Object publicMethodThatReturns(Object arg) {
        System.out.println("Here is Example.publicMethodThatReturns");
        return null;
    }

    private Object privateMethodThatReturns(Object arg) {
        System.out.println("Here is Example.privateMethodThatReturns");
        return null;
    }

}
```

<a id="class_static_example"></a>
## StaticExample.java

[TOP](#top)

```java
public class StaticExample {
    public static Object publicField;
    private static Object privateField;

    public static void publicMethod(Object arg) {
        System.out.println("Here is StaticExample.publicMethod");
    }

    private static void privateMethod(Object arg) {
        System.out.println("Here is StaticExample.privateMethod");
    }

    public static Object publicMethodThatReturns(Object arg) {
        System.out.println("Here is StaticExample.publicMethodThatReturns");
        return null;
    }

    private static Object privateMethodThatReturns(Object arg) {
        System.out.println("Here is StaticExample.privateMethodThatReturns");
        return null;
    }
}
```

<a id="dependencies"></a>
## dependencies in build.gradle

[TOP](#top)

```gradle
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:23.1.0'

    testCompile 'junit:junit:4.12'
    testCompile 'org.mockito:mockito-core:1.10.19'
    testCompile('org.powermock:powermock-module-junit4:1.6.3') {
        exclude module: 'junit'
    }
    testCompile('org.powermock:powermock-api-mockito:1.6.3') {
        exclude module: 'mockito-all'
    }
    testCompile 'org.powermock:powermock-module-junit4-rule:1.6.3'
    testCompile 'org.powermock:powermock-classloading-xstream:1.6.3'
}
```
