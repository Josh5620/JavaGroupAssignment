// shared_manager/SupplierManager.java
package shared_manager;

import shared_model.Supplier;
import java.io.*;
import java.util.*;

public class SupplierManager {
    private static final String SUPPLIER_FILE = "Suppliers.txt";

    public static List<Supplier> loadSuppliers() {
        List<Supplier> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Supplier s = Supplier.parse(line);
                if (s != null) list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean saveSuppliers(List<Supplier> suppliers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SUPPLIER_FILE))) {
            for (Supplier s : suppliers) {
                bw.write(s.toDataLine());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addSupplier(Supplier newSup) {
        List<Supplier> list = loadSuppliers();
        for (Supplier s : list) {
            if (s.getSupplierID().equalsIgnoreCase(newSup.getSupplierID())) {
                return false;  // duplicate
            }
        }
        list.add(newSup);
        return saveSuppliers(list);
    }

    public static boolean editSupplier(Supplier updatedSup) {
        List<Supplier> list = loadSuppliers();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSupplierID()
                     .equalsIgnoreCase(updatedSup.getSupplierID())) {
                list.set(i, updatedSup);
                return saveSuppliers(list);
            }
        }
        return false;
    }

    public static boolean deleteSupplier(String supplierID) {
        List<Supplier> list = loadSuppliers();
        boolean removed = list.removeIf(s -> 
            s.getSupplierID().equalsIgnoreCase(supplierID));
        return removed && saveSuppliers(list);
    }

    public static Supplier getSupplierByID(String supplierID) {
        return loadSuppliers().stream()
            .filter(s -> s.getSupplierID()
                          .equalsIgnoreCase(supplierID))
            .findFirst()
            .orElse(null);
    }

    /** Return all itemIDs this supplier carries */
    public static List<String> getItemIDsBySupplier(String supplierID) {
        Supplier s = getSupplierByID(supplierID);
        return s != null 
            ? s.getItemIDs() 
            : Collections.emptyList();
    }
}
