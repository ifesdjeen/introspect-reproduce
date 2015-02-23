package introspect;

import net.bytebuddy.instrumentation.method.bytecode.bind.annotation.*;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class LogInterceptor {

  public static AtomicInteger counter = new AtomicInteger();

  @RuntimeType
  public static Object intercept(@SuperCall Callable method,
                                 @This Object proxy,
                                 @AllArguments Object[] allArguments) {
    counter.addAndGet(1);
    String args = "";
    for (int i = 0; i < allArguments.length; i++) {
      args += allArguments[i].toString();
      args += ", ";
    }

    Object ret = null;
    try {
      System.out.println(proxy);
      ret = method.call();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }

    System.out.printf("Called %s with %s, returning %s\n",
                      method,
                      args,
                      ret);
    return ret;
  }
}
