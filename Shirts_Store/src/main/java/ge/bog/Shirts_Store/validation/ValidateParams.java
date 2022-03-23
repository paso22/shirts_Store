package ge.bog.Shirts_Store.validation;

import ge.bog.Shirts_Store.entities.PasoShopUser;
import ge.bog.Shirts_Store.repository.PasoShopUserRepository;
import ge.bog.Shirts_Store.exception.ErrorEnum;
import ge.bog.Shirts_Store.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class ValidateParams {

    private static final int MIN_DATE = 1900;
    private static final int MAX_DATE = 2004;

    private final PasoShopUserRepository paso_shop_user_repository;

    public ValidateParams(PasoShopUserRepository paso_shop_user_repository) {
        this.paso_shop_user_repository = paso_shop_user_repository;
    }

    @Before(value = "(execution(* ge.bog.Shirts_Store.controller.ShirtsController.register(..)) && args(paso_shop_user,b))", argNames = "paso_shop_user,b")
    public void validateRegisterInfo(PasoShopUser paso_shop_user, BindingResult b) {
        notNullChecker(b);
        registrationChecker(paso_shop_user);
    }

    @Before(value = "execution(* ge.bog.Shirts_Store.controller.ShirtsController.update(..)) && args(userName,password,updateForm,b))", argNames = "userName,password,updateForm,b")
      public void validateLogInForUpdate(String userName, String password, PasoShopUser updateForm, BindingResult b) {
        logInValidation(userName, password);
        notNullChecker(b);
        updateChecker(userName,password,updateForm);
    }

    @Before(value = "execution(* ge.bog.Shirts_Store.service.PasoShopUserServiceImpl.getInfo(..)) && args(userName,password))", argNames = "userName,password")
    public void validateLogInForGet(String userName, String password) {
        logInValidation(userName, password);
    }

    @Before(value = "execution(* ge.bog.Shirts_Store.service.PasoShopUserServiceImpl.deleteUser(..)) && args(userName,password))", argNames = "userName,password")
    public void validateLogInForDelete(String userName, String password) {
        logInValidation(userName, password);
    }

    private void updateChecker(String userName, String password, PasoShopUser updatedUser) {
        PasoShopUser user = paso_shop_user_repository.findByUsername(userName).get();
        if(paso_shop_user_repository.findByUsername(updatedUser.getUsername()).isPresent()
                && !updatedUser.getUsername().equals(userName)) {
            throw new GeneralException(ErrorEnum.USERNAME_ALREADY_EXISTS);
        }
        if(paso_shop_user_repository.findByPassword(updatedUser.getPassword()).isPresent()
                && !updatedUser.getPassword().equals(password)) {
            throw new GeneralException(ErrorEnum.PASSWORD_ALREADY_USED);
        }
        if(paso_shop_user_repository.findByEmail(updatedUser.getEmail()).isPresent()
                && !updatedUser.getEmail().equals(user.getEmail())) {
            throw new GeneralException(ErrorEnum.EMAIL_ALREADY_EXISTS);
        }
        checkMailAndGenderAndDate(updatedUser);
    }

    private void checkMailAndGenderAndDate(PasoShopUser user) {
        String gender = user.getGender();
        String email = user.getEmail();
        String birthDate = user.getBirth_date();
        String admin = user.getAdmin();

        if(!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
            throw  new GeneralException(ErrorEnum.INVALID_GENDER);
        }
        if(!admin.equalsIgnoreCase("Y") && !admin.equalsIgnoreCase("N")) {
            throw  new GeneralException(ErrorEnum.INVALID_ADMIN);
        }
        if(!EmailValidator.getInstance().isValid(email)) {
            throw new GeneralException(ErrorEnum.INVALID_EMAIL);
        }
        int userBirthDate = Integer.parseInt(birthDate.substring(birthDate.length() - 4));
        if(!GenericValidator.isDate(birthDate, "dd/MM/yyyy", true)
                || userBirthDate < MIN_DATE || userBirthDate > MAX_DATE) {
            throw new GeneralException(ErrorEnum.INVALID_DATE_FORMAT);
        }
        // ლამაზად და ერთ სტილში რომ იყოს მეტნაკლებად ცხრილში ჩანაწერები :)
        user.setGender(gender.toUpperCase());
        user.setEmail(email.toLowerCase());
        user.setFavorite_team(user.getFavorite_team().toUpperCase());
        user.setLocation(user.getLocation().toUpperCase());
        user.setAdmin(admin.toUpperCase());
    }

    @Before(value = "execution(* ge.bog.Shirts_Store.service.PasoShopUserServiceImpl.getAdminInfo(..)) && args(userName,password))", argNames = "userName,password")
    private void logInValidation(String userName, String password) {
        if(userName == null) {
            throw new GeneralException(ErrorEnum.USERNAME_NULL);
        }
        if(password == null) {
            throw new GeneralException(ErrorEnum.PASSWORD_NULL);
        }
        Optional<PasoShopUser> user = paso_shop_user_repository.findByUsername(userName);
        if(!user.isPresent()) {
            throw new GeneralException(ErrorEnum.USERNAME_DOES_NOT_EXISTS);
        }
        if(!password.equals(user.get().getPassword())) {
            throw new GeneralException(ErrorEnum.PASSWORD_NOT_CORRECT);
        }
    }

    private void registrationChecker(PasoShopUser paso_shop_user) {
        String userName = paso_shop_user.getUsername();
        String password = paso_shop_user.getPassword();
        String email = paso_shop_user.getEmail();

        if(paso_shop_user_repository.findByUsername(userName).isPresent()) {
            throw new GeneralException(ErrorEnum.USERNAME_ALREADY_EXISTS);
        }
        if(paso_shop_user_repository.findByPassword(password).isPresent()) {
            throw new GeneralException(ErrorEnum.PASSWORD_ALREADY_USED);
        }
        if(paso_shop_user_repository.findByEmail(email).isPresent()) {
            throw new GeneralException(ErrorEnum.EMAIL_ALREADY_EXISTS);
        }
        checkMailAndGenderAndDate(paso_shop_user);

    }

    private void notNullChecker(BindingResult b) {
        if(b.hasErrors()) {
            String resError;
            for(Object o : b.getAllErrors()) {
                if(o instanceof FieldError) {
                    FieldError fe = (FieldError) o;
                    resError = fe.getDefaultMessage();
                    throw new GeneralException(fe.getField() + " - " + resError);
                }
            }
        }
    }

}
