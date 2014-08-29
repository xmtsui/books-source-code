package jmxbook.ch6;

import java.sql.*;
import java.io.Serializable;
import javax.management.*;

public class NotificationBroadcasterPersister extends NotificationBroadcasterSupport
{
  private Connection con = null;
  private boolean enable = false;

  public NotificationBroadcasterPersister( Connection con )
  {
    this.con = con;
  }

  public void setStorage( boolean enable )
  {
    this.enable = enable;
  }

  public boolean getStorage()
  {
    return enable;
  }

  public void sendNotification( Notification notif )
  {
    try
    {
      String sql = "insert into Notifications ( message, sequence_number, " + " source, timestamp, type, user_data ) values ( ?,?,?,?,?,? )";
      PreparedStatement ps = con.prepareStatement( sql );

      ps.setString( 1, notif.getMessage() );
      ps.setLong( 2, notif.getSequenceNumber() );

      if( notif.getSource() != null && notif.getSource() instanceof Serializable )
        ps.setObject( 3,notif.getSource() );
      else
        ps.setString( 3, "No Source" );

      ps.setLong( 4, notif.getTimeStamp() );
      ps.setString( 5, notif.getType() );

      if( notif.getUserData() != null && notif.getUserData() instanceof Serializable )
        ps.setObject( 6,notif.getUserData() );
      else
        ps.setString( 6, "No User Data" );

      ps.executeUpdate();
      con.commit();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

    super.sendNotification( notif );
  }

}

