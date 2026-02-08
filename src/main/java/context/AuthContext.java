package context;

public class AuthContext {

    private static String token;

    public static void setToken(String token) {
        AuthContext.token = token;
    }

    public static String getToken() {
        return token;
    }
}
