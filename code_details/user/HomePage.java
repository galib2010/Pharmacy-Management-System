package code_details.user;
import code_details.interfaces.*;
import code_details.common.*;
import code_details.admin.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

public class HomePage implements ActionListener{
    JFrame frame;
    JPanel upPanel, leftPanel, rightPanel0, contentPanel;
    JPanel[] medPanel=new JPanel[10000];
    JLabel[] titleLabel=new JLabel[10000];
    JLabel[] stockLabel=new JLabel[10000];
    JButton[] addButton=new JButton[10000];
    JButton[] catagoryButton=new JButton[1000];
    JButton cartButton=new JButton("Cart");
    JButton logoutButton=new JButton("Log Out");
    JTextField[] quantityFeild= new JTextField[10000];
    double[] priceList = new double[10000];

    static int i=0;
    static int j;

    Cart cart = new Cart();

    public HomePage(){
        this.setFrame();
        this.setPanel();
        this.setButtons();
        this.showMed();
        frame.setVisible(true);
    }

    private void setFrame(){
        frame=new JFrame("Alpha Pharmacy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        frame.setLayout(new BorderLayout());

        contentPanel=new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(1000, 1200));

        JScrollPane scrollPane=new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void setPanel(){
        upPanel=new JPanel();
        upPanel.setBackground(new Color(8, 55, 76));
        upPanel.setBounds(0, 0, 1000, 100);
        upPanel.setLayout(null);

        leftPanel=new JPanel(new GridLayout(20, 1, 15, 15));
        leftPanel.setBackground(new Color(135, 206, 235));
        leftPanel.setBounds(10, 110, 250, 1070);

        rightPanel0=new JPanel();
        rightPanel0.setBackground(new Color(248, 248, 248));
        rightPanel0.setBounds(270, 110, 705, 1070);
        rightPanel0.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        contentPanel.add(upPanel);
        contentPanel.add(leftPanel);
        contentPanel.add(rightPanel0);
    }

    private void setButtons(){
        File buttonFile = new File("categoryButtons.txt");
        Vector<String> buttName = new Vector<>();

        if (buttonFile.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(buttonFile))) {
                String temp;
                while ((temp = reader.readLine()) != null){
                    buttName.add(temp);
                }
            }catch(IOException e) {
                JOptionPane.showMessageDialog(frame, "problem with file output!!");
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(frame, "Error reading category buttons.");
            }
        }

        j = buttName.size();
        for (int x = 0; x < buttName.size(); x++){
            catagoryButton[x] = new JButton(buttName.get(x));
            catagoryButton[x].setPreferredSize(new Dimension(200, 110));
            catagoryButton[x].addActionListener(this);
            catagoryButton[x].setFocusable(false);
            leftPanel.add(catagoryButton[x]);
        }
        logoutButton.setPreferredSize(new Dimension(200,110));
        logoutButton.addActionListener(this);
        logoutButton.setFocusable(false);
        leftPanel.add(logoutButton);

        cartButton.setBounds(900, 20, 60, 60);
        cartButton.addActionListener(this);
        cartButton.setFocusable(false);
        upPanel.add(cartButton);
    }

    public void showMed(){
        rightPanel0.removeAll();
        rightPanel0.revalidate();
        rightPanel0.repaint();

        String medName, imgPath;
        int s;
        double p;
        i = 0;

        File file0 = new File("medDetails.txt");
        if (file0.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file0))){
                while ((medName = reader.readLine()) != null) {
                    imgPath = reader.readLine();
                    s = Integer.parseInt(reader.readLine());
                    p = Double.parseDouble(reader.readLine());
                    readMedFile(medName, imgPath, s, p, i);
                    priceList[i] = p;
                    i++;
                }
            }catch(IOException e) {
                JOptionPane.showMessageDialog(frame, "problem with file output!!");
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error loading medicines.");
            }
        }
    }

    public void showCatagoryMed(String type){
        i = 0;
        rightPanel0.removeAll();
        rightPanel0.revalidate();
        rightPanel0.repaint();
        String medName, imgPath;
        int s;
        double p;

        try (BufferedReader reader = new BufferedReader(new FileReader(type + ".txt"))){
            while ((medName = reader.readLine()) != null) {
                imgPath = reader.readLine();
                s = Integer.parseInt(reader.readLine());
                p = Double.parseDouble(reader.readLine());
                readMedFile(medName, imgPath, s, p, i);
                priceList[i] = p;
                i++;
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(frame, "problem with file output!!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(frame, "Error loading category medicines.");
        }
    }

    public void readMedFile(String medName, String imgPath, int s, double p, int i){
        medPanel[i]=new JPanel();
        medPanel[i].setPreferredSize(new Dimension(200, 250));
        medPanel[i].setBackground(new Color(244, 244, 244));
        medPanel[i].setLayout(null);

        ImageIcon imageIcon1=new ImageIcon(imgPath);
        Image img=imageIcon1.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        titleLabel[i]=new JLabel();
        titleLabel[i].setIcon(new ImageIcon(img));
        titleLabel[i].setText(medName);
        titleLabel[i].setHorizontalTextPosition(JLabel.CENTER);
        titleLabel[i].setVerticalTextPosition(JLabel.CENTER);
        titleLabel[i].setBounds(40, 30, 120, 160);

        stockLabel[i]=new JLabel("Available: " + s + " pcs", JLabel.RIGHT);
        stockLabel[i].setBounds(90, 10, 100, 20);

        JLabel priceLabel=new JLabel("৳ " + p, JLabel.CENTER);
        priceLabel.setBounds(5, 5, 80, 30);

        quantityFeild[i]=new JTextField("Q");
        quantityFeild[i].setBounds(20, 200, 80, 30);
        medPanel[i].add(quantityFeild[i]);

        addButton[i]=new JButton("Add");
        addButton[i].setBounds(110, 200, 70, 30);
        addButton[i].addActionListener(this);

        medPanel[i].add(priceLabel);
        medPanel[i].add(stockLabel[i]);
        medPanel[i].add(titleLabel[i]);
        medPanel[i].add(addButton[i]);
        rightPanel0.add(medPanel[i]);
    }

    public void actionPerformed(ActionEvent e){
        for (int k=0;k<j;k++){
            if (e.getSource()==catagoryButton[k]) {
                showCatagoryMed(catagoryButton[k].getText());
            }
        }
        if (e.getSource() == cartButton) {
            cart.showCart();
        }
        for (int k = 0; k < i; k++) {
            if (e.getSource() == addButton[k]) {
                int quantity = Integer.parseInt(quantityFeild[k].getText());
                if (quantity > 0) {
                    cart.addToCart(titleLabel[k].getText(), priceList[k], quantity);
                    quantityFeild[k].setText(""); // I ADDED THIS to Clear the quantity field after adding
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter a valid quantity!");
                }
            }
        }
        if(e.getSource()==logoutButton){
            new LoginPage();
            frame.dispose();
        }
    }
}