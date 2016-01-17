junit-ext also provides the JunitExtSpringRunner to let you filter the testcases when running the test with Spring. The only different is to use JunitExtSpringRunner instead of JunitExtRunner

```
@RunWith(JunitExtSpringRunner.class)
@ContextConfiguration(locations = {
        "classpath:**/applicationContext-repository.xml"
        })
public class ShouldWorkWithSpringTest {
    @Autowired
    private SampleRepository repository;

    @Test
    @RunIf(NeverSatisfiedChecker.class)
    public void shouldBeIgnored() {
        throw new RuntimeException();
    }

    @Test
    @RunIf(AlwaysSatisfiedChecker.class)
    public void shouldRunWithInstantiatedRepository() throws Exception {
        assertThat(repository, is(not(nullValue())));
    }

}
```


---

All contents

  * [Quick start](QuickStart.md)
  * [Using Checkers in the test](UsingCheckers.md)
  * [Write your own checker](WriteYourOwnChecker.md)
  * [integration between junit-ext and spring](TestWithSpring.md)
  * [Why Preconditions](Preconditions.md)
  * [How to use Preconditions](HowToUsePrecondtion.md)