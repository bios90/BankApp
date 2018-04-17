package com.dimfcompany.bankapp;

public class ModelCard
{
    String name,bankName,logo;
    double stavkaRub,stavkaDol,stavkaEuro,nalsnyatie;
    int id,id_bank,rubLimit,dolLimit,euroLimit,besplatnoeO,obsluj,cashBack,lgotSrok,lgotType,age,cardPodtverj,register,cardStaj,dostavka,reshenie;

    public ModelCard()
    {

    }

    //region GettersSetters
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

    public double getStavkaRub() {
        return stavkaRub;
    }

    public void setStavkaRub(double stavkaRub) {
        this.stavkaRub = stavkaRub;
    }

    public double getStavkaDol() {
        return stavkaDol;
    }

    public void setStavkaDol(double stavkaDol) {
        this.stavkaDol = stavkaDol;
    }

    public double getStavkaEuro() {
        return stavkaEuro;
    }

    public void setStavkaEuro(double stavkaEuro) {
        this.stavkaEuro = stavkaEuro;
    }

    public double getNalsnyatie() {
        return nalsnyatie;
    }

    public void setNalsnyatie(double nalsnyatie) {
        this.nalsnyatie = nalsnyatie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_bank() {
        return id_bank;
    }

    public void setId_bank(int id_bank) {
        this.id_bank = id_bank;
    }

    public int getRubLimit() {
        return rubLimit;
    }

    public void setRubLimit(int rubLimit) {
        this.rubLimit = rubLimit;
    }

    public int getDolLimit() {
        return dolLimit;
    }

    public void setDolLimit(int dolLimit) {
        this.dolLimit = dolLimit;
    }

    public int getEuroLimit() {
        return euroLimit;
    }

    public void setEuroLimit(int euroLimit) {
        this.euroLimit = euroLimit;
    }

    public int getBesplatnoeO() {
        return besplatnoeO;
    }

    public void setBesplatnoeO(int besplatnoeO) {
        this.besplatnoeO = besplatnoeO;
    }

    public int getObsluj() {
        return obsluj;
    }

    public void setObsluj(int obsluj) {
        this.obsluj = obsluj;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }

    public int getLgotSrok() {
        return lgotSrok;
    }

    public void setLgotSrok(int lgotSrok) {
        this.lgotSrok = lgotSrok;
    }

    public int getLgotType() {
        return lgotType;
    }

    public void setLgotType(int lgotType) {
        this.lgotType = lgotType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCardPodtverj() {
        return cardPodtverj;
    }

    public void setCardPodtverj(int cardPodtverj) {
        this.cardPodtverj = cardPodtverj;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public int getCardStaj() {
        return cardStaj;
    }

    public void setCardStaj(int cardStaj) {
        this.cardStaj = cardStaj;
    }

    public int getDostavka() {
        return dostavka;
    }

    public void setDostavka(int dostavka) {
        this.dostavka = dostavka;
    }

    public int getReshenie() {
        return reshenie;
    }

    public void setReshenie(int reshenie) {
        this.reshenie = reshenie;
    }
    //endregion
}
