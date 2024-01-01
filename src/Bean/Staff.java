package Bean;

public class Staff {
    private int sId;
    private String sName;
    private String sSex;
    private String sAge;
    private String sEdu;
    private String sPlace;

    public Staff() {
    }

    public Staff(int sId, String sName, String sSex, String sAge, String sEdu, String sPlace) {
        this.sId = sId;
        this.sName = sName;
        this.sSex = sSex;
        this.sAge = sAge;
        this.sEdu = sEdu;
        this.sPlace = sPlace;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public String getsPwd() {
        return sAge;
    }
    public void setsPwd(String sAge) {
        this.sAge = sAge;
    }
    public String getsEdu() {
        return sEdu;
    }

    public void setsEdu(String sEdu) {
        this.sEdu = sEdu;
    }

    public String getsPlace() {
        return sPlace;
    }

    public void setsPlace(String sPlace) {
        this.sPlace = sPlace;
    }

}