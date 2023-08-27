import javax.swing.*;

public class Login {
    private UserAccount userAccount;
    public Login(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    public void createLoginWindow(){
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 250);
        loginFrame.setLayout(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Enter Account Number:");
        label.setBounds(50, 30, 200, 30);
        loginFrame.add(label);

        JTextField accountField = new JTextField();
        accountField.setBounds(200, 30, 150, 30);
        loginFrame.add(accountField);

        JLabel pinLabel = new JLabel("Enter PIN:");
        pinLabel.setBounds(50, 80, 200, 30);
        loginFrame.add(pinLabel);

        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(200, 80, 150, 30);
        loginFrame.add(pinField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 40);
        loginFrame.add(loginButton);   

        loginButton.addActionListener(e -> {
            String accountNumber = accountField.getText();
            String pin = new String(pinField.getPassword());
        
            if ("123456".equals(accountNumber)&& userAccount.getPin().equals(pin)) { // Removed PIN check here
                loginFrame.dispose(); // Close the login window
                SwingUtilities.invokeLater(() -> {
                    new Choice(userAccount);
                });
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please check your account number.");
            }
        });

        loginFrame.setVisible(true);
    }
    public static void main(String[] args) {
        UserAccount defaultUser = new UserAccount("123456", null, 1000.0);
        Login login = new Login(defaultUser);
        SwingUtilities.invokeLater(() -> {
            login.createLoginWindow();
        });

    }
}
