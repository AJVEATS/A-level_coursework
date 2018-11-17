package server.controllers;
import server.Logger;
import server.models.User;
import server.models.services.UserService;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.util.UUID;
@Path("login/")
public class UserController {
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String attemptLogin(@FormParam("username") String username,
                               @FormParam("password") String password ) {
        Logger.log("/user/users - Attempt by " + username);
        UserService.selectAllInto(User.users);
        for (User a: User.users) {
            if (a.getUsername().toLowerCase().equals(username.toLowerCase())) {
                if (!a.getPassword().equals(password)) {
                    return "Error: Incorrect password";
                }
                String token = UUID.randomUUID().toString();
                a.setSessionToken(token);
                String success = UserService.update(a);
                if (success.equals("OK")) {
                    return token;
                } else {
                    return "Error: Can't create session token.";
                }
            }
        }
        return "Error: Can't find user account.";
    }
    @GET
    @Path("check")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkLogin(@CookieParam("sessionToken") Cookie sessionCookie) {
        Logger.log("/User/check - Checking user against database");
        String currentUser = validateSessionCookie(sessionCookie);

        if (currentUser == null) {
            Logger.log("Error: Invalid user session token");
            return "";
        } else {
            return currentUser;
        }
    }
    public static String validateSessionCookie(Cookie sessionCookie) {
        if (sessionCookie != null) {
            String token = sessionCookie.getValue();
            String result = UserService.selectAllInto(User.users);
            if (result.equals("OK")) {
                for (User a : User.users) {
                    if (a.getSessionToken().equals(token)) {
                        Logger.log("Valid session token received.");
                        return a.getUsername();
                    }
                }
            }
        }
        return null;
    }

}

