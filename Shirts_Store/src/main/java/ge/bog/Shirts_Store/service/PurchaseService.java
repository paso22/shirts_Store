package ge.bog.Shirts_Store.service;

import ge.bog.Shirts_Store.dto.PurchaseDto;
import ge.bog.Shirts_Store.entities.PasoShopPurchase;

import java.util.List;

public interface PurchaseService {

    PurchaseDto makePurchase(String userName, PurchaseDto input);
    List<PasoShopPurchase> getUserPurchases(String userName);
}
