package ge.bog.Shirts_Store.repository;

import ge.bog.Shirts_Store.entities.PasoShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasoShopUserRepository extends JpaRepository<PasoShopUser, Integer> {

    Optional<PasoShopUser> findByUsername(String userName);
    Optional<PasoShopUser> findByPassword(String password);
    Optional<PasoShopUser> findByEmail(String email);

}
