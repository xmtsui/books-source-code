package jmxbook.ch3;

import javax.management.*;

public class ExceptionUtil
{

  public static void printException( Exception e )
  {
    StringBuffer exceptionName = new StringBuffer();
    Exception exc = null;
    System.out.println("-------[ Exception ]-------");
    e.printStackTrace();

    if (e instanceof MBeanException)
    {
      boolean hasEmbeddedExceptions = true;
      Exception embeddedExc = e;

      while (hasEmbeddedExceptions)
      {
        embeddedExc = (( MBeanException ) embeddedExc).getTargetException();
        System.out.println("-------[ Embedded Exception ]-------");
        embeddedExc.printStackTrace();
        if (!(embeddedExc instanceof MBeanException))
        {
          hasEmbeddedExceptions = false;
        }
      }

    }

  }

}

