package ge.bog.Shirts_Store.service;

import ge.bog.Shirts_Store.dto.PasoShopUserDto;
import ge.bog.Shirts_Store.entities.PasoShopUser;

public interface PasoShopUserService {

    PasoShopUserDto register(PasoShopUser registerForm);
    PasoShopUserDto update(String userName, String password, PasoShopUser updateForm);
    PasoShopUser getInfo(String userName, String password);
    boolean deleteUser(String userName, String password);
    boolean getAdminInfo(String userName, String password);

}
