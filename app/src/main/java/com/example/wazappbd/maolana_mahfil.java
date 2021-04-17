package com.example.wazappbd;

public class maolana_mahfil {
    public String mahfildate;
    public String mahfiltype;
    public String mahfilplace;

    public maolana_mahfil(String mahfiltype,String mahfilplace,String mahfildate)
    {
        this.mahfildate=mahfildate;
        this.mahfilplace=mahfilplace;
        this.mahfiltype=mahfiltype;
    }
    public String getMahfildate(){
        return mahfildate;
    }

    public String getMahfilplace() {
        return mahfilplace;
    }

    public String getMahfiltype() {
        return mahfiltype;
    }
}
