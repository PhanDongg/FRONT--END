package mock.project.frontend.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import mock.project.frontend.entities.Users;
import mock.project.frontend.services.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Users.class.equals(aClass);
    }


	@Override
	public void validate(Object target, Errors errors) {
		Users users = (Users) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (users.getUserName().length() < 6 || users.getUserName().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        
//        if (userService.findByUserName(users.getUserName()) != null) {
//            errors.rejectValue("username", "Duplicate.userForm.username");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (users.getPassword().length() < 8 || users.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

//        if (!users.getPasswordConfirm().equals(users.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }
	}
}