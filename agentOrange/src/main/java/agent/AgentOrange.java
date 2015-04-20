package agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by patrykl on 20/04/15.
 */
public class AgentOrange {

    public static void premain(String agentArgs, Instrumentation inst){
        inst.addTransformer(new OptimusPrimeTransformer());
    }
}
