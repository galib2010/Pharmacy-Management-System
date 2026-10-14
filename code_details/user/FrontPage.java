package code_details.user;
import code_details.common.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage implements ActionListener{
   private JFrame frame;
   private JButton RegisterButton,LoginButton;
   private JPanel panel1,panel2;

   public FrontPage(){
       this.setFrame();
       this.setPanels();
       this.setButtons();

       frame.setVisible(true);
   }

    private void setFrame(){
        frame = new JFrame("Welcome");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setResizable(false);

        ImageIcon logo = new ImageIcon("C:\\Pharmacy\\Pharmacy\\pictures\\pharmaceutical-industry.jpg");
        frame.setIconImage(logo.getImage());

        ImageIcon imageIcon = new ImageIcon("C:\\Pharmacy\\Pharmacy\\pictures\\pharmaceutical-industry.jpg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 1000, 1000);
        frame.setContentPane(backgroundLabel);
    }
    private void setPanels(){
        panel1 = new JPanel();
        panel1.setBounds(250, 100, 500, 150);
        panel1.setBackground(new Color(0,255,0,0));
        panel1.setLayout(null);

        JLabel welcomeLabel = new JLabel("WelCOME !!!", JLabel.CENTER);
        welcomeLabel.setVerticalTextPosition(JLabel.TOP);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setBounds(0, 0, 500, 50);
        panel1.add(welcomeLabel);
        frame.add(panel1);

        JLabel titleLabel = new JLabel("ALPHA PHARMACY", JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(0, 50, 500, 50);
        panel1.add(titleLabel);
        frame.add(panel1);

        panel2 = new JPanel();
        panel2.setBounds(250, 300, 500, 350);
        panel2.setBackground(new Color(0,255,0,0));
        panel2.setLayout(null);
        frame.add(panel2);
    }
    private void setButtons(){
        RegisterButton = new JButton("Register");
        RegisterButton.setBounds(100, 200, 100, 40);
        RegisterButton.addActionListener(this);
        RegisterButton.setFocusable(false);
        panel2.add(RegisterButton);

        LoginButton=new JButton("Go Login");
        LoginButton.setBounds(250,200,100,40);
        LoginButton.addActionListener(this);
        LoginButton.setFocusable(false);
        panel2.add(LoginButton);
    }

    public void actionPerformed(ActionEvent e){
            if(e.getSource()==RegisterButton){
                new Registration();
                frame.dispose();
            }
            if(e.getSource()==LoginButton){
                new LoginPage();
                frame.dispose();
            }
    }
}
