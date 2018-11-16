package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class LoginService {
    public static Object login;
    public static LoginService[] logins;
    private String username;
    private String password;
    private String sessionToken;

    // Get IntelliJ to auto-generate a constructor, getter and setters here:

    public LoginService(String username, String password, String sessionToken) {
        this.username = username;
        this.password = password;
        this.sessionToken = sessionToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static ArrayList<LoginService> admins = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("username", getUsername());
        j.put("password", getPassword());
        j.put("sessionToken", getSessionToken());






        return j;
    }
}
