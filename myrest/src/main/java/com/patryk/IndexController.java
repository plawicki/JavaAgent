package com.patryk;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.*;
import java.lang.management.ManagementFactory;

@RestController
public class IndexController {

    private IndexMonitor im;

    public IndexController(){
        super();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName monitorName = null;

        try {
            monitorName = new ObjectName("com.patryk:type=Server,name=IndexService");

            im = new IndexMonitor();
            mbs.registerMBean(im, monitorName);

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String index(Integer liczba) {

        StringBuilder retVal = new StringBuilder("Szczesliwy numerek na dzis to: ");

        if(liczba != null) {
            im.setLiczba(liczba);
            retVal.append(im.getLiczba());
        }

        return retVal.toString();
    }
}
