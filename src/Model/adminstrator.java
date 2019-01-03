package Model;

import javafx.beans.property.SimpleStringProperty;

public class adminstrator {
    private SimpleStringProperty administratorId;
    private SimpleStringProperty administratorName;
    private SimpleStringProperty administratorPwd;

    public String getAdministratorId() {
        return administratorId.get();
    }

    public SimpleStringProperty administratorIdProperty() {
        return administratorId;
    }

    public void setAdministratorId(String administratorId) {
        this.administratorId.set(administratorId);
    }

    public String getAdministratorName() {
        return administratorName.get();
    }

    public SimpleStringProperty administratorNameProperty() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName.set(administratorName);
    }

    public String getAdministratorPwd() {
        return administratorPwd.get();
    }

    public SimpleStringProperty administratorPwdProperty() {
        return administratorPwd;
    }

    public void setAdministratorPwd(String administratorPwd) {
        this.administratorPwd.set(administratorPwd);
    }

    public adminstrator(String administratorId, String administratorName, String administratorPwd) {
        this.administratorId = new SimpleStringProperty(administratorId);
        this.administratorName =  new SimpleStringProperty(administratorName);
        this.administratorPwd = new SimpleStringProperty(administratorPwd);

    }
}
