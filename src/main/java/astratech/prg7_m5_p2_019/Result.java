package astratech.prg7_m5_p2_019;

public class Result {

    private Integer status;

    private String result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Result(Integer status, String result) {
        this.status = status;
        this.result = result;
    }
}
