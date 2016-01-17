To use precondition, you should either declare JunitExtRunner or JunitExtSpringRunner in the @RunWith, like this:

```
@RunWith(JunitExtRunner.class)
public class ShouldFailedTestAndContinueRanAllTeardownTest {
    @Test
    @Preconditions({NewProcessIsCreated.class})
    public void shouldFailedTestAndContinueRanAllTeardown() {
    }
}
```

NewProcessIsCreated needs to implement Precondition interface.

```

public class NewProcessIsCreated implements Precondition {
    public void setup() {
      //create Process
    }

    public void teardown() {
      //kill Process
    }
}

```

If you need to share state between to Preconditions, you need to annotate the object which is used to store state with @Context, it will be injected to every single Precondtion

```
@RunWith(JunitExtRunner.class)
public class ShouldFailedTestAndContinueRanAllTeardownTest {
    @Context Map context = new HashMap();

    @Test
    @Preconditions({NewProcessIsCreated.class, NewProcess2IsCreated.class})
    public void shouldFailedTestAndContinueRanAllTeardown() {
    }
}

public class NewProcessIsCreated implements Precondition {
    private Map context;

    public NewProcessIsCreated(Object obj) {
       context = (Map) obj;
    }

    public void setup() {
      //create Process
      //put my pid into context
    }

    public void teardown() {
      //kill Process
    }
}

public class NewProcess2IsCreated implements Precondition {
    private Map context;

    public NewProcessIsCreated(Object obj) {
       context = (Map) obj;
    }

    public void setup() {
      //read the pid from the context.
      //create Process
    }

    public void teardown() {
      //kill Process
    }
}
```

Note :

  * The object annotated with @Context does not need to be Map, it could be any Type.
  * NewProcessIsCreated and NewProcess2IsCreated need a constructor make the injection happen.



---

All contents

  * [Quick start](QuickStart.md)
  * [Using Checkers in the test](UsingCheckers.md)
  * [Write your own checker](WriteYourOwnChecker.md)
  * [integration between junit-ext and spring](TestWithSpring.md)
  * [Why Preconditions](Preconditions.md)
  * [How to use Preconditions](HowToUsePrecondtion.md)