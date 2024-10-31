package thread.sync;

public interface BankAccount {

    boolean withdraw(int amount); //계좌 돈 출금
    int getBalance(); //계좌 잔액 반환
}
