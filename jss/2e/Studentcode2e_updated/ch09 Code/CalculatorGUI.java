//********************************************************************
//  CalculatorGUI.java       Authors: Lewis/Chase
//
//  Provides the graphical user interface for the calculator system.
//********************************************************************
import jss2.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class CalculatorGUI extends JPanel
{
  JPanel keyPanel, textPanel, equalPanel;
  JButton oneButton,twoButton,threeButton,fourButton,fiveButton;
  JButton sixButton,sevenButton,eightButton,nineButton,zeroButton;
  JButton plusButton,minusButton,multButton,divButton,equalButton;
  JButton leftPButton, rightPButton, clearButton;
  JTextField inputTF;
  
  /*******************************************************************
    Constructs a calculator
  *******************************************************************/
  public CalculatorGUI()
  {
    keyPanel = new JPanel();
    textPanel = new JPanel();
    equalPanel = new JPanel();

    inputTF = new JTextField(10);
    inputTF.addActionListener(new EqualListener());
    oneButton = new JButton("1");
    oneButton.setActionCommand("1");
    oneButton.addActionListener(new KeyListener());
    twoButton = new JButton("2");
    twoButton.setActionCommand("2");
    twoButton.addActionListener(new KeyListener());
    threeButton = new JButton("3"); 
    threeButton.setActionCommand("3");
    threeButton.addActionListener(new KeyListener());
    fourButton = new JButton("4");
    fourButton.setActionCommand("4");
    fourButton.addActionListener(new KeyListener());
    fiveButton = new JButton("5");
    fiveButton.setActionCommand("5");
    fiveButton.addActionListener(new KeyListener());
    sixButton = new JButton("6");
    sixButton.setActionCommand("6");
    sixButton.addActionListener(new KeyListener());
    sevenButton = new JButton("7");
    sevenButton.setActionCommand("7");
    sevenButton.addActionListener(new KeyListener());
    eightButton = new JButton("8");
    eightButton.setActionCommand("8");
    eightButton.addActionListener(new KeyListener());
    nineButton = new JButton("9");
    nineButton.setActionCommand("9");
    nineButton.addActionListener(new KeyListener());
    zeroButton = new JButton("0");
    zeroButton.setActionCommand("0");
    zeroButton.addActionListener(new KeyListener());
    leftPButton = new JButton("(");
    leftPButton.setActionCommand("(");
    leftPButton.addActionListener(new KeyListener());
    rightPButton = new JButton(")");
    rightPButton.setActionCommand(")");
    rightPButton.addActionListener(new KeyListener());
    plusButton = new JButton("+");
    plusButton.setActionCommand("+");
    plusButton.addActionListener(new KeyListener());
    minusButton = new JButton("-"); 
    minusButton.setActionCommand("-");
    minusButton.addActionListener(new KeyListener());
    multButton = new JButton("*");
    multButton.setActionCommand("*");
    multButton.addActionListener(new KeyListener());
    divButton = new JButton("/");
    divButton.setActionCommand("/");
    divButton.addActionListener(new KeyListener());
    equalButton = new JButton("        =        ");
    equalButton.addActionListener(new EqualListener());
    clearButton = new JButton("clear");
    clearButton.addActionListener(new ClearListener());
    
    textPanel.add(inputTF);
    keyPanel.add(sevenButton);
    keyPanel.add(eightButton);
    keyPanel.add(nineButton);
    keyPanel.add(divButton);
    keyPanel.add(fourButton);
    keyPanel.add(fiveButton);
    keyPanel.add(sixButton);
    keyPanel.add(multButton);
    keyPanel.add(oneButton);
    keyPanel.add(twoButton);
    keyPanel.add(threeButton);
    keyPanel.add(minusButton);  
    keyPanel.add(leftPButton);
    keyPanel.add(zeroButton);
    keyPanel.add(rightPButton);  
    keyPanel.add(plusButton); 
    equalPanel.add(equalButton);
    equalPanel.add(clearButton);
    add(textPanel);
    add(keyPanel); 
    add(equalPanel);
    keyPanel.setLayout(new GridLayout(4,4,5,5));
  }  
  
  /*******************************************************************
    Displays the calculator on the screen
  *******************************************************************/
  public void display() 
  {
    /** Create and set up the window. */
    JFrame myFrame = new JFrame("Calculator");
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.setContentPane(new CalculatorGUI());
    myFrame.setPreferredSize(new Dimension(200, 250));

    /** Display the window. */
    myFrame.pack();
    myFrame.setVisible(true);
  }
  
  /*******************************************************************
    Represents an action listener for the clear button
  *******************************************************************/
  public class ClearListener implements ActionListener
  {
    /*****************************************************************
      Clears the input window.
    *****************************************************************/
    public void actionPerformed(ActionEvent e)
    {
      inputTF.setText("");
      inputTF.requestFocusInWindow();
    }
  }
  
  /*******************************************************************
    Represents an action listener for number and operator buttons
  *******************************************************************/
  public class KeyListener implements ActionListener 
  {
    /****************************************************************
      When a key is pressed, it is added to the input text field
      which also allows the user to add in negative numbers and
      numbers with more than one digit. No decimals are allowed.
      First number cannot be negative.
    ****************************************************************/
    public void actionPerformed(ActionEvent e)
    {
      String current = inputTF.getText();
      String temp = e.getActionCommand();
      
      if( temp.equals("*") || temp.equals("/") || temp.equals("+") ||
          temp.equals("-") || temp.equals("(") || temp.equals(")") )
      {
        try
        {
          char prev = current.charAt(current.length()-2);
          
          /** negatives*/
          if( temp.equals("-") &&  ( prev=='*' || prev=='/' || prev=='+' 
                     || prev=='-' || prev=='(' || prev==')' ) )
            inputTF.setText(current + temp);
          else
            inputTF.setText(current+" "+temp+" ");  
        }
        catch(Exception exception)
        {
          inputTF.setText(current+" "+temp+" ");  
        }
      }
      
      /** multiple digits */
      else 
        inputTF.setText(current + temp);
    }
  }
  
  /*******************************************************************
    Represents an action listener for the equal button
  *******************************************************************/
  public class EqualListener implements ActionListener
  {
    /*****************************************************************
      Gets and solves Postfix, then displays answer in GUI.
    *****************************************************************/
    public void actionPerformed(ActionEvent e) 
    {
      int answer=0;
      String input = inputTF.getText();
      
      /** to get Postfix */
      ArrayUnorderedList<String> tokenList = new ArrayUnorderedList<String>();
      InfixToPostfix inToPost = new InfixToPostfix();
      tokenList = inToPost.convert(input);
      
      /** to solve Postfix */
      ListPostfixEvaluator postToAns = new ListPostfixEvaluator();
      answer = postToAns.evaluate(tokenList);
      
      inputTF.setText(answer+"");
    }
  }
}
