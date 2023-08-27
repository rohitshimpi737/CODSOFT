import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Choice {
    private UserAccount currentUser;
    private JFrame mainFrame;

    public Choice(UserAccount currentUser) {
        this.currentUser = currentUser;
        initializeComponents();
    }

    public void initializeComponents() {
        mainFrame = new JFrame();
        JLabel backgroundLabel = new JLabel(new ImageIcon("./images/589298.jpg"));

        JLabel atmLabel, pleaseSelectTx;
        JButton balanceEnquiry, cashWithdraw, cashDeposit, pinChange, logout, miniStatement;
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        Font fontBold = new Font(Font.DIALOG, Font.BOLD, 48);

        // Declaration
        atmLabel = new JLabel("ATM System");
        pleaseSelectTx = new JLabel("Please Select Your Transaction");
        balanceEnquiry = new JButton("Balance Enquiry");
        cashWithdraw = new JButton("Cash Withdrawal");
        cashDeposit = new JButton("Cash Deposit");
        miniStatement = new JButton("Mini Statement");
        pinChange = new JButton("Pin Change");
        logout = new JButton("Logout");

        // Styling and positioning
        backgroundLabel.setBounds(0, 0, 1085, 700);
        atmLabel.setBounds(420, 30, 350, 100);
        pleaseSelectTx.setBounds(440, 80, 350, 100);
        balanceEnquiry.setBounds(20, 250, 350, 60);
        cashWithdraw.setBounds(20, 370, 350, 60);
        cashDeposit.setBounds(20, 490, 350, 60);
        miniStatement.setBounds(700, 250, 350, 60);
        pinChange.setBounds(700, 370, 350, 60);
        logout.setBounds(700, 490, 350, 60);

        // Font styling
        atmLabel.setFont(fontBold);
        pleaseSelectTx.setFont(font);
        pleaseSelectTx.setBackground(new Color(51, 51, 51));
        balanceEnquiry.setFont(font);
        cashWithdraw.setFont(font);
        cashDeposit.setFont(font);
        miniStatement.setFont(font);
        pinChange.setFont(font);
        logout.setFont(font);

        // Event Listeners
        balanceEnquiry.addActionListener(e -> showBalance());
        cashWithdraw.addActionListener(e -> handleCashWithdrawal());
        cashDeposit.addActionListener(e -> handleCashDeposit());
        miniStatement.addActionListener(e -> showMiniStatement());
        pinChange.addActionListener(e -> handleChangePin());
        logout.addActionListener(e -> handleLogout());

        // Adding components
        mainFrame.add(backgroundLabel);
        backgroundLabel.add(atmLabel);
        backgroundLabel.add(pleaseSelectTx);
        backgroundLabel.add(balanceEnquiry);
        backgroundLabel.add(cashWithdraw);
        backgroundLabel.add(cashDeposit);
        backgroundLabel.add(miniStatement);
        backgroundLabel.add(pinChange);
        backgroundLabel.add(logout);

        mainFrame.setSize(1085, 700);
        mainFrame.setResizable(true);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showBalance() {
        JOptionPane.showMessageDialog(null, "Your balance: " + currentUser.getBalance());
    }

    private void handleCashWithdrawal() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
        if (amountStr == null || amountStr.trim().isEmpty()) return;
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid amount.");
                return;
            }

            double previousBalance = currentUser.getBalance();
            currentUser.withdraw(amount);

            if (previousBalance != currentUser.getBalance()) {
                String transactionDesc = "Withdrawal: " + amount + ", New Balance: " + currentUser.getBalance();
                Transaction transaction = new Transaction("T1", transactionDesc, "Debit", amount);
                currentUser.addTransaction(transaction);
                JOptionPane.showMessageDialog(null, "Withdrawal successful. Your new balance: " + currentUser.getBalance());
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input.");
        }
    }

    private void handleCashDeposit() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
        if (amountStr == null || amountStr.trim().isEmpty()) return;
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid amount.");
                return;
            }

            double previousBalance = currentUser.getBalance();
            currentUser.deposit(amount);

            if (previousBalance != currentUser.getBalance()) {
                String transactionDesc = "Deposit: " + amount + ", New Balance: " + currentUser.getBalance();
                Transaction transaction = new Transaction("T2", transactionDesc, "Credit", amount);
                currentUser.addTransaction(transaction);
                JOptionPane.showMessageDialog(null, "Deposit successful. Your new balance: " + currentUser.getBalance());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input.");
        }
    }

    private void showMiniStatement() {
        List<Transaction> transactions = currentUser.getTransactions();

        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No transactions available.");
        } else {
            JFrame statementFrame = new JFrame("Mini Statement");
            statementFrame.setSize(600, 400);
            statementFrame.setLayout(new BorderLayout());

            String[] columnNames = {"Transaction ID", "Description", "Type", "Amount"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Transaction transaction : transactions) {
                String[] row = {transaction.getTransactionId(), transaction.getDescription(), transaction.getType(), String.valueOf(transaction.getAmount())};
                tableModel.addRow(row);
            }

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            statementFrame.add(scrollPane, BorderLayout.CENTER);

            statementFrame.setVisible(true);
        }
    }

    private void handleLogout() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Logout successful.");
            mainFrame.setVisible(false);

            SwingUtilities.invokeLater(() -> {
                new Login(currentUser).createLoginWindow();
            });
        }
    }

    private void handleChangePin() {
        String currentPin = JOptionPane.showInputDialog("Enter current PIN:");
        if (currentPin != null && currentPin.equals(currentUser.getPin())) {
            String newPin = JOptionPane.showInputDialog("Enter new PIN:");
            if (newPin != null && !newPin.isEmpty()) {
                currentUser.setPin(newPin);
                JOptionPane.showMessageDialog(null, "PIN changed successfully.");
                handleLogout();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid new PIN.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect current PIN.");
        }
    }

    public static void main(String args[]) {

    }
}
