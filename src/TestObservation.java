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

public class TestObservation {

    public static boolean testLotValue(Lot lot) {
        return (lot.valuePerLot > 450000.00);
    }

    public static String getTheWrongLotValueMessage(Terrain terrain) {
        for (int i = 0; i < terrain.list_lots.size(); i++) {
            if (testLotValue(terrain.list_lots.get(i))) {
                return "\"La valeur du lot " + i + " est trop dispendieuse.\"";
            }
        }
        return null;
    }

    public static boolean testTaxeMunicipal(Terrain terrain) {
        return (terrain.municipalTax > 1000.00);
    }

    public static boolean testDateMesured(Terrain terrain) {
        Date startDate = null;
        Date endDate = null;
        long interval;

        for (Lot element : terrain.list_lots) {
            if (element.dateMeasured.before(startDate)) {
                startDate = element.dateMeasured;
            } else if (element.dateMeasured.after(endDate)) {
                endDate = element.dateMeasured;
            }
        }
        interval = endDate.getTime() - startDate.getTime();
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
        for (int i = 0; i < terrain.list_lots.size(); i++) {
            if (testSurfaceLot(terrain.list_lots.get(i))) {
                return "\"La taille du lot " + i + " est trop grande.\"";
            }
        }
        return null;
    }

    public static boolean testSquareMeterPrice(Terrain terrain) {
        return (terrain.priceMax > (terrain.priceMin * 2));
    }
}
