package ge.bog.Shirts_Store.controller;

import ge.bog.Shirts_Store.dto.PurchaseDto;
import ge.bog.Shirts_Store.entities.PasoShopPurchase;
import ge.bog.Shirts_Store.entities.PasoShopUser;
import ge.bog.Shirts_Store.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping("/makePurchase/{userName}/{password}")
    public ResponseEntity<PurchaseDto> makePurchase(@PathVariable String userName, @PathVariable String password
                                                          , @Valid @RequestBody PurchaseDto input, BindingResult b) {
        PurchaseDto purchase = purchaseService.makePurchase(userName, input);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    @GetMapping("/getPurchases/{userName}/{password}")
    public ResponseEntity<List<PasoShopPurchase>> getPurchases(@PathVariable String userName, @PathVariable String password) {
        List<PasoShopPurchase> res = purchaseService.getUserPurchases(userName);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
