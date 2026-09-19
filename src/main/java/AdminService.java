public class AdminService {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public boolean login(String username, String password) {

        return ADMIN_USERNAME.equals(username)
                && ADMIN_PASSWORD.equals(password);
    }
}
