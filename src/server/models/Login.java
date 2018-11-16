package server.models;
import org.json.simple.JSONObject;
import server.DatabaseConnection;
import server.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Login {

    private String username;
    private String password;
    private String sessionToken;

    // Get IntelliJ to auto-generate a constructor, getter and setters here:

    public Login(String username, String password, String sessionToken) {
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

    public static ArrayList<Login> logins = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("username", getUsername());
        j.put("password", getPassword());
        j.put("sessionToken", getSessionToken());






        return j;
    }
}
