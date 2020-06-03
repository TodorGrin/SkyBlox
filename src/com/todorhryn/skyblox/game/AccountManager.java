package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.views.Alert;

import java.io.*;
import java.util.ArrayList;

public class AccountManager {
    private ArrayList<Account> accounts = new ArrayList<>();
    private Account currentAccount;
    private static transient AccountManager accountManager;
    private static final String accountsFile = "data/accounts.dat";

    private static final String guestUsername = "Гость";
    private static final String guestPassword = "@Gdv7P#67$";

    private AccountManager() {
        load();
    }

    public static AccountManager getInstance() {
        if (accountManager == null)
            accountManager = new AccountManager();

        return accountManager;
    }

    public boolean login(String login, String password) {
        for (Account account: accounts) {
            if (account.getUsername().equals(login) && account.getPassword().check(password)) {
                currentAccount = account;
                save();
                return true;
            }
        }

        return false;
    }

    public boolean createAccount(String login, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(login))
                return false;
        }

        Account account = new Account(login, new Password(password));
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
        catch (ClassNotFoundException | IOException ex) {
            accounts = new ArrayList<>();
            currentAccount = null;
        }

        if (currentAccount == null)
            loginAsGuest();
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

    public void loginAsGuest() {
        createAccount(guestUsername, guestPassword);
        login(guestUsername, guestPassword);
    }

    public boolean loggedInAsGuest() {
        return currentAccount.getUsername().equals(guestUsername);
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
