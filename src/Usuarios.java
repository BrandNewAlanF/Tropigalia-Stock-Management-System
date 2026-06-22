import java.io.*;
final class Usuarios implements Serializable {
    private String name0,surname0,passW0;
    public Usuarios(String name,String surname,String passW) {
        name0=name;
        surname0=surname;
        passW0=passW;
    }

    @Override
    public String toString() {
        return "User [name0=" + name0 + ", surname0=" + surname0 + ", passW0=" + passW0 + "]";
    }

    public Usuarios() {
        this("","","");
    }

    public String getName0() {
        return name0;
    }

    public void setName0(String name) {
        this.name0 = name;
    }

    public String getSurname0() {
        return surname0;
    }

    public void setSurname0(String surname) {
        this.surname0 = surname;
    }

    public String getPassW0() {
        return passW0;
    }

    public void setPassW0(String passW) {
        this.passW0 = passW;
    }
}