public class Person {
    private String name;
    private int age;
    private String email;
    private String address;
    private String phoneNumber;

    public Person(String name, int age, String email, String address, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }


}
