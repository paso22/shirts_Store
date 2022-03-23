package ge.bog.Shirts_Store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PASO_SHOP_USERS")
@Getter
@Setter
public class PasoShopUser {

    @Id
    @SequenceGenerator(name = "PASO_SHOP_USERS_SEQ", sequenceName = "PASO_SHOP_USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "PASO_SHOP_USERS_SEQ")
    @Column(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    @NotNull(message = "იუზერის ველი არ უნდა იყოს ცარიელი!")
    @Size(min = 6, message = "იუზერის სახელი უნდა შედგებოდეს მინიმუმ 6 სიმბოლოსგან!")
    private String username;

    @Column(name = "PASSWORD")
    @NotNull(message = "პაროლის ველი არ უნდა იყოს ცარიელი!")
    @Size(min = 7, message =  "პაროლი უნდა შედგებოდეს მინიმუმ 7 სიმბოლოსგან!")
    private String password;

    @Column(name = "EMAIL")
    @NotNull(message = "მეილის ველი არ უნდა იყოს ცარიელი!")
    private String email;

    @Column(name = "BIRTH_DATE")
    @NotNull(message = "დაბადების თარიღის ველი არ უნდა იყოს ცარიელი!")
    private String birth_date;

    @Column(name = "GENDER")
    @NotNull(message = "გენდერის ველი არ უნდა იყოს ცარიელი!")
    private String gender;

    @Column(name = "FAVORITE_TEAM")
    @NotNull(message = "საყვარელი გუნდის ველი არ უნდა იყოს ცარიელი!")
    private String favorite_team;

    @Column(name = "LOCATION")
    @NotNull(message = "ლოკაციის ველი არ უნდა იყოს ცარიელი!")
    private String location;

    @Column(name = "WALLET", nullable = false)
    @NotNull(message = "ანგარიშის ველი არ უნდა იყოს ცარიელი!")
    @Min(value = 1, message = "თანხა არ შეიძლება იყოს უარყოფითი რიცხვი")
    private Integer wallet;

    @Column(name = "ADMIN")
    @NotNull
    @Size(min = 1, max = 1)
    private String admin;

}
