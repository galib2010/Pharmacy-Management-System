package code_details.common;

import code_details.Abstract.absLoginPage;
import code_details.admin.*;
import code_details.interfaces.*;
import code_details.user.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoginPage extends absLoginPage implements ActionListener,ICommonInterface {
    private JFrame frame;
    private JPanel panel1,panel2;
    private JButton LoginButton,changePasswordButton, forgetpassButton,adminLoginButton;
    private JTextField TextField;
    private JPasswordField passwordField;
    public LoginPage(){
        this.setFrame();
        this.setPanels();
        this.setTextField();
        this.setButtons();

        frame.setVisible(true);
    }
    public void setFrame(){
        frame=new JFrame("Login");
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setBackground(Color.cyan);
        frame.setResizable(false);
        frame.setLayout(null);

        ImageIcon logo1 = new ImageIcon("C:\\Pharmacy\\Pharmacy\\pictures\\Media (1).jpg");
        frame.setIconImage(logo1.getImage());

        ImageIcon imageIcon1=new ImageIcon("C:\\Pharmacy\\Pharmacy\\pictures\\Media (1).jpg");
        Image image1= imageIcon1.getImage();
        Image scaledImage=image1.getScaledInstance(1000,1000,4);
        ImageIcon scaledIcon1=new ImageIcon(scaledImage);

        JLabel backgroundLabel=new JLabel(scaledIcon1);
        backgroundLabel.setBounds(0,0,1000,1000);
        frame.setContentPane(backgroundLabel);
    }

    public void setPanels(){
        panel1=new JPanel();
        panel1.setBounds(250, 40, 600, 100);
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(null);

        JLabel label1=new JLabel("ALPHA PHARMACY",JLabel.CENTER);
        label1.setBounds(0,0,600,100);
        label1.setForeground(new Color(144, 12, 63 ));
        label1.setFont(new Font("Montserrat",Font.BOLD,50));
        panel1.setBackground(new Color(255,255,255,120));
        panel1.add(label1);
        frame.add(panel1);

        panel2=new JPanel();
        panel2.setBounds(220,300,600,500);
        panel2.setBackground(new Color(0,255,0,0));
        panel2.setLayout(null);
        frame.add(panel2);
    }
    public void setTextField(){
        JLabel userNameLabel=new JLabel("UserName :");
        userNameLabel.setForeground(new Color(255,255,255));
        userNameLabel.setFont(new Font("Arial",Font.BOLD,14));
        userNameLabel.setBounds(160,20,100,25);
        panel2.add(userNameLabel);

        TextField=new JTextField();
        TextField.setBounds(160,50,300,30);
        panel2.add(TextField);

        JLabel passLabel=new JLabel("Password :");
        passLabel.setForeground(new Color(255,255,255));
        passLabel.setFont(new Font("Arial",Font.BOLD,14));
        passLabel.setBounds(160,85,100,25);
        panel2.add(passLabel);

        passwordField=new JPasswordField();
        passwordField.setBounds(160,115,300,30);
        panel2.add(passwordField);
    }
    public void setButtons(){
        LoginButton=new JButton("Login");
        LoginButton.setBounds(190,165,100,25);
        LoginButton.setFocusable(false);
        LoginButton.addActionListener(this);
        panel2.add(LoginButton);

        /*changePasswordButton=new JButton("Change Password");
        changePasswordButton.setBounds(300,165,150,25);
        changePasswordButton.setFocusable(false);
        changePasswordButton.addActionListener(this);
        panel2.add(changePasswordButton);
         */

        forgetpassButton =new JButton("Forgot Password??");
        forgetpassButton.setBounds(300,165,150,25);
        forgetpassButton.setFocusable(false);
        forgetpassButton.addActionListener(this);
        panel2.add(forgetpassButton);

        adminLoginButton=new JButton("Admin");
        adminLoginButton.setBounds(245,200,100,25);
        adminLoginButton.setFocusable(false);
        adminLoginButton.addActionListener(this);
        panel2.add(adminLoginButton);

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == LoginButton) {
            String username = TextField.getText();
            String password = new String(passwordField.getPassword());
            if(username.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(frame, "username and password can not be empty !!!");
            }
            else if(username.length()<6 && password.length()<6){
                JOptionPane.showMessageDialog(frame, "username and password can not be less than 6 charecters !!!");
            }
            else check(username,password);
        }
        if(e.getSource() == forgetpassButton){
            String email=JOptionPane.showInputDialog(frame,"Enter Your Email:","E-Mail",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(frame,"Key sent to your Email.","key",JOptionPane.INFORMATION_MESSAGE);
            String key=JOptionPane.showInputDialog(frame,"Enter key--");
            if(key.equals("1234")){
                forgetPassDetails(email);
            }
        }
        /*if(e.getSource() == changePasswordButton){
            String username=JOptionPane.showInputDialog(frame,"Enter UserName:");
            String password=JOptionPane.showInputDialog(frame,"Enter old Password:");
            String newPass=JOptionPane.showInputDialog(frame,"Enter new Password!!!");
            updatePassword(username,password,newPass);
        }
         */
        if(e.getSource()==adminLoginButton){
            String str1=JOptionPane.showInputDialog(frame,"Enter Password : ");
            if(str1.isEmpty()){
                JOptionPane.showMessageDialog(frame,"Admin Password can't be empty !!");
            }
            if(str1.equals("galib*#2010")){
                new AdminPanel();
                frame.dispose();
            }
        }
    }

    public void check(String username,String password){
           try (BufferedReader reader = new BufferedReader(new FileReader("details.txt"))){
               String line = "";
               int x=0;
               while (( line=reader.readLine()) != null) { 
                   if (line.equals(username + " " + password)) {
                      new HomePage();
                      frame.dispose();
                       x++;
                       break;
                       
                   }
               }
               if (x == 0) {
                   JOptionPane.showMessageDialog(frame, "Wrong username password :)");
               }
           }catch(FileNotFoundException e){
               System.out.println("file not found");
           }
           catch(IOException e){
               System.out.println("ioException");
           }
           catch(Exception e){
               System.out.println("Something went wrong :)");
           }
    }

    public void forgetPassDetails(String email){
       try(BufferedReader reader2 = new BufferedReader(new FileReader("emailPass.txt"))){
           String mailLine="";
           String userNameLine="";
           String passwordLine="";
           boolean found=false;
           while( (mailLine=reader2.readLine())!=null){
               userNameLine=reader2.readLine();
               passwordLine=reader2.readLine();
               reader2.readLine();//emptyline skip

               if(mailLine.equals(email)){
                   JOptionPane.showMessageDialog(frame,"E-mail:"+mailLine+"\n"+"User Name:"+userNameLine+"\n"+"Password:"+passwordLine,"ID INFO:",JOptionPane.INFORMATION_MESSAGE);
               found=true;
               break;
               }
           }
           if(!found){
               JOptionPane.showMessageDialog(frame,"Email not found !!","!found",JOptionPane.ERROR_MESSAGE);
           }
       } catch (FileNotFoundException e) {
          JOptionPane.showMessageDialog(frame,"File not Found!!!");
       } catch (IOException e) {
           JOptionPane.showMessageDialog(frame,"Something is wrong!!!");
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(frame,"something went wrong !!!");
        }
    }

    /*private void updatePassword(String username,String password,String newPass){
        boolean found=false;
        String userPass =username+" "+password;
        Vector<String>v1= new Vector<>();

        try(BufferedReader reader3=new BufferedReader(new FileReader("C:\\Users\\Admin\\Documents\\project-java\\pharmacy\\details.txt"))){
            String s;
            while((s=reader3.readLine())!=null){
                if(s.equals(userPass)){
                    found=true;
                }
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(frame,"Something is wrong !!!");
        }

        if(!found) JOptionPane.showMessageDialog(frame,"Username not found !!!","Error",JOptionPane.ERROR_MESSAGE);
        else{
            try(BufferedReader reader4=new BufferedReader(new FileReader("C:\\Users\\Admin\\Documents\\project-java\\pharmacy\\details.txt"))){
                String temp;
                while((temp=reader4.readLine())!=null){
                    v1.add(temp);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(frame,"Something is wrong !!!");
            }
            int index=v1.indexOf(userPass);

            String updatedUserPass=username+" "+newPass;
            v1.set(index,updatedUserPass);//replace with notun password
            try{
                FileWriter writer=new FileWriter(new File("C:\\Users\\Admin\\Documents\\project-java\\pharmacy\\details.txt"),false);
                writer.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(frame,"Something is wrong !!!");
            }
            try(FileWriter writer1=new FileWriter("C:\\Users\\Admin\\Documents\\project-java\\pharmacy\\details.txt",true)){
               for(int i=0;i<v1.size();i++){
                   writer1.write(v1.elementAt(i)+"\n");
               }
               JOptionPane.showMessageDialog(frame,"Password Updated !!","update",JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(frame,"Something is wrong !!!");
            }
        }
    }*/
}
