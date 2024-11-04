package com.hk.transportProject.Data.Traffic;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Xml(name = "response")
public class TrafficResponse {

    @Element(name = "msgHeader")
    public MsgHeader msgHeader;

    @Element(name = "msgBody")
    public MsgBody msgBody;

    @Xml(name = "msgHeader")
    public static class MsgHeader {
        @PropertyElement(name = "queryTime")
        public String queryTime;

        @PropertyElement(name = "resultCode")
        public String resultCode;

        @PropertyElement(name = "resultMessage")
        public String resultMessage;
    }

    @Xml(name = "msgBody")
    public static class MsgBody {
        @PropertyElement(name = "busStationAroundList")
        public BusStationAroundList busStationAroundList;
    }

    @Xml(name = "busStationAroundList")
    public static class BusStationAroundList {

        @PropertyElement(name = "centerYn")
        public String centerYn;

        @PropertyElement(name = "districtCd")
        public String districtCd;

        @PropertyElement(name = "mobileNo")
        public String mobileNo;

        @PropertyElement(name = "regionName")
        public String regionName;

        @PropertyElement(name = "stationId")
        public String stationId;

        @PropertyElement(name = "stationName")
        public String stationName;

        @PropertyElement(name = "x")
        public float x;

        @PropertyElement(name = "y")
        public float y;

        @PropertyElement(name = "distance")
        public String distance;
    }
}