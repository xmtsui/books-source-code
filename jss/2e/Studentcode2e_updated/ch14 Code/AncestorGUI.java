//********************************************************************
//  AncestorGUI.java       Authors: Lewis/Chase
//
//  Demonstrates an ancestor tree using a graphical user interface.
//********************************************************************
import jss2.*;
import jss2.exceptions.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.awt.FontMetrics;
import java.util.Scanner;

public class AncestorGUI extends JPanel
{
  Graphics2D g2;
  
  static JFrame myFrame = new JFrame("Ancestor Tree");
  static JPanel drawPane;
  static JFrame newFrame;
  private JTextField inputTF;
  private JTextArea contentsTA;
  private JScrollPane contentsSP, drawPaneSP;
         
  static Color textColor = new Color(210,210,255);
  static Color nodeColor = new Color(1,110,218);
  static Color bgColor = new Color(47,47,63);
  static Color ptrColor = new Color(7,223,7);
  static Color valueColor = new Color(255,255,255);
  static Color objColor = Color.ORANGE;
  static Color strColor = Color.MAGENTA;
  static Color logColor = new Color(34,110,61);

  private Person temp;
  private AncestorTree myAT = new AncestorTree();
  private Object[] treeArray;
  private String current;

  /*******************************************************************
    Creates the GUI elements
  *******************************************************************/
  public Component createComponents()
  {
    JLabel contentsLbl = new JLabel("Ancestor Tree Contents");
    contentsLbl.setHorizontalAlignment(SwingConstants.CENTER);
    contentsLbl.setFont(new Font("Verdana", Font.PLAIN, 12));

    /** listens for add command. */
    class AddListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        JPanel addPanel = new JPanel();
        final JTextField lName = new JTextField(15);
        final JLabel lNameLbl = new JLabel("* Last name");
        final JTextField fName = new JTextField(15);
        final JLabel fNameLbl = new JLabel("* First name");
        final JTextField dob = new JTextField(15);
        final JLabel dobLbl = new JLabel("DOB");
        final JTextField dod = new JTextField(15);
        final JLabel dodLbl = new JLabel("DOD");
        final JTextField occupation = new JTextField(15);
        final JLabel occLbl = new JLabel("Occupation");
        final JTextField address = new JTextField(15);
        final JLabel addLbl = new JLabel("Address");
        final JButton okButton = new JButton("OK - DONE");

        addPanel.add(lNameLbl);
        addPanel.add(lName);
        addPanel.add(fNameLbl);
        addPanel.add(fName);
        addPanel.add(dobLbl);
        addPanel.add(dob);
        addPanel.add(dodLbl);
        addPanel.add(dod);
        addPanel.add(occLbl);
        addPanel.add(occupation);
        addPanel.add(addLbl);
        addPanel.add(address);
        addPanel.add(okButton);
        okButton.repaint();
        add(addPanel);
         
        final JFrame addFrame = new JFrame("add frame");
        addFrame.getContentPane().add(addPanel);
        addFrame.pack();
        addFrame.setSize(200 ,400);
        addFrame.setVisible(true);
        addFrame.validate();

        final String onButton = ((JButton)(e.getSource())).getText();

        /** listens for submission of user choice */
        class OKListener implements ActionListener
        {
          public void actionPerformed(ActionEvent e)
          {
            String lNameStr="",fNameStr="",dobStr="";
            String dodStr="",occupStr="",addrStr="";

            lNameStr = lName.getText();
            fNameStr = fName.getText();
            dobStr = dob.getText();
            dodStr = dod.getText();
            occupStr = occupation.getText();
            addrStr = address.getText();
            
            if ((lNameStr.length()==0) || (fNameStr.length()==0))
            {
              JOptionPane.showMessageDialog(null, "First and last names are " +
                         "required fields. ", null, JOptionPane.ERROR_MESSAGE );
            }
            
            else
            {
              temp = new Person(fNameStr, lNameStr, dobStr, dodStr, 
                                occupStr, addrStr);

              if(onButton == "Add Sibling")
              {
                Scanner scan = new Scanner(current);
                String findFName = scan.next();
                String findLName = " ";
                
                if(scan.hasNext())
                  findLName = scan.next();
                
                Person findPerson = new Person(findFName,findLName,"","","","");
                Object foundParent = myAT.find(findPerson);
                
                AncestorNode found = (AncestorNode)foundParent;
                (found.siblings).add(temp);
                
                newFrame.dispose();
              }
              
              else if (onButton == "Begin Tree")
              {
                myAT.removeAllElements();
                myAT.addElement(temp, null);
              }
              
              else
              {
                Scanner scan = new Scanner(current);
                String findFName = scan.next();
                String findLName = " ";
                
                if(scan.hasNext())
                  findLName = scan.next();
                
                Person findPerson = new Person(findFName,findLName,"","","","");
                Object foundParent = myAT.find(findPerson);
                
                /** make the temp a parent of the button clicked */
                myAT.addElement(temp, ((AncestorNode)foundParent).element);
                newFrame.dispose();
              }
              
              addFrame.dispose();
              contentsTA.setText(myAT.toString());
              drawPane.repaint();
            }
          }
        }
        
        okButton.addActionListener(new OKListener());
      }
    }

    JButton addRootButton = new JButton("Begin Tree");
    addRootButton.setMnemonic(KeyEvent.VK_A);
    addRootButton.addActionListener(new AddListener());
    addRootButton.setFont(new Font("Verdana", Font.PLAIN, 22));

    /** to display information about a person */
    class InfoListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        Scanner scan = new Scanner(current);
        String findFName = scan.next();
        String findLName = "";
        
        if(scan.hasNext())
          findLName = scan.next();

        Person findPerson = new Person(findFName,findLName,"","","","");
        Object foundX = myAT.find(findPerson);
        AncestorNode found =  (AncestorNode)foundX;
        
        JPanel infoPanel = new JPanel();
        final JButton closeButton = new JButton("Close");
        JTextArea siblingTA = new JTextArea();
        siblingTA.setText(found.toString());
        siblingTA.setEditable(false);
        JScrollPane siblingSP = new JScrollPane(siblingTA);
        infoPanel.setLayout(new GridLayout(2,1));
   
        infoPanel.add("North",siblingSP);
        infoPanel.add("South",closeButton); 
        add(infoPanel);
         
        final JFrame infoFrame = new JFrame("info frame");
        infoFrame.getContentPane().add(infoPanel);
        infoFrame.pack();
        infoFrame.setSize(200 ,400);
        infoFrame.setVisible(true);
        infoFrame.validate();
        newFrame.dispose();

        closeButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            infoFrame.dispose();
          }
        });
      }
    }
    
    /** listens for a remove operation on a person button */
    class RemoveListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        if (myAT.size() > 0)
        {
          Scanner scan = new Scanner(current);
          String findFName = scan.next();
          String findLName = scan.next();
          
          Person findPerson = new Person(findFName,findLName,"","","","");
          Object foundParent = myAT.find(findPerson); //person to delete

          myAT.removeElement(((AncestorNode)foundParent).element);
               
          contentsTA.setText(myAT.toString());               
          drawPane.repaint();
        }
        
        newFrame.dispose();
      }
    }
    
    /** listens for a click on a person button */
    class PersonListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        String onButton = ((JButton)(e.getSource())).getText();
        current = onButton;
          
        JLabel currentLbl = new JLabel();
        currentLbl.setText(onButton);
        JPanel newPanel = new JPanel();
        final JButton parentButton = new JButton("Add Parent");
        JButton siblingButton = new JButton("Add Sibling");
        JButton infoButton = new JButton("View Info");
        JButton removeButton = new JButton("Remove");

        parentButton.addActionListener(new AddListener());
        siblingButton.addActionListener(new AddListener());
        infoButton.addActionListener(new InfoListener());
        removeButton.addActionListener(new RemoveListener());

        newPanel.setLayout(new FlowLayout());
        newPanel.add(currentLbl);
        newPanel.add(parentButton);
        newPanel.add(siblingButton);
        newPanel.add(infoButton);
        newPanel.add(removeButton);
        add(newPanel);         

        newFrame = new JFrame("new frame");
        newFrame.getContentPane().add(newPanel);
        newFrame.pack();
        newFrame.setSize(200, 300);
        newFrame.setVisible(true);
        newFrame.validate();
      }
    }
    
    /** this panel shows the graphical representation of the tree */
    class DrawATPanel extends JPanel
    {
      public void paintComponent(Graphics g)
      {
        drawPane.removeAll(); 

        int curX = 200;
        int curY = 420;
        int boxHt = 30;
        int counter = 1;
        int level = 1;
        int xPadding = 0;

        g2 = (Graphics2D) g;
        super.paintComponent(g2); //paint background
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, 
                            RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, 
                            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setStroke(new BasicStroke((float)1.75,BasicStroke.CAP_ROUND, 
                         BasicStroke.JOIN_MITER));
                 
        /** need to draw each box for the border */
        Dimension drawDim = this.getSize();
        g2.setBackground(bgColor);
        g2.clearRect(0,0,drawDim.width,drawDim.height);	
			   
        g2.drawRect(0,0,drawDim.width-1,drawDim.height-1);
        
        g2.setFont(new Font("Verdana", Font.BOLD, 12));
        FontMetrics fm = g2.getFontMetrics();
        g2.setPaint(textColor);
       
        curX -= boxHt;
        curY += boxHt*2;
        int i = 0;
        Iterator TtreeArray = myAT.iteratorLevelOrder();

        while(TtreeArray.hasNext())
        {
          AncestorNode currentName = (AncestorNode)(TtreeArray.next());
          
          if (i == counter)
          {
            counter = counter*2 + 1;
            level++;
            xPadding = 0;
          }
          
          else
            xPadding++;
          
          if (i == 0)
            xPadding = 0;
      
          if(currentName != null)
          {
            /** making the string to go on the button */
            Scanner scan = new Scanner(currentName.toString());
            String top = scan.next(); 
            top += " ";
            top +=  scan.next();

            int elementLen = fm.stringWidth("\" + top + \"");

            /** if the current node has a left child */
            if((currentName.getLeft()) != null)
            {
              /** draw a line to the current node's left child */
              g2.setPaint(ptrColor);
              g2.drawLine(curX + (int)(boxHt*Math.pow(2, myAT.getHeight() - level) - 1)
                         + (int)(xPadding*boxHt*(Math.pow(2,myAT.getHeight() - 
                           (int)(Math.log(i+1)/Math.log(2))))) + (elementLen + 10)/2, 
                           curY - (level - 1)*(boxHt*3/2) + boxHt/2, curX + 
                           (int)(boxHt*Math.pow(2, myAT.getHeight() - level) - 1)
                         + (int)(xPadding*boxHt*(Math.pow(2,myAT.getHeight() - 
                           (int)(Math.log(i+1)/Math.log(2))))) + (elementLen + 10)/2 - 
                           (int)(boxHt/2*(Math.pow(2,myAT.getHeight() - 
                           (int)(Math.log(i+1)/Math.log(2))-1))), curY - 
                           (level)*(boxHt*3/2) + boxHt/2);
            }
            
            /** if the current node has a right child */
            if((currentName.getRight()) != null)
            {
              /** draw a line to the current node's right node */
              g2.setPaint(ptrColor);
              g2.drawLine(curX + (int)(boxHt*Math.pow(2, myAT.getHeight() - level) - 1)
                               + (int)(xPadding*boxHt*(Math.pow(2,myAT.getHeight() - 
                                 (int)(Math.log(i+1)/Math.log(2))))) + (elementLen + 10)/2, 
                                 curY - (level - 1)*(boxHt*3/2) + boxHt/2, curX + 
                                 (int)(boxHt*Math.pow(2, myAT.getHeight() - level) - 1)
                               + (int)(xPadding*boxHt*(Math.pow(2,myAT.getHeight() - 
                                 (int)(Math.log(i+1)/Math.log(2)))))+ (elementLen + 10)/2
                               + (int)(boxHt/2*(Math.pow(2,myAT.getHeight() - 
                                 (int)(Math.log(i+1)/Math.log(2))-1))), curY - 
                                 (level)*(boxHt*3/2) + boxHt/2);
            }
            
            g2.setPaint(strColor);
            
            /** draw the element node as a button */
            drawPane.setLayout(null);
            JButton nodeButton = new JButton();
            nodeButton.setBounds(curX + (int)(boxHt*Math.pow(2, myAT.getHeight() - level) - 1)
                                      + (int)(xPadding*boxHt*(Math.pow(2,myAT.getHeight() - 
                                        (int)(Math.log(i+1)/Math.log(2))))), curY - 
                                        (level - 1)*(boxHt*3/2), elementLen + 10, boxHt);
            
            /** element data */
            nodeButton.setFont(new Font("Verdana",Font.BOLD, 8));
            nodeButton.setText(top);
            nodeButton.addActionListener(new PersonListener());
            add(nodeButton);
          }
          
          i++;
        }
        
        /** updates the scrollbars if drawing goes out of view */
        drawPane.setPreferredSize(new Dimension(curX + boxHt*(int)Math.pow(2,myAT.getHeight())
                                                     + boxHt, curY + myAT.getHeight()*boxHt*3/2));
        drawPane.revalidate();
      }
    }
    
    /** removes all nodes from the tree */
    JButton removeAllButton = new JButton("Clear Tree");
    removeAllButton.setFont(new Font("Verdana", Font.PLAIN, 22));
    removeAllButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (myAT.size() > 0) 
        {
          myAT.removeAllElements();

          contentsTA.setText(myAT.toString());               
          drawPane.repaint();
        }
      }
    });
    
    contentsTA = new JTextArea();
    contentsTA.setFont(new Font("Verdana", Font.BOLD, 12));
    contentsTA.setEditable(false);
    contentsTA.setToolTipText("This area displays the current contents of " +
                              "the tree as returned by the toString() method.");
    
    contentsSP = new JScrollPane(contentsTA);
    
    JPanel pane = new JPanel();      
    pane.setLayout(new BorderLayout());

    JPanel leftPane = new JPanel();
    leftPane.setLayout(new BorderLayout());
          
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BorderLayout());
    
    JPanel south = new JPanel();
    south.setLayout(new GridLayout(8,1));
    south.add(inputPanel);
    addRootButton.setToolTipText("Press this button to begin the tree.");
    south.add(addRootButton);
    removeAllButton.setToolTipText("Press this button to remove all objects " +
                                   "from the tree.");
    south.add(removeAllButton);
 
    leftPane.add("South", south);
      
    JPanel center = new JPanel();
    center.setLayout(new BorderLayout());
    center.add("North", contentsLbl);
    center.add("Center", contentsSP);
           
    leftPane.add("Center", center);
      
    drawPane = new DrawATPanel();
    drawPaneSP = new JScrollPane(drawPane);

    pane.add("West",leftPane);      
    pane.add("Center",drawPaneSP);

    return pane;
  }

  /*******************************************************************
    Creates and displays main application window.
  *******************************************************************/
  public void display()
  {
    try
    {
      UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) { }

    /** create the top-level container and add contents to it */
    AncestorGUI atApp = new AncestorGUI();
    Component contents = atApp.createComponents();
    myFrame.getContentPane().add(contents, BorderLayout.CENTER);

    /** listen for window closing */
    myFrame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    });
    
    /** setup the menu bar at the top of the frame*/
    JMenuBar myMenuBar = new JMenuBar();
    myFrame.setJMenuBar(myMenuBar);
         
    JMenu optionsMenu = new JMenu("Options");
    JMenu colorMenu = new JMenu("Color");
    
    ButtonGroup colorGroup = new ButtonGroup();
    
    JRadioButtonMenuItem selectColor = new JRadioButtonMenuItem
                                       ("Show Color Display");
    colorGroup.add(selectColor);
    colorMenu.add(selectColor);
    selectColor.setSelected(true);
    
    /** makes changes when a different color is selected */
    class ColorListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        if (e.getActionCommand().equals("Show Monochrome Display"))
        {
          /** change to black and white */
          textColor = new Color(0,0,0);
          nodeColor = new Color(0,0,0);
          bgColor = new Color(255,255,255);
          ptrColor = new Color(0,0,0);
          valueColor = new Color(255,255,255);
          objColor = new Color(0,0,0);
          strColor = new Color(0,0,0);
          drawPane.repaint();
        }
        else
        {
          /** change to color */
          textColor = new Color(210,210,255);
          nodeColor = new Color(1,110,218);
          bgColor = new Color(47,47,63);
          ptrColor = new Color(7,223,7);
          valueColor = new Color(255,255,255);
          objColor = Color.ORANGE;
          strColor = Color.MAGENTA;
          drawPane.repaint();
        }
      }
    }
    
    selectColor.addActionListener(new ColorListener());
    
    JRadioButtonMenuItem selectMono = new JRadioButtonMenuItem
                                      ("Show Monochrome Display");
    colorGroup.add(selectMono);
    colorMenu.add(selectMono);
    selectMono.addActionListener(new ColorListener());
    
    optionsMenu.add(colorMenu);      
          
    myMenuBar.add(optionsMenu);      
    
    JMenu helpMenu = new JMenu("Help");
    JMenuItem aboutMenuItem = new JMenuItem("About AncestorGUI");
    aboutMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        JOptionPane.showMessageDialog(myFrame, 
        "Ancestor Tree Demonstration\n\nlast modified July.26.2005\n" + 
        "Massjouni\\Park \nmods Coleman\\McPherson",
        "About ATGUI", JOptionPane.PLAIN_MESSAGE);
      }
    });
    
    helpMenu.add(aboutMenuItem);
    myMenuBar.add(helpMenu);
    
    myFrame.pack();
    myFrame.setSize(800,650);      
    myFrame.setVisible(true);
  }
}
