package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

/**
 * Created by Dan on 11/18/2017.
 */

public class PersistentAccountDAO implements AccountDAO {

    private DBHelper dbHelper;

    public PersistentAccountDAO(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }
    @Override
    public List<String> getAccountNumbersList() {
        return dbHelper.getAllAccountNumbers();
    }

    @Override
    public List<Account> getAccountsList() {
        return dbHelper.getAllAccounts();
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        Account result = dbHelper.getTheAccount(accountNo);
        if(result==null){
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        return dbHelper.getTheAccount(accountNo);
    }

    @Override
    public void addAccount(Account account) {
        dbHelper.insertAccount(account.getAccountNo(),account.getBankName(),account.getAccountHolderName(),account.getBalance());
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {

        if(!dbHelper.isAccountValid(accountNo)){
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        dbHelper.deleteAccount(accountNo);
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        if(!dbHelper.isAccountValid(accountNo)){
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        Account account = getAccount(accountNo);
        // specific implementation based on the transaction type
        switch (expenseType) {
            case EXPENSE:
                account.setBalance(account.getBalance() - amount);
                break;
            case INCOME:
                account.setBalance(account.getBalance() + amount);
                break;
        }
        dbHelper.updateBalance(accountNo,account.getBalance());
    }
}
