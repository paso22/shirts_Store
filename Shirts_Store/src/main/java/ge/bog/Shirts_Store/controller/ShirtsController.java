package ge.bog.Shirts_Store.controller;

import ge.bog.Shirts_Store.dto.PasoShopUserDto;
import ge.bog.Shirts_Store.entities.PasoShopUser;
import ge.bog.Shirts_Store.service.PasoShopUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/PasoShop")
public class ShirtsController {

    private final PasoShopUserService paso_shop_user_service;

    public ShirtsController(PasoShopUserService paso_shop_user_service) {
        this.paso_shop_user_service = paso_shop_user_service;
    }

    @GetMapping("/get/{userName}/{password}")
    public ResponseEntity<PasoShopUser> getProfileInfo(@PathVariable String userName, @PathVariable String password) {
        PasoShopUser res = paso_shop_user_service.getInfo(userName, password);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<PasoShopUserDto> register(@Valid @RequestBody PasoShopUser registerInfo, BindingResult b) {
        PasoShopUserDto res = paso_shop_user_service.register(registerInfo);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userName}/{password}")
    public ResponseEntity<PasoShopUserDto> update(@PathVariable String userName, @PathVariable String password,
                                                  @Valid @RequestBody PasoShopUser updateInfo, BindingResult b) {
        PasoShopUserDto res = paso_shop_user_service.update(userName, password, updateInfo);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userName}/{password}")
    public ResponseEntity<String> delete(@PathVariable String userName, @PathVariable String password) {
        boolean res = paso_shop_user_service.deleteUser(userName, password);
        return new ResponseEntity<>(userName + " deleted!", HttpStatus.OK);
    }

    @GetMapping("/get/isAdmin/{userName}/{password}")
    public ResponseEntity<Boolean> getAdmin(@PathVariable String userName, @PathVariable String password) {
        boolean res = paso_shop_user_service.getAdminInfo(userName,password);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
