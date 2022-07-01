package com.example.deptfreedom;

public class getdatamodel {


    public getdatamodel()
    {

    }

    String deptname,sbalance,mpayment,apr,category,paydate,remdate,setMesaageId;

    public getdatamodel(String deptname, String sbalance, String mpayment, String apr, String category, String paydate, String remdate) {
        this.deptname = deptname;
        this.sbalance = sbalance;
        this.mpayment = mpayment;
        this.apr = apr;
        this.category = category;
        this.paydate = paydate;
        this.remdate = remdate;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getSbalance() {
        return sbalance;
    }

    public void setSbalance(String sbalance) {
        this.sbalance = sbalance;
    }

    public String getMpayment() {
        return mpayment;
    }

    public void setMpayment(String mpayment) {
        this.mpayment = mpayment;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getRemdate() {
        return remdate;
    }

    public void setRemdate(String remdate) {
        this.remdate = remdate;
    }

}
