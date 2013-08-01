package javathreads.examples.ch15;

import java.lang.*;
import java.util.concurrent.atomic.*;

public class AtomicDouble extends Number {
    private AtomicReference<Double> value;

    public AtomicDouble() {
        this(0.0);
    }

    public AtomicDouble(double initVal) {
        value = new AtomicReference<Double>(new Double(initVal));
    }

    public double get() {
        return value.get().doubleValue();
    }

    public void set(double newVal) {
        value.set(new Double(newVal));
    }

    public boolean compareAndSet(double expect, double update) {
        Double origVal, newVal;

        newVal = new Double(update);
        while (true) {
            origVal = value.get();

            if (Double.compare(origVal.doubleValue(), expect) == 0) {
                if (value.compareAndSet(origVal, newVal))
                    return true; 
            } else {
                return false;
            }
        }
    }

    public boolean weakCompareAndSet(double expect, double update) {
        return compareAndSet(expect, update);
    }

    public double getAndSet(double setVal) {
        Double origVal, newVal;

        newVal = new Double(setVal);
        while (true) {
            origVal = value.get();

            if (value.compareAndSet(origVal, newVal))
                return origVal.doubleValue();
        }
    }

    public double getAndAdd(double delta) {
        Double origVal, newVal;

        while (true) {
            origVal = value.get();
            newVal = new Double(origVal.doubleValue() + delta);
            if (value.compareAndSet(origVal, newVal))
                return origVal.doubleValue();
        }
    }

    public double addAndGet(double delta) {
        Double origVal, newVal;

        while (true) {
            origVal = value.get();
            newVal = new Double(origVal.doubleValue() + delta);
            if (value.compareAndSet(origVal, newVal))
                return newVal.doubleValue();
        }
    }

    public double getAndIncrement() {
        return getAndAdd((double) 1.0);
    }

    public double getAndDecrement() {
        return getAndAdd((double) -1.0);
    }

    public double incrementAndGet() {
        return addAndGet((double) 1.0);
    }

    public double decrementAndGet() {
        return addAndGet((double) -1.0);
    }

    public double getAndMultiply(double multiple) {
        Double origVal, newVal;

        while (true) {
            origVal = value.get();
            newVal = new Double(origVal.doubleValue() * multiple);
            if (value.compareAndSet(origVal, newVal))
                return origVal.doubleValue();
        }
    }

    public double multiplyAndGet(double multiple) {
        Double origVal, newVal;

        while (true) {
            origVal = value.get();
            newVal = new Double(origVal.doubleValue() * multiple);
            if (value.compareAndSet(origVal, newVal))
                return newVal.doubleValue();
        }
    }

    public int intValue() {
        return DoubleValue().intValue();
    }

    public long longValue() {
        return DoubleValue().longValue();
    }

    public float floatValue() {
        return DoubleValue().floatValue();
    }

    public double doubleValue() {
        return DoubleValue().doubleValue();
    }

    public byte byteValue() {
        return (byte)intValue();
    }

    public short shortValue() {
        return (short)intValue();
    }

    public Double DoubleValue() {
        return value.get();
    }

    public boolean isNaN() {
        return DoubleValue().isNaN();
    }

    public boolean isInfinite() {
        return DoubleValue().isInfinite();
    }

    public String toString() {
        return DoubleValue().toString();
    }

    public int hashCode() {
        return DoubleValue().hashCode();
    }

    public boolean equals(Object obj) {
        Double origVal = DoubleValue();

        return ((obj instanceof Double)
                && (origVal.equals((Double)obj)))
               ||
               ((obj instanceof AtomicDouble)
                && 
(origVal.equals(((AtomicDouble)obj).DoubleValue())));
    }

    public int compareTo(Double aValue) {
        return Double.compare(doubleValue(), aValue.doubleValue());
    }

    public int compareTo(AtomicDouble aValue) {
        return Double.compare(doubleValue(), aValue.doubleValue());
    }
}
