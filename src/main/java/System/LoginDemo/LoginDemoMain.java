package System.LoginDemo;

import State.StringCommand;

public class LoginDemoMain  {

    public static void main(String[] args) {
        Sistema.systemSetUp();
        new LoginComponent<StringCommand>(Sistema.getStore());
    }

}
