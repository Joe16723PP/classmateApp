package kku.en.coe.extraproject;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class ObjectEvent {
    public String email;
    public String start_date , end_date ,time;
    public int evt_cnt , status;
    public double lat , lng;

    public ObjectEvent() {
    }

    public ObjectEvent(String email , String start_date , String end_date , String time , int evt_cnt , double lat ,double lng , int status) {
        this.email = email;
        this.start_date = start_date;
        this.end_date = end_date;
        this.time = time;
        this.evt_cnt = evt_cnt;
        this.lat = lat;
        this.lng = lng;
        this.status = status;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("start_date", start_date);
        result.put("end_date", end_date);
        result.put("time", time);
        result.put("evn_cnt", evt_cnt);
        result.put("lat", lat);
        result.put("lng", lng);
        result.put("status", status);
        return result;
    }
}
