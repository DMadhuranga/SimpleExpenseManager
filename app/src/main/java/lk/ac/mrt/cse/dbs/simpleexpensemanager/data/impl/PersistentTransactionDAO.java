package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentTransactionDAO implements TransactionDAO {

    private DBHelper dbHelper;
    public PersistentTransactionDAO(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }
    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        dbHelper.insertTransaction(accountNo,expenseType.toString(),dateStr,amount);
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        return dbHelper.getAllTransactions();
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return dbHelper.getAllTransactionsLimited(limit);
    }

}