package api.freelance.entities;

public class RegisterUser {

    private String username;
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public RegisterUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterUser setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
