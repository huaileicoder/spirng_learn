package service.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidatorService {
    @Autowired
    List<IValidator> IValidators;

    public void validate(String email, String password, String name) {
        for (IValidator IValidator : IValidators) {
            IValidator.validate(email, password, name);
        }
    }
}
