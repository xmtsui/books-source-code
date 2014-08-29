package jmxbook.ch14;

public interface UserInfoMgrMBean{
  public int getQueryCount( String userName );
  public void allowLogin( String userName,boolean isAllowed );
}

