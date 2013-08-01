package javathreads.examples.ch05.example4;

import java.util.*;

public abstract class Calculator {

    private static ThreadLocal<HashMap> results = new ThreadLocal<HashMap>() {
        protected HashMap initialValue() {
            return new HashMap();
        }
    };

    public Object calculate(Object param) {
        HashMap hm = results.get();
	Object o = hm.get(param);
	if (o != null)
	    return o;
        o = doLocalCalculate(param);
        hm.put(param, o);
        return o;
    }

    protected abstract Object doLocalCalculate(Object param);
}
