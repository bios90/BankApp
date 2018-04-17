package com.dimfcompany.bankapp;

public class ModelCredit
{
    String name,bankName,logo;
    int id,bank_id,rubsymmacredit,rubsrokcredit,dolsymmacredit,dolsrokcredit,eurosymmacredit,eurosrokcredit,staj,podtverj;
    double stavkarubcredit,stavkadolcredit,stavkaeurocredit;
    boolean refinans,obes,tolkopass;

    public ModelCredit() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public int getRubsymmacredit() {
        return rubsymmacredit;
    }

    public void setRubsymmacredit(int rubsymmacredit) {
        this.rubsymmacredit = rubsymmacredit;
    }

    public int getRubsrokcredit() {
        return rubsrokcredit;
    }

    public void setRubsrokcredit(int rubsrokcredit) {
        this.rubsrokcredit = rubsrokcredit;
    }

    public int getDolsymmacredit() {
        return dolsymmacredit;
    }

    public void setDolsymmacredit(int dolsymmacredit) {
        this.dolsymmacredit = dolsymmacredit;
    }

    public int getDolsrokcredit() {
        return dolsrokcredit;
    }

    public void setDolsrokcredit(int dolsrokcredit) {
        this.dolsrokcredit = dolsrokcredit;
    }

    public int getEurosymmacredit() {
        return eurosymmacredit;
    }

    public void setEurosymmacredit(int eurosymmacredit) {
        this.eurosymmacredit = eurosymmacredit;
    }

    public int getEurosrokcredit() {
        return eurosrokcredit;
    }

    public void setEurosrokcredit(int eurosrokcredit) {
        this.eurosrokcredit = eurosrokcredit;
    }

    public int getStaj() {
        return staj;
    }

    public void setStaj(int staj) {
        this.staj = staj;
    }

    public int getPodtverj() {
        return podtverj;
    }

    public void setPodtverj(int podtverj) {
        this.podtverj = podtverj;
    }

    public double getStavkarubcredit() {
        return stavkarubcredit;
    }

    public void setStavkarubcredit(double stavkarubcredit) {
        this.stavkarubcredit = stavkarubcredit;
    }

    public double getStavkadolcredit() {
        return stavkadolcredit;
    }

    public void setStavkadolcredit(double stavkadolcredit) {
        this.stavkadolcredit = stavkadolcredit;
    }

    public double getStavkaeurocredit() {
        return stavkaeurocredit;
    }

    public void setStavkaeurocredit(double stavkaeurocredit) {
        this.stavkaeurocredit = stavkaeurocredit;
    }

    public boolean isRefinans() {
        return refinans;
    }

    public void setRefinans(boolean refinans) {
        this.refinans = refinans;
    }

    public boolean isObes() {
        return obes;
    }

    public void setObes(boolean obes) {
        this.obes = obes;
    }

    public boolean isTolkopass() {
        return tolkopass;
    }

    public void setTolkopass(boolean tolkopass) {
        this.tolkopass = tolkopass;
    }
}
