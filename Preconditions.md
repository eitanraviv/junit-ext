### The problem ###
In Junit, destroy the resource which is opened in the test is not ideal, see the following code:
```
public class TestSuite {
    @After
    public void teardown() {
       //clean up the resourceA
       //clean up the resourceB
    }

    @Test
    public void test1() {
        //create the resouceA
    }

    @Test
    public void test2() {
        //create the resouceB
    }
}

```

It is normally what you do in the junit test, _resource_ here could be a new process, could be a file or anything like that.

teardown is the only way you can safely destory the resouce, however, when you run the single testcase (let's say test1), it will probably fail, because you may not destory resourceB correctly, since in this case, resourceB is not created. so what you have to do is :

```
public class TestSuite {
    @After
    public void teardown() {
        //if resourceA exist, clean it
        //if resourceB exist, clean it.
    }
}
```

however, since teardown needs to take care of all the resources which may spread in different test cases, it will become very large very quickly.

The right way to do it is **whoever open the resource, should destroy it.**, however, junit does not support that.

another thing I have noticed is how people use their setup

```
public class TestSuite {
    @Before
    public void setUp() {

    }

    @After
    public void teardown() {
       //clean up the resourceA
    }

    @Test
    public void test1() {
        //create the resouceA
        //do the test
    }

    @Test
    public void test2() {
        //create the resouceA
        //do the test
    }
}

```

since in the test1 and test2, they both created the resourceA, in order to reduce duplication, many people tend to move the resource creation into the setup, the code will become like this:

```
public class TestSuite {
    @Before
    public void setUp() {
        //create the resouceA
    }

    @After
    public void teardown() {
       //clean up the resourceA
    }

    @Test
    public void test1() {
        //do the test
    }

    @Test
    public void test2() {
        //do the test
    }
}
```

the problem of doing this is:

  * It is hard to understand in order to run test1, what is the data/environment to be prepared.
  * It is impossible to add a new test case like this:


```
    @Test
    public void test3() {
        //resourceC needs to be created instead of resourceA, and existence 
        //of resourceA will actually make me fail.
    }

```

### What is going wrong ###

setup and teardown is the place to clean and setup basic environment, let's say you are doing the test with database, setup and teardown is the place to start up the database , clean all the records in the database, shutdown the database. Developer should not put the logic like "create user/delete user, create wallet/crate wallet" in the setup and teardown just for reuse. those logic belong to the test case.

however, if you do the right thing, you will find it is really hard for you to destroy resource like this:
```
    @Test
    public void test2() {
        //created the resource
        //do the test
        //destroy the resource
    }
}
```

since the code to "destroy the resource" probably do not even have the chance to be executed, before the test may throw exception in between.

It is why I want this Preconditions thing.

Please read [How to use Preconditions](HowToUsePrecondtion.md) for more information.


---


All contents:
  * [Quick start](QuickStart.md)
  * [Using Checkers in the test](UsingCheckers.md)
  * [Write your own checker](WriteYourOwnChecker.md)
  * [integration between junit-ext and spring](TestWithSpring.md)
  * [Why Preconditions](Preconditions.md)
  * [How to use Preconditions](HowToUsePrecondtion.md)