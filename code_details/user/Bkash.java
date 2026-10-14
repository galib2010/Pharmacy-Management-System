package code_details.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bkash implements ActionListener{
    private JFrame frame;
    private JPasswordField pinField;
    private JPanel panel1,panel2;
    private JButton payButton;

    public Bkash(){
        this.setFrame();
        this.setPanels();
        this.setPinField();
        this.setButton();

        frame.setVisible(true);
    }

    private void setFrame(){
        frame=new JFrame("BKASH");
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(255,51,153));
    }
    public void setPanels(){
        panel1 = new JPanel();
        panel1.setBounds(250, 100, 500, 100);
        panel1.setBackground(new Color(0,255,0,0));
        panel1.setLayout(null);

        JLabel bkashLabel = new JLabel("BKASH", JLabel.CENTER);
        bkashLabel.setVerticalTextPosition(JLabel.TOP);
        bkashLabel.setFont(new Font("Arial", Font.BOLD, 50));
        bkashLabel.setBounds(0, 0, 500, 100);
        panel1.add(bkashLabel);
        frame.add(panel1);

        panel2 = new JPanel();
        panel2.setBounds(250, 250, 500, 600);
        panel2.setBackground(new Color(0,255,0,0));
        panel2.setLayout(null);
        frame.add(panel2);
    }
    public void setPinField(){
        JLabel pinLabel = new JLabel("Enter Your Pin Number:");
        pinLabel.setBounds(20, 20, 300, 25);
        pinLabel.setFont(new Font("Arial",Font.BOLD,14));
        panel2.add(pinLabel);

        pinField=new JPasswordField();
        pinField.setBounds(20,50,450,40);
        panel2.add(pinField);
    }
    public void setButton(){
        payButton = new JButton("Pay");
        payButton.setBounds(190, 100, 70, 30);
        payButton.addActionListener(this);
        payButton.setFocusable(false);
        panel2.add(payButton);
    }

    public void actionPerformed(ActionEvent e){
              if(e.getSource()==payButton){
                  String str1=new String(pinField.getPassword());
                  if(str1.isEmpty()){
                      JOptionPane.showMessageDialog(frame,"Pin number can not be Empty !!!","eror",JOptionPane.WARNING_MESSAGE);
                  }
                  else if(str1.length()>5){
                      JOptionPane.showMessageDialog(frame,"Pin number can not be more than 5 digits !!!","eror",JOptionPane.WARNING_MESSAGE);
                  }
                  else{
                      JOptionPane.showMessageDialog(frame,"Payment Successfull !!!","Congratulations !!!",JOptionPane.INFORMATION_MESSAGE);
                      frame.dispose();
                  }
              }
    }
}
