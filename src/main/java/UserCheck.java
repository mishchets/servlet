import java.util.ArrayList;

public class UserCheck {
    public static boolean isUserCorrect(ArrayList<User> users, String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && (user.getPassword().equals(password))) {
                return true;
            }
        }
        return false;
    }
}
