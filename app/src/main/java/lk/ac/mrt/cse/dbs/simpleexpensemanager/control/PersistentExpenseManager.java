package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.DBHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by Dan on 11/18/2017.
 */

public class PersistentExpenseManager extends ExpenseManager {

    private Context context;
    public DBHelper dbHelper;

    public PersistentExpenseManager(Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
        setup();
    }
    @Override
    public void setup(){
        TransactionDAO persistentTransactionDAO = new PersistentTransactionDAO(dbHelper);
        setTransactionsDAO(persistentTransactionDAO);
        AccountDAO persistentAccountDAO = new PersistentAccountDAO(dbHelper);
        setAccountsDAO(persistentAccountDAO);
        /*List<Transaction> acounts = persistentTransactionDAO.getAllTransactionLogs();
        for (Transaction account:acounts) {
            Toast.makeText(context, account.getAccountNo()+" "+ account.getExpenseType().toString()+" "+Double.toString(account.getAmount()), Toast.LENGTH_SHORT).show();
        }*/
    }
}
