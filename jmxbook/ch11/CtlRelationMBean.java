package jmxbook.ch11;

import javax.management.*;
import javax.management.relation.*;

public interface CtlRelationMBean extends RelationSupportMBean
{
  public void disablePhoneCard(int cardNum)throws MBeanException;
  public void disableFaxCard()throws MBeanException;
}

