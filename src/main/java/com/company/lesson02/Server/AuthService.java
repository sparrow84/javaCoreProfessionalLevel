package com.company.lesson02.Server;

public interface AuthService {
    String authByLoginAndPassword(String login, String password);

    User createOrActivateUser(String login, String password, String nick);

    boolean deactivateUser(String nick);
}





//public class AuthService {
//    public String getNickByLoginAndPass(String login, String pass) {
//
//        return "";
//    }
//}
