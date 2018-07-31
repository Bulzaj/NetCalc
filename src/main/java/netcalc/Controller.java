package netcalc;

import netcalc.core.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/")
    public String home(@RequestParam(value="name", defaultValue = "User") String name) {
        return "Hello " + name;
    }

    @GetMapping(value = "/ip", produces = "application/json")
    public String calcIpAddress(@RequestParam(value="ip_address") String ipAddress,
                                     @RequestParam(value="mask") int mask) {

        int octet1, octet2, octet3, octet4;
        String [] split = ipAddress.split("-");

        octet1 = Integer.parseInt(split[0]);
        octet2 = Integer.parseInt(split[1]);
        octet3 = Integer.parseInt(split[2]);
        octet4 = Integer.parseInt(split[3]);

        Ipv4Address ipv4Address = new Ipv4Address(new Octet(1, octet1),
                new Octet(2, octet2),
                new Octet(3, octet3),
                new Octet(4, octet4),
                new Mask(mask));

        Netcalc netcalc = new Netcalc(ipv4Address);

        NetcalcJSONBuilder netcalcJSON = new NetcalcJSONBuilder(netcalc);

        return netcalcJSON.buildJson();
    }
}
