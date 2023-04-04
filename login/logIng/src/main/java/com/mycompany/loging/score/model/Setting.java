package com.mycompany.loging.score.model;


public class Setting {

    private String _id;
    private String id_setting;
    private String name;
    private String setting;
    private String statusSetting;  
    
    public String getId() {
        return _id;  
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getId_setting() {
        return id_setting;
    }

    public void setId_setting(String id_setting) {
        this.id_setting = id_setting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getStatusSetting() {
        return statusSetting;
    }

    public void setStatusSetting(String statusSetting) {
        this.statusSetting = statusSetting;
    }
}