package kku.en.coe.extraproject;

public class readEvent {
    public String email;
    public String start_date , end_date ,time;
    public String evt_cnt;
    public String status;
    public String lat , lng;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvt_cnt() {
        return evt_cnt;
    }

    public void setEvt_cnt(String evt_cnt) {
        this.evt_cnt = evt_cnt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public readEvent(String email, String start_date, String end_date, String time, String evt_cnt, String status, String lat, String lng) {
        this.email = email;
        this.start_date = start_date;
        this.end_date = end_date;
        this.time = time;
        this.evt_cnt = evt_cnt;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
    }
}
