package jmxbook.ch9;

import java.net.*;
import javax.management.*;
import java.io.*;

public class TCPTester
{

  public TCPTester( String port ) throws Exception
  {
    Socket s = new Socket( "localhost", Integer.parseInt( port ) );
    PrintWriter print = new PrintWriter( s.getOutputStream() );

    //create a Hello World MBean
    print.println( TCPAdapter.CREATE_MBEAN );
    print.flush();
    print.println( "jmxbook.ch2.HelloWorld" );
    print.flush();
    print.println( "JMXBookAgent:name=TCPCreatedHW" );
    print.flush();
    print.println( TCPAdapter.ARGS );
    print.flush();
    print.println( "This is my greeting" );
    print.flush();
    print.println( "java.lang.String" );
    print.flush();

    BufferedReader in = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
    System.out.println( in.readLine() );
    Thread.sleep(10000);

    //reset the greeting
    print.println( TCPAdapter.SET_ATTRIBUTE );
    print.flush();
    print.println( "Greeting" );
    print.flush();
    print.println( "JMXBookAgent:name=TCPCreatedHW" );
    print.flush();
    print.println( TCPAdapter.ARGS );
    print.flush();
    print.println( "This is my greeting after being changed" );
    print.flush();
    print.println( "java.lang.String" );
    print.flush();
    Thread.sleep(10000);

    //get the greeting
    print.println( TCPAdapter.GET_ATTRIBUTE );
    print.flush();
    print.println( "Greeting" );
    print.flush();
    print.println( "JMXBookAgent:name=TCPCreatedHW" );
    print.flush();
    System.out.println( in.readLine() );

    //invoke printGreeting
    print.println( TCPAdapter.INVOKE );
    print.flush();
    print.println( "printGreeting" );
    print.flush();
    print.println( "JMXBookAgent:name=TCPCreatedHW" );
    print.flush();
    print.println( TCPAdapter.SHUTDOWN );
    print.flush();

    System.out.println( in.readLine() );
  }

  public static void main(String args[]) throws Exception
  {
    TCPTester t = new TCPTester( args[0] );
  }

}

