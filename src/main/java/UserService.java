import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {
    private MailService mailService;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    private final List<User> users = new ArrayList<>(Arrays.asList(
            new User(1, "bob@example.com", "Bob", "password"),
            new User(2, "alice@example.com", "Alice", "password"),
            new User(3, "tom@example.com",  "Tom", "password")));

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed!");
    }

    public User getUser(int id) {
        return this.users.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    public User register(String email, String password, String name) {
        users.forEach((user -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("existed");
            }
        }));

        User user = new User(users.stream().mapToInt(User::getUserId).max().getAsInt(), email, name, password);
        users.add(user);
        mailService.sendRegistrationMail(user);
        return user;
    }

}
