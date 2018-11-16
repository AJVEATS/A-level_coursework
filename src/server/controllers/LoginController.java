package server.controllers;
import server.Logger;
import server.models.Login;
import server.models.services.LoginService;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("login/")
public class LoginController {

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String attemptLogin(@FormParam("username") String username,
                               @FormParam("password") String password ) {

        Logger.log("/user/login - Attempt by " + username);
        LoginService.selectAllInto(Login.logins);
        for (Login a: Login.logins) {
            if (a.getUsername().toLowerCase().equals(username.toLowerCase())) {
                if (!a.getPassword().equals(password)) {
                    return "Error: Incorrect password";
                }
                String token = UUID.randomUUID().toString();
                a.setSessionToken(token);
                String success = LoginService.update(a);
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

        Logger.log("/login/check - Checking user against database");

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
            String result = LoginService.selectAllInto(Login.logins);
            if (result.equals("OK")) {
                for (Login a : Login.logins) {
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

