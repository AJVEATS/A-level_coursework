package server.models;
import org.json.simple.JSONObject;
import java.util.ArrayList;
public class User {
    private int userId;
    private String userName;
    private String password;
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
        j.put("password", getPassword());
        return j;
    }
    public void setSessionToken(String token) {
    }
    public String getUsername() {
        return null;
    }
    public Object getSessionToken() {
        return null;
    }
}
