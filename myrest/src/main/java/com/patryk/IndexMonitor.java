package com.patryk;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Created by patrykl on 17/04/15.
 */
public class IndexMonitor extends NotificationBroadcasterSupport implements
        IndexMonitorMBean {

    public static final int MAX_LICZBA = 23;

    private int seq = 0;

    @Override
    public int getLiczba() {
        return CounterService.LICZBA;
    }

    @Override
    public void setLiczba(int liczba) {

        if (liczba > MAX_LICZBA) {

            Notification notification = new AttributeChangeNotification(this,
                    seq++, System.currentTimeMillis(),
                    "Przekroczenie maksymalnego numerka", "LICZBA", "int", CounterService.LICZBA,
                    liczba);
            notification.setUserData("zmiana szczesliwego numerka  na " + liczba);
            sendNotification(notification);

        }

        CounterService.LICZBA = liczba;
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };

        String name = AttributeChangeNotification.class.getName();
        String description = "Zmiana wartosci atrybutu";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
                description);
        return new MBeanNotificationInfo[] { info };
    }
}
