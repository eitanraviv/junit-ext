# Checkers #

Checkers are used to decide whether the test case should be ran or not. Here is the example of using OSCheck in the test.

```
package com.googlecode.junit.ext.checkers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.*;
import com.googlecode.junit.ext.checkers.OSChecker;

@RunWith(JunitExtRunner.class)
public class TestCasesOnDifferentOS {
    @Test
    @RunIf(value = OSChecker.class, arguments = OSChecker.MAC)
    public void shouldRunOnMac() throws Exception {
    }

    @Test
    @RunIf(value = OSChecker.class, arguments = OSChecker.WINDOWS)
    public void shouldRunOnWindows() throws Exception {

    }

    @Test
    @RunIf(value = OSChecker.class, arguments = OSChecker.LINUX)
    public void shouldRunOnLinux() throws Exception {
    }

```


What you should do is
  * Put @RunWith(JunitExtRunner.class) on top of your class to make junit run the test with JunitExtRunner.
  * Annotate your test with @RunIf and @Test
  * In the @RunIf, pick up a proper Checker or write your own Checker.


There are 5 Checkers provided as default

**OSChecker**

it is used to check whether the OS is the targeting platform or not. The targeting platform should be provided as arguments. Given the following test case:
```
@RunIf(value = OSChecker.class, arguments = OSChecker.LINUX)
@Test
public void test1() {

}
```

it will only be ran on the Linux and be ignored on any other platform.

**FileChecker**

It is used to check whether the targeting file exists or not. the targeting file should be provided as arguments, Given the following test case:
```

@RunIf(value = FileChecker.class, arguments = "/etc/version")
@Test
public void test1() {

}
```

it will only be ran when /etc/version exist and be ignored on any other cases.

**HttpChecker**

It is used to check whether the targeting URL is reachable or not. the targeting URL should be provided as arguments, Given the following test case:

```

@RunIf(value = HTTPChecker.class, arguments = "http://www.google.com")
@Test
public void test1() {

}

or

//5 seconds as timeout
@RunIf(value = HTTPChecker.class, arguments = {"http://www.google.com", "5000"})
@Test
public void test1() {

}

```

it will only be ran when the http://www.google.com is reachable.

**SocketChecker**

It is used to check whether the targeting Socket server is reachable. the targeting socket server should be provided as arguments, Given the following test case:

```

@RunIf(value = SocketChecker.class, arguments = {"10.18.3.12", "8723"})
@Test
public void test1() {

}


```

it will only be ran when the server at 10.18.3.12 opened the port 8723

**AppsInstalledChecker**

Check whether the targeting application is installed or not, you can provide a command to detect it, for example, you can detect whether ant is installed by providing "ant help" as the command.

```

@RunIf(value = AppsInstalledChecker.class, arguments = "ant help")
@Test
public void test1() {

}


```

it will only be ran when ant is installed(in your PATH)



---


All contents:
  * [Quick start](QuickStart.md)
  * [Using Checkers in the test](UsingCheckers.md)
  * [Write your own checker](WriteYourOwnChecker.md)
  * [integration between junit-ext and spring](TestWithSpring.md)
  * [Why Preconditions](Preconditions.md)
  * [How to use Preconditions](HowToUsePrecondtion.md)