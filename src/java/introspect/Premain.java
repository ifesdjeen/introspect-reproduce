package introspect;

import net.bytebuddy.agent.ByteBuddyAgent;
import java.lang.instrument.Instrumentation;

public class Premain {

  public static void premain(String agentArgs, Instrumentation inst) {
		MainTest.initialize("introspect_tests", ByteBuddyAgent.installOnOpenJDK());
    //MainTest.initialize("introspect", inst);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        System.out.printf("LogInterceptor: %d\n", LogInterceptor.counter.get());
      }
    });

    // System.out.println(new FnImpl().invoke(1, 2));
  }
}
