package introspect;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.instrumentation.MethodDelegation;
import net.bytebuddy.instrumentation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class MainTest {

    public static void initialize(String name, Instrumentation inst) {
        new AgentBuilder.Default()
                .rebase(ElementMatchers.nameContains(name)
                                .and(ElementMatchers.not(ElementMatchers.nameContains("LogInterceptor")))
                                .and(ElementMatchers.not(ElementMatchers.nameContains("load")))
                                .and(ElementMatchers.not(ElementMatchers.nameContains("init")))
                ).transform(new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder) {
                return builder
                        .method(ElementMatchers.any())
                        .intercept(MethodDelegation.to(LogInterceptor.class).andThen(SuperMethodCall.INSTANCE));
            }
        }).installOn(inst);
    }

    public static void main(String[] args) {
        initialize("introspect", ByteBuddyAgent.installOnOpenJDK());
        Foo foo = new Foo();
        System.out.println(foo.foo(1, 5));
    }

    private static class Foo {

        public int foo(int a, int b) {
            return a + b;
        }
    }
}
