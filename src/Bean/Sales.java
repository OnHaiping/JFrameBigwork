package Bean;

public class Sales {
    private int saleId;
    private String saleName;
    private String saleClient;
    private String saleTime;
    private String saleStaff;
    public Sales() {
    }
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleClient() {
        return saleClient;
    }

    public void setSaleClient(String saleClient) {
        this.saleClient = saleClient;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public String getSaleStaff() {
        return saleStaff;
    }

    public void setSaleStaff(String saleStaff) {
        this.saleStaff = saleStaff;
    }
}
