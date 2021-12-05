public class Instructor extends User {

    private OwnerType type;

    public Instructor(String email, String password, String name, String surname, String address, String city, String country, String telephone, OwnerType type) {
        super(email, password, name, surname, address, city, country, telephone);
        this.type = type;
    }
}
