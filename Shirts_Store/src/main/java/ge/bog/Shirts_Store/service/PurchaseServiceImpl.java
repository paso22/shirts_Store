package ge.bog.Shirts_Store.service;

import ge.bog.Shirts_Store.dto.PurchaseDto;
import ge.bog.Shirts_Store.dto.PurchaseNumericDataDto;
import ge.bog.Shirts_Store.entities.PasoShopPurchase;
import ge.bog.Shirts_Store.entities.PasoShopUser;
import ge.bog.Shirts_Store.repository.PasoShopUserRepository;
import ge.bog.Shirts_Store.repository.PurchaseRepository;
import ge.bog.Shirts_Store.exception.ErrorEnum;
import ge.bog.Shirts_Store.exception.GeneralException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Value("http://localhost:8081")
    private String secondUrl;

    private final PurchaseRepository purchaseRepository;
    private final PasoShopUserRepository paso_shop_user_repository;
    private final RestTemplate restTemplate;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PasoShopUserRepository paso_shop_user_repository, RestTemplate restTemplate) {
        this.purchaseRepository = purchaseRepository;
        this.paso_shop_user_repository = paso_shop_user_repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public PurchaseDto makePurchase(String userName, PurchaseDto input) {
        convertToUpperCase(input);
        sufficientChecker(userName, input);
        return input;
    }

    @Override
    public List<PasoShopPurchase> getUserPurchases(String userName) {
        List<PasoShopPurchase> purchases = purchaseRepository.findByUserName(userName).orElse(null);
        if(purchases == null || purchases.size() == 0) {
            throw new GeneralException(ErrorEnum.PURCHASES_NOT_EXISTS);
        }
        return purchases;
    }

    private void sufficientChecker(String userName, PurchaseDto input) {
        PasoShopUser user = paso_shop_user_repository.findByUsername(userName).orElse(null);
        PurchaseNumericDataDto numericData = stopIfError(input, user.getWallet());
        PasoShopPurchase purchase = PasoShopPurchase.toEntity(numericData, input, userName);
        purchaseRepository.save(purchase);
        user.setWallet(user.getWallet() - numericData.getTotalCost());
        paso_shop_user_repository.save(user);
    }

    private PurchaseNumericDataDto stopIfError(PurchaseDto input, Integer wallet) {
        ResponseEntity<PurchaseNumericDataDto> responseEntity;
            try {
                String url = String.format("%s/Purchase/get/numericData/%s/%s/%s/%s/%s/%s/%s", secondUrl, input.getTeam(), input.getType(), input.getIsHome(), input.getSeason()
                        ,input.getHasNameNumber(), input.getQuantity(), wallet);
                responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<PurchaseNumericDataDto>() {});
            } catch(HttpClientErrorException e) {
                throw new GeneralException(ErrorEnum.CALLING_REST_API_ERROR);
            }
            if(responseEntity.getBody().getE() != null) {
                throw new GeneralException(responseEntity.getBody().getE().getErrorEnum());
            }
            return responseEntity.getBody();
    }

    private void convertToUpperCase(PurchaseDto input) {
        input.setTeam(input.getTeam().toUpperCase());
        input.setType(input.getType().toUpperCase());
        input.setSeason(input.getSeason().toUpperCase());
        input.setIsHome(input.getIsHome().toUpperCase());
        input.setHasNameNumber(input.getHasNameNumber().toUpperCase());
    }
}

