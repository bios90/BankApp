package com.dimfcompany.bankapp;

public class ModelVklad
{
    String name,bankName,logo;
    int id,bank_id,rubsymma,rubsrok,dolsymma,dolsrok,eurosymma,eurosrok;
    double stavkarub,stavkadol,stavkaeuro;
    boolean popoln,snyatie,rastorj;

    //region Concstructors
    public ModelVklad()
    {

    }

    public ModelVklad(String name, String bankName, int id, int bank_id, int rubsymma, int rubsrok, int dolsymma, int dolsrok, int eurosymma, int eurosrok, double stavkarub, double stavkadol, double stavkaeuro, boolean popoln, boolean snyatie, boolean rastorj) {
        this.name = name;
        this.bankName = bankName;
        this.id = id;
        this.bank_id = bank_id;
        this.rubsymma = rubsymma;
        this.rubsrok = rubsrok;
        this.dolsymma = dolsymma;
        this.dolsrok = dolsrok;
        this.eurosymma = eurosymma;
        this.eurosrok = eurosrok;
        this.stavkarub = stavkarub;
        this.stavkadol = stavkadol;
        this.stavkaeuro = stavkaeuro;
        this.popoln = popoln;
        this.snyatie = snyatie;
        this.rastorj = rastorj;
    }
    //endregion

    //region GettersSetters
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public int getRubsymma() {
        return rubsymma;
    }

    public void setRubsymma(int rubsymma) {
        this.rubsymma = rubsymma;
    }

    public int getRubsrok() {
        return rubsrok;
    }

    public void setRubsrok(int rubsrok) {
        this.rubsrok = rubsrok;
    }

    public int getDolsymma() {
        return dolsymma;
    }

    public void setDolsymma(int dolsymma) {
        this.dolsymma = dolsymma;
    }

    public int getDolsrok() {
        return dolsrok;
    }

    public void setDolsrok(int dolsrok) {
        this.dolsrok = dolsrok;
    }

    public int getEurosymma() {
        return eurosymma;
    }

    public void setEurosymma(int eurosymma) {
        this.eurosymma = eurosymma;
    }

    public int getEurosrok() {
        return eurosrok;
    }

    public void setEurosrok(int eurosrok) {
        this.eurosrok = eurosrok;
    }

    public double getStavkarub() {
        return stavkarub;
    }

    public void setStavkarub(double stavkarub) {
        this.stavkarub = stavkarub;
    }

    public double getStavkadol() {
        return stavkadol;
    }

    public void setStavkadol(double stavkadol) {
        this.stavkadol = stavkadol;
    }

    public double getStavkaeuro() {
        return stavkaeuro;
    }

    public void setStavkaeuro(double stavkaeuro) {
        this.stavkaeuro = stavkaeuro;
    }

    public boolean isPopoln() {
        return popoln;
    }

    public void setPopoln(boolean popoln) {
        this.popoln = popoln;
    }

    public boolean isSnyatie() {
        return snyatie;
    }

    public void setSnyatie(boolean snyatie) {
        this.snyatie = snyatie;
    }

    public boolean isRastorj() {
        return rastorj;
    }

    public void setRastorj(boolean rastorj) {
        this.rastorj = rastorj;
    }
    //endregion

}
