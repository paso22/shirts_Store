package ge.bog.Shirts_Store.service;

import ge.bog.Shirts_Store.dto.PasoShopUserDto;
import ge.bog.Shirts_Store.entities.PasoShopUser;
import ge.bog.Shirts_Store.repository.PasoShopUserRepository;
import org.springframework.stereotype.Service;

@Service
public class PasoShopUserServiceImpl implements PasoShopUserService {

    private final PasoShopUserRepository paso_shop_user_repository;

    public PasoShopUserServiceImpl(PasoShopUserRepository paso_shop_user_repository) {
        this.paso_shop_user_repository = paso_shop_user_repository;
    }

    @Override
    public PasoShopUserDto register(PasoShopUser registerForm) {
        paso_shop_user_repository.save(registerForm);
        return getDtoFromUser(registerForm);
    }

    @Override
    public PasoShopUserDto update(String userName, String password, PasoShopUser updateForm) {
        PasoShopUser user = paso_shop_user_repository.findByUsername(userName).get();
        fillProfile(user, updateForm);
        paso_shop_user_repository.save(user);
        return getDtoFromUser(user);
    }

    @Override
    public PasoShopUser getInfo(String userName, String password) {
        PasoShopUser user = paso_shop_user_repository.findByUsername(userName).get();
        return user;
    }

    @Override
    public boolean deleteUser(String userName, String password) {
        PasoShopUser user = paso_shop_user_repository.findByUsername(userName).get();
        paso_shop_user_repository.delete(user);
        return true;
    }

    @Override
    public boolean getAdminInfo(String userName, String password) {
        return paso_shop_user_repository.findByUsername(userName).get().getAdmin().equalsIgnoreCase("Y");
    }

    private void fillProfile(PasoShopUser user, PasoShopUser updateForm) {
        user.setUsername(updateForm.getUsername());
        user.setPassword(updateForm.getPassword());
        user.setGender(updateForm.getGender());
        user.setLocation(updateForm.getLocation());
        user.setEmail(updateForm.getEmail());
        user.setFavorite_team(updateForm.getFavorite_team());
        user.setBirth_date(updateForm.getBirth_date());
        user.setWallet(updateForm.getWallet());
    }

    private PasoShopUserDto getDtoFromUser(PasoShopUser paso_shop_user) {
        return PasoShopUserDto.builder()
                .username(paso_shop_user.getUsername())
                .email(paso_shop_user.getEmail())
                .birth_date(paso_shop_user.getBirth_date())
                .gender(paso_shop_user.getGender())
                .favorite_team(paso_shop_user.getFavorite_team())
                .location(paso_shop_user.getLocation())
                .build();
    }

}
