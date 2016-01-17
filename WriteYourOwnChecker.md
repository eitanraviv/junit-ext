# Write your own Checker #
You can develop your own checker by implementing the Checker interface.

```
package com.googlecode.junit.ext;

public interface Checker {
    boolean satisfy();
}
```


```
package com.googlecode.junit.ext;

public class MyChecker implements Checker {
    public boolean satisfy() {
        //to something and return boolean.
    }
```


your checker can take String or String[.md](.md) as argument in the contructor,

```
package com.googlecode.junit.ext;

public class MyChecker implements Checker {
    public MyChecker(String str) {

    }
 
    public boolean satisfy() {
        //to somethin and return boolean.
    }
}

or

package com.googlecode.junit.ext;

public class MyChecker implements Checker {
    public MyChecker(String[] strs) {

    }
 
    public boolean satisfy() {
        //to somethin and return boolean.
    }
}


```


the arguments can be provided through arguments in the annotation:

```
@Prerequisite(checker = MyChecker.class, arguments="sth")
public void runTestWhenMyCheckerIsSatisfied() {

}
```

or

```
@Prerequisite(checker = MyChecker.class, arguments={"sth", "sth"})
public void runTestWhenMyCheckerIsSatisfied() {

}
```





---


All contents:
  * [Quick start](QuickStart.md)
  * [Using Checkers in the test](UsingCheckers.md)
  * [Write your own checker](WriteYourOwnChecker.md)
  * [integration between junit-ext and spring](TestWithSpring.md)
  * [Why Preconditions](Preconditions.md)
  * [How to use Preconditions](HowToUsePrecondtion.md)