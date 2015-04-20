package agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by patrykl on 20/04/15.
 */
public class OptimusPrimeTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] retVal = classfileBuffer;

        if(className.contains("IndexController")) {
            try {
                String dotClassName = className.replace('/', '.');

                ClassPool cp = ClassPool.getDefault();
                CtClass ctClazz = cp.get(dotClassName);

                CtMethod method1 = ctClazz.getDeclaredMethod("index");

                method1.insertAt(46, "{ retVal.append(\" ! \"); }");

                retVal = ctClazz.toBytecode();
            }
            catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return retVal;
    }
}
