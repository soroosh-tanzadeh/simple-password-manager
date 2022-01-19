package main;

import main.service.AuthenticationService;
import main.service.ServiceManager;
import main.windows.MainWindow;
import main.windows.auth.LoginWindow;
import main.windows.auth.SignupWindow;

public class Main {

    public static void main(String[] args) {
        Thread applicationThread = new Thread(() -> {
            try {
                AuthenticationService service = ServiceManager.getService(AuthenticationService.class);
                if (service.anyUserExists()) {
                    if (AuthenticationService.currentUser != null) {
                        MainWindow mainWindow = new MainWindow();
                        mainWindow.setVisible(true);
                    } else {
                        LoginWindow frame = new LoginWindow();
                        frame.setVisible(true);
                    }
                } else {
                    SignupWindow frame = new SignupWindow();
                    frame.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        applicationThread.start();
    }

}
