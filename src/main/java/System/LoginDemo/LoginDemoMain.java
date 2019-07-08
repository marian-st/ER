package System.LoginDemo;

import State.MyString;

public class LoginDemoMain  {

    public static void main(String[] args) {
        Sistema.systemSetUp();
        new LoginComponent<MyString>(Sistema.getStore());
    }

}
