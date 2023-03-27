package onpe.com.pe.gestorconfiguracionactas.core.model;

/**
 *
 * @author NHuaman
 */
public class Setting {

    String _id;
    String id_setting;
    String name;
    String setting;
    String statusSetting;

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
