package service.validate;

import org.springframework.stereotype.Component;

@Component
public class PasswordIValidator implements IValidator {
    @Override
    public void validate(String email, String password, String name) {
        System.err.println("validate password");
    }
}
