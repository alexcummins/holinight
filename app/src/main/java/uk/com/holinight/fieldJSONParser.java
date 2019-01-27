package uk.com.holinight;

import org.json.JSONException;
import org.json.JSONObject;


public class fieldJSONParser {




    private String equityID;
    private String name;
    private String stock;
    private String equityCode;
    private String country;
    private String type;
    private String priceCents;
    private String price;
    private String sector;
    private String ISIN;
    private String subSector;
    private String ID;
    private String signal_date;
    private String sig;
    private String signal_time;
    private String priceSignal;
    private String percentagePL;



    private String date;
    private String signaltype;
    private String additional_info;
    private String sender_id;
    private String accepted_id;
    private String position;
    private String userid;

    public fieldJSONParser() {
    }

    public fieldJSONParser(
            String equityID, String name, String stock, String equityCode, String country,
            String type, String priceCents, String price, String sector, String ISIN,
            String subSector, String ID, String signal_date, String sig, String signal_time,
            String priceSignal, String percentagePL, String date, String signaltype,
            String additional_info, String sender_id, String accepted_id, String position,
            String userid) {

        this.equityID           = equityID;
        this.name               = name;
        this.stock              = stock;
        this.equityCode         = equityCode;
        this.country            = country;
        this.type               = type;
        this.priceCents         = priceCents;
        this.price              = price;
        this.sector             = sector;
        this.ISIN               = ISIN;
        this.subSector          = subSector;
        this.ID                 = ID;
        this.signal_date        = signal_date;
        this.sig                = sig;
        this.signal_time        = signal_time;
        this.priceSignal        = priceSignal;
        this.percentagePL       = percentagePL;
        this.date               = date;
        this.signaltype         = signaltype;
        this.additional_info    = additional_info;
        this.sender_id          = sender_id;
        this.accepted_id        = accepted_id;
        this.position           = position;
        this.userid             = userid;

    }

    public fieldJSONParser(JSONObject jsonObject) {



        try {




            this.equityID           = jsonObject.getString("equityID");
            this.name               = jsonObject.getString("name");
            this.stock              = jsonObject.getString("stock");
            this.equityCode         = jsonObject.getString("equityCode");
            this.country            = jsonObject.getString("country");
            this.type               = jsonObject.getString("type");
            this.priceCents         = jsonObject.getString("priceCents");
            this.price              = jsonObject.getString("price");
            this.sector             = jsonObject.getString("sector");
            this.ISIN               = jsonObject.getString("ISIN");
            this.subSector          = jsonObject.getString("subSector");
            this.ID                 = jsonObject.getString("ID");
            this.signal_date        = jsonObject.getString("signal_date");
            this.sig                = jsonObject.getString("sig");;
            this.signal_time        = jsonObject.getString("signal_time");
            this.priceSignal        = jsonObject.getString("priceSignal");
            this.percentagePL       = jsonObject.getString("percentagePL");
//            this.date               = jsonObject.getString("date");
//            this.signaltype         = jsonObject.getString("signalType");
//            this.additional_info    = jsonObject.getString("additional_info");
//            this.sender_id          = jsonObject.getString("sender_id");
//            this.accepted_id        = jsonObject.getString("accepted_id");
//            this.position           = jsonObject.getString("position");
//            this.userid             = jsonObject.getString("userid");
            this.date = jsonObject.getString("signal_date");
            this.additional_info = "Test";
            this.sender_id = "437";
            this.accepted_id = "292";
            this.position = "Long";
            this.userid = "292";

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String getEquityID() {
        return equityID;
    }

    public void setEquityID(String equityID) {
        this.equityID = equityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getEquityCode() {
        return equityCode;
    }

    public void setEquityCode(String equityCode) {
        this.equityCode = equityCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(String priceCents) {
        this.priceCents = priceCents;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public String getSubSector() {
        return subSector;
    }

    public void setSubSector(String subSector) {
        this.subSector = subSector;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSignal_date() {
        return signal_date;
    }

    public void setSignal_date(String signal_date) {
        this.signal_date = signal_date;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getSignal_time() {
        return signal_time;
    }

    public void setSignal_time(String signal_time) {
        this.signal_time = signal_time;
    }

    public String getPriceSignal() {
        return priceSignal;
    }

    public void setPriceSignal(String priceSignal) {
        this.priceSignal = priceSignal;
    }

    public String getPercentagePL() {
        return percentagePL;
    }

    public void setPercentagePL(String percentagePL) {
        this.percentagePL = percentagePL;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getSignaltype() {
        return signaltype;
    }

    public void setSignaltype(String signaltype) {
        this.signaltype = signaltype;
    }





    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getAccepted_id() {
        return accepted_id;
    }

    public void setAccepted_id(String accepted_id) {
        this.accepted_id = accepted_id;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
