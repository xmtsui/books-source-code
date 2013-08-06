package numbercruncher.program1_3;

/**
 * PROGRAM 1-3: Not Associative
 *
 * Demonstrate that floating-point addition and multiplication
 * are not associative operations.
 */
public class NotAssociative
{
    public static void main(String args[])
    {
        // Addition: Insufficient precision

        float a = 1;
        float b = 3.0E-8f;
        float c = 4.0E-8f;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        float s1 = (a+b)+c;
        float s2 = a+(b+c);

        System.out.println();
        System.out.println("(a+b)+c = " + s1);
        System.out.println("a+(b+c) = " + s2);

        // Addition: Roundoff error

        float d  = 0.54385f;
        float e  = 0.9599806f;
        float f  = 0.2252711f;

        System.out.println();
        System.out.println("d = " + d);
        System.out.println("e = " + e);
        System.out.println("f = " + f);

        s1 = (d+e)+f;
        s2 = d+(e+f);

        System.out.println();
        System.out.println("(d+e)+f = " + s1);
        System.out.println("d+(e+f) = " + s2);

        // Multipication: Underflow

        float u = 0.5f;
        float v = 1.0E-45f;
        float w = 3.0E38f;

        System.out.println();
        System.out.println("u = " + u);
        System.out.println("v = " + v);
        System.out.println("w = " + w);

        float p1 = (u*v)*w;
        float p2 = u*(v*w);

        System.out.println();
        System.out.println("(u*v)*w = " + p1);
        System.out.println("u*(v*w) = " + p2);

        // Multiplication: Roundoff error

        float x = 0.9091322f;
        float y = 0.8606576f;
        float z = 0.5684686f;

        System.out.println();
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);

        p1 = (x*y)*z;
        p2 = x*(y*z);

        System.out.println();
        System.out.println("(x*y)*z = " + p1);
        System.out.println("x*(y*z) = " + p2);
    }
}

/*
Output:
a = 1.0
b = 3.0E-8
c = 4.0E-8

(a+b)+c = 1.0
a+(b+c) = 1.0000001

d = 0.54385
e = 0.9599806
f = 0.2252711

(d+e)+f = 1.7291018
d+(e+f) = 1.7291017

u = 0.5
v = 1.4E-45
w = 3.0E38

(u*v)*w = 0.0
u*(v*w) = 2.1019477E-7

x = 0.9091322
y = 0.8606576
z = 0.5684686

(x*y)*z = 0.4447991
x*(y*z) = 0.44479907
*/