package main.windows;

import main.components.PasswordStoreRenderer;
import main.records.secure.PasswordStore;
import main.service.AuthenticationService;
import main.service.ServiceManager;
import main.service.dataAccess.UserDataAccessService;
import main.windows.auth.SignupWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainWindow extends BaseWindow {

    private JPanel topPanel;
    private JTextField searchField;

    private JList<PasswordStore> passwordStores;

    private UserDataAccessService userDataAccessService;

    public MainWindow() {
        super(new BorderLayout());
        setSize(400, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("مدیریت رمزعبور");
        try {
            this.userDataAccessService = ServiceManager.getService(UserDataAccessService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setupMenu();
        this.setupComponents();
    }

    public void loadPasswords() {
        DefaultListModel<PasswordStore> passwordStoreListModel = new DefaultListModel<>();
        passwordStoreListModel.addAll(this.userDataAccessService.getPasswords());
        this.passwordStores.setModel(passwordStoreListModel);
    }

    private void setupComponents() {
        this.topPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 8, 8));
        this.searchField = new JTextField();
        this.searchField.setPreferredSize(new Dimension(300, 45));
        this.searchField.addCaretListener(e -> {
            String searchText = this.searchField.getText();
            if (!searchText.isEmpty()) {
                List<PasswordStore> passwordStores = this.userDataAccessService.getPasswords().stream()
                        .filter(passwordStore -> passwordStore.getTitle().contains(searchText) || passwordStore.getUsername().contains(searchText) || passwordStore.getWebsite().contains(searchText))
                        .toList();
                DefaultListModel<PasswordStore> passwordStoreListModel = new DefaultListModel<>();
                passwordStoreListModel.addAll(passwordStores);
                this.passwordStores.setModel(passwordStoreListModel);
            } else {
                this.loadPasswords();
            }
        });
        JButton btnDelete = new JButton("حذف");

        // Remove Password Action
        btnDelete.addActionListener(e -> {
            List<PasswordStore> selectedValuesList = this.passwordStores.getSelectedValuesList();
            for (PasswordStore store : selectedValuesList) {
                this.userDataAccessService.removePassword(store.getId());
            }
            this.loadPasswords();
        });
        topPanel.setMaximumSize(new Dimension(400, 110));
        topPanel.setBorder(new EmptyBorder(4, 4, 16, 4));
        this.topPanel.add(btnDelete);
        this.topPanel.add(searchField);

        this.mainPanel.add(this.topPanel, BorderLayout.PAGE_START);

        this.passwordStores = new JList<>();
        this.passwordStores.setBackground(Color.white);
        this.passwordStores.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    PasswordStore value = passwordStores.getSelectedValue();
                    ShowPasswordInfo passwordInfo = new ShowPasswordInfo(MainWindow.this,value);
                    passwordInfo.setVisible(true);
                }
            }
        });
        DefaultListModel<PasswordStore> passwordStoreListModel = new DefaultListModel<>();
        passwordStoreListModel.addAll(AuthenticationService.currentUser.getPasswordStores());
        this.passwordStores.setCellRenderer(new PasswordStoreRenderer(this));
        this.passwordStores.setModel(passwordStoreListModel);
        this.mainPanel.add(this.passwordStores, BorderLayout.CENTER);
    }

    /**
     * Setup MenuBar
     */
    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu usersMenu = new JMenu("کاربران");
        JMenuItem createUserItem = new JMenuItem("ایجاد کاربر");
        JMenuItem logoutItem = new JMenuItem("خروج");
        createUserItem.addActionListener(e -> {
            SignupWindow frame = new SignupWindow(false);
            frame.setVisible(true);
        });
        logoutItem.addActionListener(e -> {
            try {
                AuthenticationService service = ServiceManager.getService(AuthenticationService.class);
                service.logout();
                System.exit(0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        usersMenu.add(createUserItem);
        usersMenu.add(logoutItem);

        JMenu passwordsMenu = new JMenu("رمزعبور");
        JMenuItem createPasswordItem = new JMenuItem("رمزعبور  جدید");

        createPasswordItem.addActionListener(e -> {
            CreatePasswordDialog dialog = new CreatePasswordDialog(this);
            dialog.addActionListener(action -> {
                dialog.setVisible(false);
                this.loadPasswords();
            });
            dialog.setVisible(true);
        });

        passwordsMenu.add(createPasswordItem);


        menuBar.add(usersMenu);
        menuBar.add(passwordsMenu);
        setJMenuBar(menuBar);
    }

}
