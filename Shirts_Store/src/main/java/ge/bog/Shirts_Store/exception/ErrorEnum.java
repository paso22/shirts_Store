package ge.bog.Shirts_Store.exception;


import org.springframework.http.HttpStatus;

public enum ErrorEnum {

    USERNAME_ALREADY_EXISTS("მოცემული იუზერნეიმი უკვე არსებობს!", HttpStatus.BAD_REQUEST),
    PASSWORD_ALREADY_USED("პაროლი უკვე გამოყენებულია", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("ასეთი მეილი უკვე არსებობს", HttpStatus.BAD_REQUEST),
    INVALID_GENDER("გენდერი არასწორია! გთხოვთ ჩაწეროთ ან M, ან F", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL("მეილი არასწორია!", HttpStatus.BAD_REQUEST),
    NEGATIVE_WALLET("თანხა არ შეიძლება უარყოფითი იყოს!", HttpStatus.BAD_REQUEST),
    USERNAME_DOES_NOT_EXISTS("იუზერის სახელი არასწორია!", HttpStatus.NOT_FOUND),
    PASSWORD_NOT_CORRECT("პაროლი არასწორია", HttpStatus.NOT_FOUND),
    INVALID_DATE_FORMAT("დაბადების თარიღი(DD/MM/YYYY) არასწორია!", HttpStatus.BAD_REQUEST),
    WALLET_NOT_NULL("საფულის ველი არ უნდა იყოს ცარიელი ან უარყოფითი რიცხვი!", HttpStatus.BAD_REQUEST),
    USERNAME_NULL("ავტორიზაციისას შესაყვანი იუზერის სახელი ცარიელია!", HttpStatus.BAD_REQUEST),
    PASSWORD_NULL("ავტორიზაციისას შესაყვანი პაროლი ცარიელია!", HttpStatus.BAD_REQUEST),
    INVALID_ADMIN("ადმინის ველი უნდა შეიცვადეს ან Y-ს, ან N-ს!", HttpStatus.BAD_REQUEST),
    CALLING_REST_API_ERROR("შეცდომა მოხდა მე-2 აპლიკაციის გამოძახებისას!", HttpStatus.BAD_GATEWAY),
    SHIRT_NOT_EXISTS("მოცემული პარამეტრებით მაისური არ არსებობს", HttpStatus.NOT_FOUND),
    CANT_REDUCE_QUANTITY("ხელით არაა შესაძლებელი მაისურების რაოდენობის შემცირება. შესაძლებელია მხოლოდ გაზრდა", HttpStatus.FORBIDDEN),
    INSUFFICIENT_STOCKS("მარაგში არაა მოცემული რაოდენობის მაისური", HttpStatus.FORBIDDEN),
    INSUFFICIENT_MONEY("თანხა არასაკმარისია!", HttpStatus.FORBIDDEN),
    PURCHASES_NOT_EXISTS("მოცემული იუზერის შესყიდვების ისტორია ცარიელია", HttpStatus.NOT_FOUND);


    private String message;
    private HttpStatus status;

    ErrorEnum(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
