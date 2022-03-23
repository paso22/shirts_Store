package ge.bog.Shirts_Store.entities;

import ge.bog.Shirts_Store.dto.PurchaseDto;
import ge.bog.Shirts_Store.dto.PurchaseNumericDataDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PASO_SHOP_PURCHASES")
@Getter
@Setter
public class PasoShopPurchase {

    @Id
    @SequenceGenerator(name = "PASO_SHOP_PURCHASES_SEQ", sequenceName = "PASO_SHOP_PURCHASES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASO_SHOP_PURCHASES_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "TEAM")
    private String team;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "IS_HOME")
    private String isHome;

    @Column(name = "SEASON")
    private String season;

    @Column(name = "HAS_NAME_NUMBER")
    private String nameNumber;

    @Column(name = "SHIRT_PRICE")
    private Integer shirtPrice;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL_COST")
    private Integer totalCost;


    public static PasoShopPurchase toEntity(PurchaseNumericDataDto numericData, PurchaseDto purchaseInfo, String userName) {
        PasoShopPurchase res = new PasoShopPurchase();
        res.setUserName(userName);
        res.setTeam(purchaseInfo.getTeam());
        res.setType(purchaseInfo.getType());
        res.setIsHome(purchaseInfo.getIsHome());
        res.setSeason(purchaseInfo.getSeason());
        res.setNameNumber(purchaseInfo.getHasNameNumber());
        res.setShirtPrice(numericData.getShirtPrice());
        res.setQuantity(purchaseInfo.getQuantity());
        res.setTotalCost(numericData.getTotalCost());
        return res;
    }
}



