package com.chason.entity;

public class JpFifty {

    private String ping;

    private String pian;

    public void setPian(String pian) {
        this.pian = pian;
    }

    public String getPian() {
        return pian;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getPing() {
        return ping;
    }
    public JpFifty () {

    }

    public JpFifty (String ping, String pian) {
        this.ping = ping;
        this.pian = pian;
    }
}
