package com.example.bankingproject_joshua_drahi;

public class Account {
    private int id;
    private String accountName;
    private double amount;
    private String iban;
    private String currency;

    public int getId() {return id;}
    public String getIdStr() {return String.valueOf(id);}
    public String getAccountName() {return accountName;}
    public double getAmount() {return  amount;}
    public String getAmountStr() {return String.valueOf(amount);}
    public String getIban() {return iban;}
    public String getCurrency() {return currency;}

    public void setId(int value) {id = value;}
    public void setAccountName(String value) {accountName = value;}
    public void setAmount(double value) {amount = value;}
    public void setIban(String value) {iban = value;}
    public void setCurrency(String value) {currency = value;}

    public Account(String data){
        parseAndInstantiate(data);
    }

    private void parseAndInstantiate(String data){
        String[] splitData = data.split("\\,");

        String[] idChunk = splitData[0].split("\\:");
        id = Integer.parseInt(idChunk[1].substring(1,idChunk[1].length() - 1));

        String[] nameChunk = splitData[1].split("\\:");
        accountName = nameChunk[1].substring(1,nameChunk[1].length() - 1);

        String[] amountChunk = splitData[2].split("\\:");
        int bob = amountChunk.length;
        String billy = amountChunk[0];
        String jane = amountChunk[1];
        if(amountChunk[1].contains("."))
            amount = Double.parseDouble(amountChunk[1].substring(1,amountChunk[1].length() - 1));
        else
            amount = Double.parseDouble(splitData[2].substring(9,splitData[2].length() - 1)) * 10;

        String[] ibanChunk = splitData[3].split("\\:");
        iban = ibanChunk[1].substring(1,ibanChunk[1].length() - 1);

        String[] currencyChunk = splitData[4].split("\\:");
        currency = currencyChunk[1].substring(1);

    }

    @Override
    public String toString(){
        return String.format("id: %s\nAccount Name: %s\nAmount: %s\nIBAN: %s\nCurrency: %s", id, accountName, amount, iban, currency);
    }
}
