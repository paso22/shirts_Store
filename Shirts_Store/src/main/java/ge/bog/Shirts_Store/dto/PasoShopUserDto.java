package ge.bog.Shirts_Store.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasoShopUserDto {

    private String username;

    private String email;

    private String birth_date;

    private String gender;

    private String favorite_team;

    private String location;

}
