package com.example.oncuoiki;

import java.util.HashMap;
import java.util.Map;

public class DoanhThu {
    private int tienkc;
    private int tienkt;
    private int id;

    public DoanhThu() {
    }

    public DoanhThu(int tienkc, int tienkt) {
        this.tienkc = tienkc;
        this.tienkt = tienkt;
    }

    public int getTienkc() {
        return tienkc;
    }

    public void setTienkc(int tienkc) {
        this.tienkc = tienkc;
    }

    public int getTienkt() {
        return tienkt;
    }

    public void setTienkt(int tienkt) {
        this.tienkt = tienkt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("tienkc",tienkc);
        result.put("tienkt",tienkt);
        return result;
    }
}
