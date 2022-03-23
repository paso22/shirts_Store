package ge.bog.Shirts_Store.repository;

import ge.bog.Shirts_Store.entities.PasoShopPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<PasoShopPurchase,Integer> {

    Optional<List<PasoShopPurchase>> findByUserName(String userName);

}
