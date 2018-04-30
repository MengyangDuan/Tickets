package tickets.povo;

import tickets.model.UserAccount;

public class UserAccountVO{

        private int accountId;
        private String password;
        private double balance; //余额

    public UserAccountVO(UserAccount userAccount){
       this.accountId=userAccount.getAccountId();
       this.password=userAccount.getPassword();
       this.balance=userAccount.getBalance();

    }

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
}
