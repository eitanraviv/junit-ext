# The problem #
QA reported a bug on Linux platform. you opened the IDE, quickly wrote a test to reproduce it, the test helped you to find the defect in the production code very quickly, It was easy to fix! You ran the test again, it passed in this time, which proved that you really fixed the problem.

You announced you found the problem and fixed it, you were about to checkin the code. But wait, you cannot do that, because this bug only happens on Linux, the test will definitely fail on the Windows platform. If you mark the test as @Ignore, maybe some changes in the future will make the bug reopen.

Nothing can stop you, you quickly rewrite the test in this way.:

```
@Test
public void shouldPassOnLinuxPlatform() {
     boolean isLinuxPlatform = detect the OS;
     if (isLinuxPlatform) {
          run the test.
     }
}

```

Problem resolved, everyone is happy. the only imperfect is the test code is a bit ugly.


There are many cases you want to write the test to test for real(rather than mock it), this kind of test give you lot of confidence that you really resolved the problem, but it also bring in the trouble like the tests can only pass if **some prerequisites are satisfied.**

# Solution #

junit-ext is aiming for providing a nice way to run the tests base on the prerequisite.

```
@RunWith(JunitExtRunner.class)
public class TestCasesOnDifferentOS {
    @Test
    @RunIf(value = OSChecker.class, arguments = OSChecker.LINUX)
    public void shouldPassOnLinuxPlatform() throws Exception {
    }
}

```

In this way(declared JunitExtRunner and @RunIf annotation), test case will be only executed on linux platform.

# Why not junit assumption #
Junit shipped with assumeThat for the same purpose, to run some test cases conditionally, from junit java doc, you can find this sample code.
```
    // only provides information if database is reachable.
    @Test public void calculateTotalSalary() {
        DBConnection dbc = Database.connect();
        assumeNotNull(dbc);
        // ...
     }
```

this test case will firstly be executed, and the lines next to
```
    assumeNotNull(dbc);
```
will be skipped when dbc is null and the test case will be marked as **passed**.


using @RunIf, the test case will be implemented in this way
```
    @Test
    @RunIf(DatabaseIsConnected.class)
    public void calculateTotalSalary() {
        //your code there
    }

    class DatabaseIsConnected implements Checker {
       public boolean satisify() {
            return Database.connect() != null
       }
    }
```

when the conn is null, the test will **not** be executed, and will be marked as **ignored**

the reason @RunIf is better:

  * A passed test case means the actual result is same as expected, considered the test case is actually not executed, it should be marked as ignored rather than passed. Green bar will hide the problem.

  * If the dbc is null which means the the assumption is wrong then the test framework should not waste time on calling the test case.

  * The Checker can be reused cross all the test cases, on the above case, you can easily write another test cases with same assumption in this way.
```
    @Test
    @RunIf(DatabaseIsConnected.class)
    public void calculateSomeOneSalary() {
          //your code here
    }
```

that is why junit-ext shipped with OSChecker, FileChecker and etc, I want this extension can help developers not to be bothered with some common cases and focus on the testing.

  * Good support from IDE, when you type
```
    @RunIf(
```
and press ctrl + alt + space(in IntelliJ), you can get all available Checkers in your classpath.

  * More readable



---


All contents:
  * [Quick start](QuickStart.md)
  * [Using Checkers in the test](UsingCheckers.md)
  * [Write your own checker](WriteYourOwnChecker.md)
  * [integration between junit-ext and spring](TestWithSpring.md)
  * [Why Preconditions](Preconditions.md)
  * [How to use Preconditions](HowToUsePrecondtion.md)