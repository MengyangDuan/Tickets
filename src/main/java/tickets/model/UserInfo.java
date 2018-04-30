package tickets.model;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userInfo")
public class UserInfo {
    private int id;
    private String userName;
    private String password;
    private String email;
    private int rewardPoints;   //积分
    private String activeCode; //激活码
    private int userState; //0表示未激活，1表示激活，2表示注销
    private Set<UserAccount> userAccounts=new HashSet<UserAccount>();
    private Set<Coupon> coupons=new HashSet<Coupon>();     //优惠卷
    private Set<Orders> orders=new HashSet<Orders>();
    private Set<PlatformAccount> platformAccounts=new HashSet<PlatformAccount>();

    public UserInfo(){

    }
    public UserInfo(String userName,String password,String email,String activeCode){
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.activeCode=activeCode;
    }


    @OneToMany(mappedBy = "userInfo",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public void addUserAccount(UserAccount userAccount){
        userAccounts.add(userAccount);
        userAccount.setUserInfo(this);
    }

    public void delUserAccount(UserAccount userAccount){
        userAccount.setUserInfo(null);
        userAccounts.remove(userAccount);
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    @ManyToMany(mappedBy = "userInfo")
    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }

    @OneToMany(mappedBy = "userInfo",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public void addOrder(Orders order){
        order.setUserInfo(this);
    }



    @OneToMany(mappedBy = "userInfo",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<PlatformAccount> getPlatformAccounts() {
        return platformAccounts;
    }

    public void setPlatformAccounts(Set<PlatformAccount> platformAccounts) {
        this.platformAccounts = platformAccounts;
    }

    public void addPlatformAccount(PlatformAccount platformAccount){
        platformAccount.setUserInfo(this);
    }

}
