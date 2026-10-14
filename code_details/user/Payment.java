package code_details.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment implements ActionListener{
       private JFrame frame;
       private  JButton buyButton;
        private JTextField addressField,numberField;
       private  JPanel panel1,panel2;

        public Payment(){
            this.setFrame();
            this.setPanels();
            this.setTextFields();
            this.setButtons();

            frame.setVisible(true);
        }

        private void setFrame(){
            frame = new JFrame("Payment");
            frame.setSize(1000, 1000);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLayout(null);
            frame.getContentPane().setBackground(new Color(235, 51, 255, 234));
            frame.setResizable(false);


        }
        private void setPanels(){
            panel1 = new JPanel();
            panel1.setBounds(250, 100, 500, 100);
            panel1.setBackground(new Color(0,255,0,0));
            panel1.setLayout(null);

            JLabel paymentLabel = new JLabel("PAYMENT", JLabel.CENTER);
            paymentLabel.setVerticalTextPosition(JLabel.TOP);
            paymentLabel.setFont(new Font("Arial", Font.BOLD, 30));
            paymentLabel.setBounds(0, 0, 500, 100);
            panel1.add(paymentLabel);
            frame.add(panel1);

            panel2 = new JPanel();
            panel2.setBounds(250, 250, 500, 600);
            panel2.setBackground(new Color(0,255,0,0));
            panel2.setLayout(null);
            frame.add(panel2);
        }
        private void setTextFields(){
            JLabel addressLabel = new JLabel("Address:");
            addressLabel.setBounds(20, 20, 100, 25);
            addressLabel.setFont(new Font("Arial",Font.BOLD,14));
            panel2.add(addressLabel);

            addressField = new JTextField();
            addressField.setBounds(20, 50, 450, 30);
            panel2.add(addressField);

            JLabel numberLabel = new JLabel("Enter Your BKASH Number:");
            numberLabel.setBounds(20, 100, 200, 25);
            numberLabel.setFont(new Font("Arial",Font.BOLD,14));
            panel2.add(numberLabel);

            numberField = new JTextField();
            numberField.setBounds(20, 130, 450, 30);
            panel2.add(numberField);
        }
        private void setButtons(){
            buyButton = new JButton("Place Order");
            buyButton.setBounds(160, 180, 150, 30);
            buyButton.addActionListener(this);
            buyButton.setFocusable(false);
            panel2.add(buyButton);
        }

        public void actionPerformed(ActionEvent e){
            if(e.getSource()== buyButton){
                String str1=addressField.getText();
                String str2=numberField.getText();
                if(str1.isEmpty() || str2.isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Address and number can not be Emty !!!","Wrong information!",JOptionPane.WARNING_MESSAGE);
                }else if(str2.length() != 11){
                    JOptionPane.showMessageDialog(frame,"Number should be 11 digits !!!","Check!",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    new Bkash();
                    frame.dispose();
                }
            }

        }
}

