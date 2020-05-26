package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.views.Alert;

import java.io.*;
import java.util.ArrayList;

public class AccountManager {
    private ArrayList<Account> accounts = new ArrayList<>();
    private Account currentAccount;
    private static transient AccountManager accountManager;
    private static final String accountsFile = "data/accounts.dat";

    private AccountManager() {
        load();
    }

    public static AccountManager getInstance() {
        if (accountManager == null)
            accountManager = new AccountManager();

        return accountManager;
    }

    public boolean login(String login) {
        for (Account account: accounts) {
            if (account.getUsername().equals(login)) {
                currentAccount = account;
                save();
                return true;
            }
        }

        return false;
    }

    public boolean createAccount(String login) {
        for (Account account : accounts) {
            if (account.getUsername().equals(login))
                return false;
        }

        Account account = new Account(login);
        accounts.add(account);
        save();

        return true;
    }

    public void load() {
        try (
                FileInputStream file = new FileInputStream(accountsFile);
                ObjectInputStream in = new ObjectInputStream(file);
        ){
            accounts = (ArrayList<Account>) in.readObject();
            currentAccount = (Account) in.readObject();
        }
        catch (Exception ex) {
            accounts = new ArrayList<>();
            currentAccount = null;
        }
    }

    public void save() {
        File file = new File(accountsFile);
        file.getParentFile().mkdirs();

        try (
                FileOutputStream os = new FileOutputStream(accountsFile);
                ObjectOutputStream out = new ObjectOutputStream(os);
        ){
            out.writeObject(accounts);
            out.writeObject(currentAccount);
        }
        catch (IOException e) {
            Alert.showError("Error while saving accounts", e.getLocalizedMessage());
        }
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
