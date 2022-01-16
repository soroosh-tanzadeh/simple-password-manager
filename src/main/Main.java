package main;

import main.service.AuthenticationService;
import main.service.ServiceManager;
import main.windows.LoginWindow;
import main.windows.SignupWindow;

public class Main {

    public static void main(String[] args) {
        Thread applicationThread = new Thread(() -> {
            try {
                AuthenticationService service = ServiceManager.getService(AuthenticationService.class);
                if(service.anyUserExists()){
                    LoginWindow frame = new LoginWindow();
                    frame.setVisible(true);
                }else{
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
