package com.zhu8fei.easytest.core.runner;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.lang.annotation.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by WangPengfei on 2017/7/25.
 */
public class EasySuite extends ParentRunner<Runner> {
    /**
     * Returns an empty suite.
     */
    public static Runner emptySuite() {
        try {
            return new EasySuite((Class<?>) null, new Class<?>[0]);
        } catch (InitializationError e) {
            throw new RuntimeException("This shouldn't be possible");
        }
    }

    /**
     * The <code>SuiteClasses</code> annotation specifies the classes to be run when a class
     * annotated with <code>@RunWith(Suite.class)</code> is run.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface SuiteClasses {
        /**
         * @return the classes to be run
         */
        Class<?>[] value();
    }

    private static Class<?>[] getAnnotatedClasses(Class<?> klass) throws InitializationError {
        EasySuite.SuiteClasses annotation = klass.getAnnotation(EasySuite.SuiteClasses.class);
        if (annotation == null) {
            throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", klass.getName()));
        }
        return annotation.value();
    }

    private final List<Runner> runners;

    /**
     * Called reflectively on classes annotated with <code>@RunWith(Suite.class)</code>
     *
     * @param klass   the root class
     * @param builder builds runners for classes in the suite
     */
    public EasySuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        this(builder, klass, getAnnotatedClasses(klass));
    }

    /**
     * Call this when there is no single root class (for example, multiple class names
     * passed on the command line to {@link org.junit.runner.JUnitCore}
     *
     * @param builder builds runners for classes in the suite
     * @param classes the classes in the suite
     */
    public EasySuite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
        this(null, builder.runners(null, classes));
    }

    /**
     * Call this when the default builder is good enough. Left in for compatibility with JUnit 4.4.
     *
     * @param klass        the root of the suite
     * @param suiteClasses the classes in the suite
     */
    protected EasySuite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {

        this(new AllDefaultPossibilitiesBuilder(true), klass, suiteClasses);
    }

    /**
     * Called by this class and subclasses once the classes making up the suite have been determined
     *
     * @param builder      builds runners for classes in the suite
     * @param klass        the root of the suite
     * @param suiteClasses the classes in the suite
     */
    protected EasySuite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
        this(klass, builder.runners(klass, suiteClasses));
    }

    /**
     * Called by this class and subclasses once the runners making up the suite have been determined
     *
     * @param klass   root of the suite
     * @param runners for each class in the suite, a {@link Runner}
     */
    protected EasySuite(Class<?> klass, List<Runner> runners) throws InitializationError {
        super(klass);
        try {
            klass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.runners = Collections.unmodifiableList(runners);
    }

    @Override
    protected List<Runner> getChildren() {
        return runners;
    }

    @Override
    protected Description describeChild(Runner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(Runner runner, final RunNotifier notifier) {
        runner.run(notifier);
    }
}
