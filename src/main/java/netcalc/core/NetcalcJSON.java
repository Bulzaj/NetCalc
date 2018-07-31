package netcalc.core;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class NetcalcJSON {

    private Netcalc netcalc;
    private Map<String, String> netcalcMap;
    private Gson gson;
    private String json;

    public NetcalcJSON(Netcalc netcalc) {
        this.netcalc = netcalc;
        netcalcMap = new HashMap<>();
        gson = new Gson();
        json = gson.toJson(netcalcMap);
        initMap();
    }

    private void initMap() {
        netcalcMap.put("Base address", netcalc.getBaseAddress().toString());
        netcalcMap.put("Binary base address", netcalc.getBaseAddress().getAsBinary());
        netcalcMap.put("Mask", netcalc.getBaseAddress().getMask().toString());
        netcalcMap.put("Binary mask", netcalc.getBaseAddress().getMask().getBinaryValue());
        netcalcMap.put("Mask alias", "" + netcalc.getBaseAddress().getMask().getMaskValue());
        netcalcMap.put("Net address", netcalc.getNetAddress().toString());
        netcalcMap.put("Binary net address ", netcalc.getNetAddress().getAsBinary());
        netcalcMap.put("Broadcast address", netcalc.getBroadcastAddress().toString());
        netcalcMap.put("Binary broadcast address ", netcalc.getBroadcastAddress().getAsBinary());
        netcalcMap.put("Max hosts in network", ""+netcalc.getMaxHostNumber());
        netcalcMap.put("First host address", netcalc.getFirstHostAddress().toString());
        netcalcMap.put("Binary first host address", netcalc.getFirstHostAddress().getAsBinary());
        netcalcMap.put("Last host address", netcalc.getLastHostAddress().toString());
        netcalcMap.put("Binary last host address", netcalc.getLastHostAddress().getAsBinary());
        netcalcMap.put("Subnets number", ""+netcalc.getSubnetsNumber());
    }

    public Map<String, String> getNetcalcMap() {
        return netcalcMap;
    }
}
