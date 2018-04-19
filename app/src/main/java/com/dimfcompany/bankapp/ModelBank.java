package com.dimfcompany.bankapp;



public class ModelBank
{
    private String name;
    private String logo;
    private String adress;
    private String phone;
    private String site;
    private int id;



    private double[] minmax = new double[9];

    //region Constructors
    public ModelBank() {
    }

    public ModelBank(String name) {
        this.name = name;
    }

    public ModelBank(String name, String logo, String adress, String phone, String site, String[] vkladVrubl, String[] vkladVdollar, String[] credCardRubl) {
        this.name = name;
        this.logo = logo;
        this.adress = adress;
        this.phone = phone;
        this.site = site;
    }
    //endregion

    public double[] getMinmax() {
        return minmax;
    }

    public void setMinmax(double[] minmax) {
        this.minmax = minmax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }



    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
