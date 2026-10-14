package code_details.user;

import code_details.common.*;
import code_details.interfaces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Registration extends JFrame implements ActionListener,IRegistrationInterface, ICommonInterface {
    private JButton RegisterButton,LoginButton;
    private JPanel panel1, panel2;
    private JTextField usernameField,emailField;
    private JPasswordField passwordField,passwordField2;
    public Registration(){
        this.setFrame();
        this.setPanels();
        this.setTextField();
        this.setButtons();

        this.setVisible(true);
    }
    public void setFrame(){
        new JFrame("Registration");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        ImageIcon logo = new ImageIcon("C:\\Pharmacy\\Pharmacy\\pictures\\Media (2).jpg");
        this.setIconImage(logo.getImage());

        ImageIcon imageIcon = new ImageIcon("C:\\Pharmacy\\Pharmacy\\pictures\\Media (2).jpg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 1000, 1000);

        this.setContentPane(backgroundLabel);
    }

    public void setPanels(){
        panel1 = new JPanel();
        panel1.setBounds(250, 10, 500, 100);
        panel1.setBackground(new Color(0,255,0,0));
        panel1.setLayout(null);

        JLabel titleLabel = new JLabel("ALPHA PHARMACY", JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setForeground(new Color(144, 12, 63 ));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setBounds(0, 0, 500, 100);
        panel1.add(titleLabel);
        this.add(panel1);

        panel2 = new JPanel();
        panel2.setBounds(250, 250, 500, 600);
        panel2.setBackground(new Color(0,255,0,0));
        panel2.setLayout(null);
        this.add(panel2);
    }

    public void setTextField(){
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 100, 25);
        usernameLabel.setForeground(new Color(255,255,255));
        usernameLabel.setFont(new Font("Arial",Font.BOLD,14));
        panel2.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(20, 50, 450, 30);
        panel2.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(255,255,255));
        passwordLabel.setBounds(20, 100, 100, 25);
        passwordLabel.setFont(new Font("Arial",Font.BOLD,14));
        panel2.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(20, 130, 450, 30);
        panel2.add(passwordField);

        JLabel passwordLabel2=new JLabel("Confirm Password:");
        passwordLabel2.setForeground(new Color(255,255,255));
        passwordLabel2.setBounds(20,180,200,25);
        passwordLabel2.setFont(new Font("Arial",Font.BOLD,14));
        panel2.add(passwordLabel2);

        passwordField2=new JPasswordField();
        passwordField2.setBounds(20,210,450,30);
        panel2.add(passwordField2);

        JLabel emailLabel=new JLabel("E-Mail:");
        emailLabel.setForeground(new Color(255,255,255));
        emailLabel.setBounds(20,260,200,25);
        emailLabel.setFont(new Font("Arial",Font.BOLD,14));
        panel2.add(emailLabel);

        emailField=new JTextField();
        emailField.setBounds(20,290,450,30);
        panel2.add(emailField);

    }

    public void setButtons(){
        RegisterButton = new JButton("Register");
        RegisterButton.setBounds(100, 340, 100, 40);
        RegisterButton.addActionListener(this);
        RegisterButton.setFocusable(false);
        panel2.add(RegisterButton);

        LoginButton=new JButton("Go Login");
        LoginButton.setBounds(250,340,100,40);
        LoginButton.addActionListener(this);
        LoginButton.setFocusable(false);
        panel2.add(LoginButton);
    }

 public void actionPerformed(ActionEvent e){
     if (e.getSource() == RegisterButton) {
         String username = usernameField.getText();
         String email=emailField.getText();
         String password = new String(passwordField.getPassword());
         String confirmPassword=new String(passwordField2.getPassword());
         saveDetails(username, password,confirmPassword,email);
     }
     if(e.getSource() == LoginButton){
         new LoginPage();
         this.dispose();
     }
 }
    public void saveDetails(String username, String password,String confirmPassword,String email){
        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,"Password did not match with Confirm Password !!");
        }
        else if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()){
            JOptionPane.showMessageDialog(this, "Username,Password or email cannot be empty!!", "Error", JOptionPane.WARNING_MESSAGE);
        }

        else{
            File file =new File("details.txt");
            File file1=new File("emailPass.txt");
            if(!file.exists()){
                try{
                    file.createNewFile();
                }catch(Exception e){

                }
            }
            if(!file1.exists()){
                try{
                    file1.createNewFile();
                }catch(Exception e){

                }
            }
            try (FileWriter writer = new FileWriter("details.txt", true)) {
                writer.write(username + " " + password + "\n");
                JOptionPane.showMessageDialog(this, "Registration Successful!");
            }catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving details: ", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try(FileWriter writer2=new FileWriter("emailPass.txt",true)){
                writer2.write(email+"\n"+username+"\n"+password+"\n\n");
            }catch (IOException ex){
                JOptionPane.showMessageDialog(this,"something went wrong!!!");
            }
        }
    }
}

