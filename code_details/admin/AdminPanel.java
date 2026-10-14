package code_details.admin;

import code_details.Abstract.*;
import code_details.common.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class AdminPanel extends absAdminPanel implements ActionListener {
    private JFrame frame;
    private JPanel upPanel,leftPanel,rightPanel0;
    private JButton homeButton,userInfo,priceButton,salesButton,addUpdate,Remove,adminProfile,addMedButton,removeMedButton,stockButton,removeUserButton,logoutButton;

    private JTextField medNameField,medName2Field,medName3Field,price2Field,imgPathFeild, stock2Feild,priceFeild,typeFeild,typeUpdateFeild,addMedstockFeild,removeMedFeild,removeUserFeild;

    public AdminPanel(){
        this.setFrame();
        this.setPanel();
        this.setButtons();
        frame.setVisible(true);
    }
    private void setFrame(){
        frame=new JFrame("Alpha Pharmacy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        ImageIcon logo1 = new ImageIcon("C:\\Pharmacy\\Pharmacy\\pictures\\Media (1).jpg");
        frame.setIconImage(logo1.getImage());
        frame.setResizable(false);
        frame.setLayout(null);
    }
    private void setPanel(){
        upPanel = new JPanel();
        upPanel.setBackground(new Color(8, 55, 76));
        upPanel.setBounds(0, 0, 1000, 100);
        upPanel.setLayout(null);

        leftPanel = new JPanel(new GridLayout(20,1,15,15));
        leftPanel.setBackground(new Color(135, 206, 235));
        leftPanel.setBounds(10, 110, 250, 840);
        leftPanel.setLayout(null);

        rightPanel0 = new JPanel();
        rightPanel0.setBackground(new Color(189, 175, 175));
        rightPanel0.setBounds(270, 110, 705, 840);
        rightPanel0.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome Admin!!!", JLabel.CENTER);
        welcomeLabel.setVerticalTextPosition(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setBounds(100, 300, 500, 50);
        rightPanel0.add(welcomeLabel);

        frame.add(upPanel);
        frame.add(leftPanel);
        frame.add(rightPanel0);
    }
    private void setButtons(){
        homeButton = new JButton("Medicine_Details");
        homeButton.setBounds(10,10,230,70);
        homeButton.addActionListener(this);
        homeButton.setFocusable(false);

        userInfo = new JButton("User Info");
        userInfo.setBounds(10,90,230,70);
        userInfo.addActionListener(this);
        userInfo.setFocusable(false);

        salesButton = new JButton("Sales");
        salesButton.setBounds(10,170,230,70);
        salesButton.addActionListener(this);
        salesButton.setFocusable(false);

        addUpdate = new JButton("Update Medicine");
        addUpdate.setBounds(10,250,230,70);
        addUpdate.addActionListener(this);
        addUpdate.setFocusable(false);

        Remove = new JButton("Remove");
        Remove.setBounds(10,330,230,70);
        Remove.addActionListener(this);
        Remove.setFocusable(false);

        adminProfile = new JButton("Profile");
        adminProfile.setBounds(10,410,230,70);
        adminProfile.addActionListener(this);
        adminProfile.setFocusable(false);

        logoutButton=new JButton("Log Out");
        logoutButton.setBounds(10,490,230,70);
        logoutButton.addActionListener(this);
        logoutButton.setFocusable(false);

        leftPanel.add(homeButton);
        leftPanel.add(userInfo);
        leftPanel.add(salesButton);
        leftPanel.add(addUpdate);
        leftPanel.add(Remove);
        leftPanel.add(adminProfile);
        leftPanel.add(logoutButton);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == homeButton){
            medicineDetails();
        }
        if (e.getSource() == userInfo){
            userView();
        }
        if (e.getSource() == salesButton){
            salesInfo();
        }
        if (e.getSource() == addUpdate){
            addUpdate();
        }
        if (e.getSource() == Remove){
            remove();
        }
        if (e.getSource() == adminProfile){
            showAdminDetails();
        }
        if(e.getSource()==logoutButton){
            new LoginPage();
            frame.dispose();
        }

        if (e.getSource() == addMedButton){
            try{
                String name = medNameField.getText();
                String imagePath = imgPathFeild.getText();
                int stock = Integer.parseInt(addMedstockFeild.getText());
                Double price = Double.parseDouble(priceFeild.getText());
                String type = typeFeild.getText();

                if(name.isEmpty() || imagePath.isEmpty() || type.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "All fields must be filled out!");
                }
                saveMedDetails(name, imagePath, stock, price, type);
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(frame, "Please provide correct values for stock and price.");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame, "Something went wrong: ");
            }
        }

        if (e.getSource() == stockButton){
            String name = medName2Field.getText();
            int stock=0;
            stock = Integer.parseInt(stock2Feild.getText());
            if (!name.isEmpty()){
                try{
                    updateStock(name, stock+"");
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Please provide a valid stock number.");
                }
            }else{
                JOptionPane.showMessageDialog(frame, "Fields cannot be empty or 0!");
            }
        }

        if (e.getSource() == priceButton){
            String medname = medName3Field.getText();
            double price=0;
            price = Double.parseDouble(price2Field.getText());
            if (!medname.isEmpty() && price!=0){
                try{
                    updatePrice(medname, price+"");
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please provide a valid price.");
                }
            } else{
                JOptionPane.showMessageDialog(frame, "Fields cannot be empty or 0!");
            }
        }
       if(e.getSource()== removeMedButton){
           String str=removeMedFeild.getText();
           if(str.isEmpty()){
               JOptionPane.showMessageDialog(frame,"Medicine name can not be empty");
           }
           else{
               removemed(str);
           }
        }
        if (e.getSource() == removeUserButton) {
            String userEmail = removeUserFeild.getText();
            if (userEmail.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "User  email cannot be empty");
            } else {
                removeUser(userEmail);
            }
        }
    }

public void removeUser (String email) {
    Vector<String> users = new Vector<>();
    String temp;
    try (BufferedReader reader = new BufferedReader(new FileReader("emailPass.txt"))){
        while ((temp = reader.readLine()) != null) {
            users.add(temp);
            users.add(reader.readLine()); // username
            users.add(reader.readLine()); // password
            reader.readLine(); // skip line space
        }
    } catch (IOException e){
        JOptionPane.showMessageDialog(frame, "Problem with file output!!");
        return;
    }

    boolean userFound = false;
    for (int i = 0; i < users.size(); i += 4) {
        if (users.get(i).equals(email)) {
            userFound = true;
            users.remove(i); // Remove email
            users.remove(i); // Remove username
            users.remove(i); // Remove password
            users.remove(i); // Remove line space
            break;
        }
    }

    if (userFound) {
        try(FileWriter writer = new FileWriter("emailPass.txt")){
            for (String user : users) {
                writer.write(user + "\n");
            }
            JOptionPane.showMessageDialog(frame, "User  removed successfully!");
        } catch(IOException e){
            JOptionPane.showMessageDialog(frame, "Problem with file writing!!");
        }
    } else{
        JOptionPane.showMessageDialog(frame, "User  not found!");
    }
}
    public void removemed(String name){
        Vector<String>medicine=new Vector<>();
        String temp;
        try(BufferedReader reader=new BufferedReader(new FileReader("medDetails.txt"))){
            while((temp= reader.readLine())!=null){
                medicine.add(temp);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(frame,"problem with file output!!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame,"something is wrong!!!");
        }
        int k=medicine.indexOf(name);
        medicine.remove(k);
        medicine.remove(k);
        medicine.remove(k);
        medicine.remove(k);

        try{
            FileWriter writer=new FileWriter("medDetails.txt");
            writer.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame,"something is wrong!!!");
        }
            try (FileWriter writer = new FileWriter("medDetails.txt")) {
             for(int i=0;i<medicine.size();i++) {
                 writer.write(medicine.get(i) + "\n");
             }
             JOptionPane.showMessageDialog(frame,"Medicine removed!!!");
            }catch(IOException e){
                JOptionPane.showMessageDialog(frame,"problem with file writing!!");
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(frame,"something is wrong!!!");
            }
    }

    public void medicineDetails(){
        rightPanel0.removeAll();
        rightPanel0.setLayout(new BoxLayout(rightPanel0, BoxLayout.Y_AXIS));
        JLabel[] medLabel=new JLabel[10000];

        String medName;
        int s;
        double p;
        String str="";
        int i=1;

        File file0 = new File("medDetails.txt");
        try {
            if (!file0.exists()) {
                JOptionPane.showMessageDialog(frame, "medDetails file missing!!");
                file0.createNewFile();
            }
        }catch(Exception e){

        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file0))){
            while ((medName = reader.readLine()) != null) {
                reader.readLine();//skipping imgpath
                s = Integer.parseInt(reader.readLine());
                p = Double.parseDouble(reader.readLine());

                str = i+". Name: " + medName + " | Available: " + s + "pcs | Price: " + p;
                medLabel[i-1] = new JLabel(str);
                medLabel[i-1].setFont(new Font("Arial", Font.BOLD, 14));
                medLabel[i-1].setHorizontalTextPosition(JLabel.LEFT);
                medLabel[i-1].setVerticalTextPosition(JLabel.CENTER);

                rightPanel0.add(medLabel[i-1]);
                i++;
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(frame, "File not found : medDetails.txt!");
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(frame, "Error reading medDetails.txt!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "something went wrong!","warning",JOptionPane.WARNING_MESSAGE);
        }

        rightPanel0.revalidate();
        rightPanel0.repaint();

    }



    public void userView(){
          rightPanel0.removeAll();
          rightPanel0.setLayout(new BoxLayout(rightPanel0, BoxLayout.Y_AXIS));

          try(BufferedReader reader=new BufferedReader(new FileReader("emailPass.txt"))){
              String email,username,password;
              while((email=reader.readLine())!=null){
                  rightPanel0.add(new JLabel("Email: "+email));
                  username=reader.readLine();
                  rightPanel0.add(new JLabel("UserName :"+username));
                  password=reader.readLine();
                  rightPanel0.add(new JLabel("PassWord: "+password));
                  rightPanel0.add(new JLabel(" "));
                  reader.readLine();//skipping line space
              }

          }catch(Exception e){
                JOptionPane.showMessageDialog(frame,"Provide correct inputs");
          }

          rightPanel0.revalidate();
          rightPanel0.repaint();
    }
    public void salesInfo(){

    }
    public void addUpdate() {
        rightPanel0.removeAll();
        rightPanel0.setLayout(null);

        JLabel addMedLable = new JLabel("Add Medicine : ");
        addMedLable.setBounds(10, 10, 150, 40);
        addMedLable.setFont(new Font("Arial", Font.BOLD, 20));
        rightPanel0.add(addMedLable);

        JLabel medNameLable = new JLabel("Medicine Name :");
        medNameLable.setBounds(10, 50, 100, 25);
        medNameLable.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(medNameLable);

        medNameField = new JTextField();
        medNameField.setBounds(10, 80, 300, 30);
        rightPanel0.add(medNameField);

        JLabel imgPathLabel = new JLabel("Image Path :");
        imgPathLabel.setBounds(10, 115, 100, 25);
        imgPathLabel.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(imgPathLabel);

        imgPathFeild = new JTextField();
        imgPathFeild.setBounds(10, 145, 300, 30);
        rightPanel0.add(imgPathFeild);

        JLabel stockLabel = new JLabel("Stock :");
        stockLabel.setBounds(10, 180, 50, 25);
        stockLabel.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(stockLabel);

        addMedstockFeild = new JTextField();
        addMedstockFeild.setBounds(10, 210, 140, 30);
        rightPanel0.add(addMedstockFeild);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(170, 180, 50, 25);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(priceLabel);

        priceFeild = new JTextField();
        priceFeild.setBounds(170, 210, 140, 30);
        rightPanel0.add(priceFeild);

        JLabel typeLabel = new JLabel("Category :");
        typeLabel.setBounds(10, 235, 100, 25);
        typeLabel.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(typeLabel);

        typeFeild = new JTextField();
        typeFeild.setBounds(10, 265, 300, 30);
        rightPanel0.add(typeFeild);

        addMedButton = new JButton("Add");
        addMedButton.setBounds(130, 305, 60, 30);
        addMedButton.addActionListener(this);
        addMedButton.setFocusable(false);
        rightPanel0.add(addMedButton);

        // Update Price part
        JLabel updatePrice = new JLabel("Update Price :");
        updatePrice.setBounds(10, 350, 150, 40);
        updatePrice.setFont(new Font("Arial", Font.BOLD, 20));
        rightPanel0.add(updatePrice);

        JLabel medName3label = new JLabel("Medicine name: ");
        medName3label.setBounds(10, 400, 100, 25);
        medName3label.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(medName3label);

        medName3Field = new JTextField();
        medName3Field.setBounds(10, 430, 300, 30);
        rightPanel0.add(medName3Field);

        JLabel priceLabel2 = new JLabel("Current Price :");
        priceLabel2.setBounds(10, 470, 100, 25);
        priceLabel2.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(priceLabel2);

        price2Field = new JTextField();
        price2Field.setBounds(10, 500, 300, 30);
        rightPanel0.add(price2Field);

        priceButton = new JButton("Update");
        priceButton.setBounds(110, 540, 80, 30);
        priceButton.addActionListener(this);
        rightPanel0.add(priceButton);

        // Add Stock part

        JLabel addStockLabel = new JLabel("Update Stock:");
        addStockLabel.setBounds(340, 10, 150, 40);
        addStockLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rightPanel0.add(addStockLabel);

        JLabel stockNameLabel = new JLabel("Medicine Name:");
        stockNameLabel.setBounds(340, 50, 100, 25);
        stockNameLabel.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(stockNameLabel);

        medName2Field = new JTextField();
        medName2Field.setBounds(340, 80, 300, 30);
        rightPanel0.add(medName2Field);

        JLabel stockValueLabel = new JLabel("Stock:");
        stockValueLabel.setBounds(340, 120, 50, 25);
        stockValueLabel.setFont(new Font("Arial", Font.BOLD, 11));
        rightPanel0.add(stockValueLabel);

        stock2Feild = new JTextField();
        stock2Feild.setBounds(340, 150, 300, 30);
        rightPanel0.add(stock2Feild);

        stockButton = new JButton("Update");
        stockButton.setBounds(440, 190, 80, 30);
        stockButton.addActionListener(this);
        rightPanel0.add(stockButton);

        rightPanel0.revalidate();
        rightPanel0.repaint();
    }
//.................................................................
        public void remove(){
           rightPanel0.removeAll();
           rightPanel0.setLayout(null);

            JLabel medRemoveLabel = new JLabel("Remove Medicine : ");
            medRemoveLabel.setBounds(10, 10, 300, 40);
            medRemoveLabel.setFont(new Font("Arial", Font.BOLD, 20));
            rightPanel0.add(medRemoveLabel);

            JLabel medNameLable = new JLabel("Medicine Name :");
            medNameLable.setBounds(10, 50, 100, 25);
            medNameLable.setFont(new Font("Arial", Font.BOLD, 11));
            rightPanel0.add(medNameLable);

            removeMedFeild = new JTextField();
            removeMedFeild.setBounds(10, 80, 300, 30);
            rightPanel0.add(removeMedFeild);

            removeMedButton = new JButton("Remove");
            removeMedButton.setBounds(130, 120, 100, 30);
            removeMedButton.addActionListener(this);
            removeMedButton.setFocusable(false);
            rightPanel0.add(removeMedButton);

            JLabel removeUserLabel=new JLabel("Remove User :");
            removeUserLabel.setBounds(10, 160, 150, 40);
            removeUserLabel.setFont(new Font("Arial", Font.BOLD, 20));
            rightPanel0.add(removeUserLabel);

            removeUserFeild = new JTextField();
            removeUserFeild.setBounds(10, 200, 300, 30);
            rightPanel0.add(removeUserFeild);

            removeUserButton = new JButton("Remove");
            removeUserButton.setBounds(120, 240, 100, 30);
            removeUserButton.addActionListener(this);
            removeUserButton.setFocusable(false);
            rightPanel0.add( removeUserButton);

            rightPanel0.revalidate();
            rightPanel0.repaint();


    }
    //...................................................................................
    public void showAdminDetails(){

    }
    public void saveMedDetails(String medName,String imgPath,int s,double p,String type){
        try(FileWriter writer = new FileWriter("medDetails.txt", true)){
            writer.write(medName+"\n"+imgPath+"\n"+s+"\n"+p+"\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(frame,"File not found : medDetails.txt!!!","Something went wrong !",JOptionPane.WARNING_MESSAGE);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(frame,"Problem with file outPut!!!","Something went wrong !",JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Something is wrong!!!","Something went wrong !",JOptionPane.WARNING_MESSAGE);
        }
        boolean typefound=false;
        try(BufferedReader reader=new BufferedReader(new FileReader("categoryButtons.txt"))){
            String temp;
            while((temp=reader.readLine())!=null){
                if(temp.equals(type))
                    typefound=true;
            }
        }catch(FileNotFoundException e){
            System.out.println("catagorybutton file not found");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Something is wrong!!!","Something went wrong !",JOptionPane.WARNING_MESSAGE);
        }
        if(!typefound){
            try (FileWriter writerx = new FileWriter("categoryButtons.txt",true)) {
                   writerx.write(type+"\n");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Problem with file outPut!!!","Something went wrong !",JOptionPane.WARNING_MESSAGE);
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(frame, "Something is wrong!!!","Something went wrong !",JOptionPane.WARNING_MESSAGE);
            }
        }

        File file=new File(type+".txt");
        if (!file.exists()) {
            try{
                file.createNewFile();
            }catch (Exception e) {
                JOptionPane.showMessageDialog(frame,"problem with file creation type.txt!!!");
            }
        }
        try(FileWriter writer2 = new FileWriter(type +".txt",true)){
            writer2.write(medName+"\n"+imgPath+"\n"+s+"\n"+p+"\n");
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, "Something is wrong!!!", "Something went wrong !", JOptionPane.WARNING_MESSAGE);
        }
        if(typefound)
            JOptionPane.showMessageDialog(frame,"Successfully medicine added!!!");
        if(!typefound)
            JOptionPane.showMessageDialog(frame,"Successfully medicine and catagory added!!!");
    }
    public void updateStock(String Name, String stock){
        Vector<String>medicine=new Vector<>();
        String temp;
        try(BufferedReader reader=new BufferedReader(new FileReader("medDetails.txt"))){
              while((temp=reader.readLine())!=null){
                  medicine.add(temp);
              }
        }catch (IOException e){
            JOptionPane.showMessageDialog(frame, "Problem with file outPut!!!", "Something went wrong !", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e){
            System.out.println("file missing");
        }
        boolean found=false;

        for( int i=0;i<medicine.size();i++){
            if(Name.equals(medicine.get(i))){
                found=true;
                break;
            }
        }

        if(found){
            int j = medicine.indexOf(Name);
            medicine.set(j+2,stock);
        }else{
            JOptionPane.showMessageDialog(frame,"Medicine not found in the inventory !!");
        }

        try{
            FileWriter writer2=new FileWriter(new File("medDetails.txt"),false);
            writer2.close();;
        }catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Problem with file!!!", "Something went wrong !", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e){
            System.out.println("file not gettig empty");
        }
        try(FileWriter writer=new FileWriter("medDetails.txt",true)){
            for(int i=0;i< medicine.size();i++){
                writer.write(medicine.elementAt(i)+"\n");
            }
            JOptionPane.showMessageDialog(frame,"Stock updated Successfully!!");
        }catch(IOException e){
            JOptionPane.showMessageDialog(frame, "Problem with file writing!!!", "Something went wrong !", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e){
            System.out.println("file nikhoj");
        }

    }
    public void updatePrice(String Name, String price){
        Vector<String>medicine=new Vector<>();
        String temp;
        try(BufferedReader reader=new BufferedReader(new FileReader("medDetails.txt"))){
            while((temp=reader.readLine())!=null){
                medicine.add(temp);
            }
        }catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Problem with file outPut!!!", "Something went wrong !", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Something has occured!!!", "Something went wrong !", JOptionPane.WARNING_MESSAGE);
        }
        boolean found=false;

        for( int i=0;i<medicine.size();i++){
            if(Name.equals(medicine.get(i))){
                found=true;
                break;
            }
        }


        if(found) {
            int j = medicine.indexOf(Name);
            medicine.set(j+3,price);
        }else{
            JOptionPane.showMessageDialog(frame,"Medicine not found in the inventory !!");
        }

        try{
            FileWriter writer2=new FileWriter(new File("medDetails.txt"),false);
            writer2.close();
        }catch(Exception e){
            System.out.println("file not gettig empty");
        }
        try(FileWriter writer=new FileWriter("medDetails.txt",true)){
            for(int i=0;i< medicine.size();i++){
                writer.write(medicine.elementAt(i)+"\n");
            }
            JOptionPane.showMessageDialog(frame,"Price updated Successfully!!");
        }catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Problem with file writing!!!", "Something went wrong !", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Problem with file writing!!!");
        }

    }

}