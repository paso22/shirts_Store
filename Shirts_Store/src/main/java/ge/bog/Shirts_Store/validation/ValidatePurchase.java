package ge.bog.Shirts_Store.validation;

import ge.bog.Shirts_Store.dto.PurchaseDto;
import ge.bog.Shirts_Store.entities.PasoShopUser;
import ge.bog.Shirts_Store.repository.PasoShopUserRepository;
import ge.bog.Shirts_Store.exception.ErrorEnum;
import ge.bog.Shirts_Store.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
@Slf4j
public class ValidatePurchase {

    private final PasoShopUserRepository paso_shop_user_repository;

    public ValidatePurchase(PasoShopUserRepository paso_shop_user_repository) {
        this.paso_shop_user_repository = paso_shop_user_repository;
    }

    @Before(value = "execution(* ge.bog.Shirts_Store.controller.PurchaseController.makePurchase(..)) && args(userName,password,purchase,b))", argNames = "userName,password,purchase,b")
    public void validateMakePurchase(String userName, String password, PurchaseDto purchase, BindingResult b) {
        logInValidation(userName,password);
        notNullChecker(b);
    }

    @Before(value = "execution(* ge.bog.Shirts_Store.controller.PurchaseController.getPurchases(..)) && args(userName,password))", argNames = "userName,password")
    private void logInValidation(String userName, String password) {
        if(userName == null) {
            throw new GeneralException(ErrorEnum.USERNAME_NULL);
        }
        if(password == null) {
            throw new GeneralException(ErrorEnum.PASSWORD_NULL);
        }
        PasoShopUser user = paso_shop_user_repository.findByUsername(userName).orElse(null);
        if(user == null) {
            throw  new GeneralException(ErrorEnum.USERNAME_DOES_NOT_EXISTS);
        }
        if(!user.getPassword().equals(password)) {
            throw new GeneralException(ErrorEnum.PASSWORD_NOT_CORRECT);
        }
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
