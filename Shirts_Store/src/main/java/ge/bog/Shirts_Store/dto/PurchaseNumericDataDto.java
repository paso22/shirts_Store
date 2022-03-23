package ge.bog.Shirts_Store.dto;

import ge.bog.Shirts_Store.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseNumericDataDto {

     private Integer shirtPrice;
     private Integer totalCost;
     private GeneralException e;
}
