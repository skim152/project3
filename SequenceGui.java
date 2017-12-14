/*
*  Shinyeob Kim
*  2017-12-03
*  Project three
*
*  This programming project involves writing a program to calculate the terms of 
*  the following sequence of numbers: 0 1 2 5 12 29 ... where each term of the 
*  sequence is twice the previous term plus the second previous term. The 0th term 
*  of the sequence is 0 and the 1st term of the sequence is 1.
*  For example:
*  0 1 2 -> (0 + 1 + 2) + 2 = 5 0 1 2 5 -> (0 + 1 + 2 + 5) + 5 = 12 0 1 2 5 12 -> 
*  (0 + 1 + 2 + 5 + 12) + 12 = 29

*/
package shinyeobproject3;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class SequenceGui extends JFrame {

  // declare items needed to build the GUI
  private Font guiFont = new Font("MONOSPACED", Font.BOLD, 16);
  private Font inputFont = new Font("MONOSPACED", Font.BOLD, 12);
  private JRadioButton iterativeButton = new JRadioButton("Iterative", true);
  private JRadioButton recursiveButton = new JRadioButton("Recursive");
  private JTextField nField = new JTextField(10);
  private JButton computeButton = new JButton("Compute");
  private JTextField resultField = new JTextField(10);
  private JTextField efficiencyField = new JTextField(10);
  
  // initialize variables
  private int n = -1;
  private int element = -1;
  private int efficency = -1;
  
  // output file
  private static final String FILENAME = "sequence_output.csv";
  private static final String HEADER = "n,iterative,recursive"; 
  
  // inner class to handle writing file when window is closed
  private class WindowCloser extends WindowAdapter {
      @Override
      public void windowClosing(WindowEvent windowEvent)
      {
        int [][] csvEfficiency = new int [11][2];
        
        JOptionPane.showMessageDialog(null, "Writing values for n = 0 to 10 "
              + "to sequence_output.csv");
          
        // compile array of efficiency values
        for(int n=0; n<=10; n++){
          ShinyeobProject3.computeIterative(n);
          csvEfficiency[n][0] = ShinyeobProject3.getEfficency();
          ShinyeobProject3.computeRecursive(n);
          csvEfficiency[n][1] = ShinyeobProject3.getEfficency();
          } // end for compile efficiency values
        
        // write efficiency values to csv, overwrite the file if it exists
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, false))) {
          bw.write(HEADER);
          for(int n=0; n<=10; n++){
            bw.newLine();
            bw.write(n + "," + csvEfficiency[n][0] + "," + csvEfficiency[n][1]);
            } // end for
          } // end try
        catch (IOException e) {
          JOptionPane.showMessageDialog(null, e);
          } // end catch
        
        System.exit(0);
      } //end WindowCloser
  }  // end WindowCloser class
  
  
  // gui
  public SequenceGui() {
    //title, size, closing action and listener
    super("Shinyeob Kim Project 3");
    setSize(250, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addWindowListener(new WindowCloser());
    
    //grid layout to hold panel items
    setLayout(new GridLayout(6,2,2,2));
    
    // radio buttons 
    ButtonGroup radioGroup = new ButtonGroup();
    iterativeButton.setFont(guiFont);
    recursiveButton.setFont(guiFont);
    radioGroup.add(iterativeButton);
    radioGroup.add(recursiveButton);
    add(new JLabel());
    add(iterativeButton);
    add(new JLabel());
    add(recursiveButton);
    
    // enter n section
    JLabel nLabel = new JLabel(" Enter n:");
    nLabel.setFont(guiFont);
    add(nLabel);
    nField.setFont(inputFont);
    add(nField);
    
    // compute button
    add(new JLabel());
    computeButton.setFont(guiFont);
    computeButton.addActionListener(event -> computeValue());
    add(computeButton);
    
    // result section
    JLabel resultLabel = new JLabel(" Result:");
    resultLabel.setFont(guiFont);
    add(resultLabel);
    resultField.setFont(inputFont);
    resultField.setEditable(false);
    add(resultField);

    // efficiency section
    JLabel efficiencyLabel = new JLabel(" Efficiency:");
    efficiencyLabel.setFont(guiFont);
    add(efficiencyLabel);
    efficiencyField.setFont(inputFont);
    efficiencyField.setEditable(false);
    add(efficiencyField);
    
  } // end SequenceGui
  
  
  // method for compute button
  private void computeValue() {  
    try {
      checkNumber();
      if (n < 0) { // if n is negative, result and efficiency are -1
        resultField.setText(Integer.toString(element));
        efficiencyField.setText(Integer.toString(efficency));
        }
      else if (iterativeButton.isSelected()) {
        resultField.setText(Integer.toString(ShinyeobProject3.computeIterative(n)));
        efficiencyField.setText(Integer.toString(ShinyeobProject3.getEfficency()));
         }
      else {
        resultField.setText(Integer.toString(ShinyeobProject3.computeRecursive(n)));
        efficiencyField.setText(Integer.toString(ShinyeobProject3.getEfficency()));
        }
      } // end try  
    catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "Please enter a positive integer.");
      } // catch exception from checkNumber
    } // end computeValue
  
  
  // check the input for n
  private void checkNumber() throws NumberFormatException{
    n = Integer.parseInt(nField.getText());
  } // end checkNumber

  
  public static void main (String [] args) {  
    SequenceGui project_3 = new SequenceGui();
    project_3.setVisible(true);
  } // end main
  
} // end class
