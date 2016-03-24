/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author szala
 */
import java.util.Date;
import java.util.*;

public class TestObservation {

    public static boolean testLotValue(Lot lot) {
        return (lot.valuePerLot >  45000.00 );
    }

    public static String getTheWrongLotValueMessage(Terrain terrain) {
        String tmp = "\"La valeur du lot ";
        boolean test = false;
        for (int i = 0; i < terrain.list_lots.size(); i++) {
            if (testLotValue(terrain.list_lots.get(i))) {
                tmp += "(" + (i + 1) + ")";
                test = true;
            }
        }
        if (test) {
            tmp += " est trop dispendieuse.\"";
            return tmp;
        }
        return null;
    }

    public static boolean testTaxeMunicipal(Terrain terrain) {
        return (terrain.municipalTax > 1000.00);
    }

    public static boolean testDateMesured(Terrain terrain) {
        long interval;
        SortedSet<Date> set = new TreeSet<>();
        for (Lot element : terrain.list_lots) {
            set.add(element.dateMeasured);
        }
        interval = set.last().getTime() - set.first().getTime();
        return (interval > 15768017280L);
    }

    public static Boolean testSchoolTaxe(Terrain terrain) {
        return (terrain.schoolTax > 500.00);
    }

    public static boolean testFonciereValue(Terrain terrain) {
        return (terrain.totalLandValue > 300000.00);
    }

    public static boolean testSurfaceLot(Lot lot) {
        return (lot.surfaceArea > 45000.00);
    }

    public static String getTheWrongLotSurfaceMessage(Terrain terrain) {
        String tmp = "\"La taille du lot ";
        boolean test = false;
        for (int i = 0; i < terrain.list_lots.size(); i++) {
            if (testSurfaceLot(terrain.list_lots.get(i))) {
                tmp += "(" + (i + 1) + ")";
                test = true;
            }
        }
        if (test) {
            tmp += " est trop grande.\"";
            return tmp;
        }
        return null;
    }

    public static boolean testSquareMeterPrice(Terrain terrain) {
        return (terrain.priceMax > (terrain.priceMin * 2));
    }

    public static boolean testAll(Terrain terrain) {
        if (testDateMesured(terrain)) {
            return true;
        }
        if (testFonciereValue(terrain)) {
            return true;
        }
        if (testSchoolTaxe(terrain)) {
            return true;
        }
        if (testSquareMeterPrice(terrain)) {
            return true;
        }
        if (testTaxeMunicipal(terrain)) {
            return true;
        }
        if (getTheWrongLotValueMessage(terrain) != null) {
            return true;
        }
        if (getTheWrongLotSurfaceMessage(terrain) != null) {
            return true;
        }

        return false;
    }
}
