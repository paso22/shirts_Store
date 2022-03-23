package ge.bog.Shirts_Store.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class PurchaseDto {

    @NotNull(message = "გუნდის ველი ცარიელია")
    private String team;
    @NotNull(message = "ტიპის ველი ცარიელია")
    private String type;
    @NotNull(message = "isHome ველი ცარიელია")
    @Size(min = 1, max = 1, message = "isHome - უნდა შეიცავდეს 1 სიმბოლოს")
    private String isHome;
    @NotNull(message = "სეზონის ველი ცარიელია")
    private String season;
    @NotNull(message = "nameNumber ველი ცარიელია")
    @Size(min = 1, max = 1, message = "nameNumber - უნდა შეიცავდეს 1 სიმბოლოს")
    private String hasNameNumber;
    @NotNull(message = "რაოდენობის ველი ცარიელია")
    @Min(value = 1, message = "quantity - მინიმუმ 1 უნდა იყოს")
    private Integer quantity;

}
