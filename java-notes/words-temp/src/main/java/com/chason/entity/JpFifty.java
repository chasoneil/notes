package com.chason.entity;

import java.util.Set;

public class JpFifty {

    private String ping;

    private String pian;

    private Set<String> read;

    public Set<String> getRead() {
        return read;
    }

    public void setRead(Set<String> read) {
        this.read = read;
    }

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
