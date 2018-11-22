package server.models;
import org.json.simple.JSONObject;
import java.util.ArrayList;
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String sessionToken;
    public User(int userId, String userName, String userPassword, int sessionToken) {
    }
    public User(int userId, String username, String password1, String token) {
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public int getSessionToken() {
        return Integer.parseInt(sessionToken);
    }
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
    public static ArrayList<User> users = new ArrayList<>();
    public static int nextId() {
        int id = 0;
        for (User u: users) {
            if (u.getUserId() > id) {
                id = u.getUserId();
            }
        }
        return id + 1;
    }
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("userId", getUserId());
        j.put("userName", getUserName());
        j.put("userPassword", getUserPassword());
        j.put("sessionToken", getSessionToken());
        return j;
    }
}
