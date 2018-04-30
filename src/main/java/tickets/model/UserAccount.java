package tickets.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class UserAccount {
    private UserInfo userInfo;
    private int accountId;
    private String password;
    private double balance; //余额

    @Id
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @ManyToOne(cascade = CascadeType.REMOVE,optional = true)
    @JoinColumn(name = "ownerId")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
