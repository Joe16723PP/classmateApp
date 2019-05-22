package kku.en.coe.extraproject;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class ObjectEvent {
    public String email;

    public ObjectEvent(String email, String days_log, String evName, String dayofclass, String color, String start_date, String end_date, String time, int evt_cnt, double lat, double lng) {
        this.email = email;
        this.days_log = days_log;
        this.start_date = start_date;
        this.end_date = end_date;
        this.time = time;
        this.evName = evName;
        this.dayofclass = dayofclass;
        this.color = color;
        this.evt_cnt = evt_cnt;
        this.lat = lat;
        this.lng = lng;
    }

    public String days_log;
    public String start_date , end_date ,time,evName, dayofclass, color;
    public int evt_cnt;

    public String getDays_log() {
        return days_log;
    }

    public int getEvn_cnt() {
        return evt_cnt;
    }

    public void setEvn_cnt(int evn_cnt) {
        this.evt_cnt = evn_cnt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public void setDayofclass(String dayofclass) {
        this.dayofclass = dayofclass;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double lat , lng;

    public String getEmail() {
        return email;
    }

    public String getEvName() {
        return evName;
    }

    public String getDayofclass() {
        return dayofclass;
    }


    public String getColor() {
        return color;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getTime() {
        return time;
    }


    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public ObjectEvent() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("evName", evName);
        result.put("dayofclass", dayofclass);
        result.put("days_log" , days_log);
        result.put("color", color);
        result.put("start_date", start_date);
        result.put("end_date", end_date);
        result.put("time", time);
        result.put("evn_cnt", evt_cnt);
        result.put("lat", lat);
        result.put("lng", lng);
        return result;
    }
}
