package org.tbogdanov.epamhw.module2.officesupplies.officedeskstats;

import org.tbogdanov.epamhw.module2.officesupplies.supplies.OfficeSupply;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class OfficeDesk {
    /* We could use HashMap here, but
       what if we were to rename an employee?
       Do we really want to recreate their deck?
     */
    private String employeeName;
    private List<OfficeSupply> supplies;

    public OfficeDesk(String employeeName) {
        this.employeeName = employeeName;
        supplies = new LinkedList<>();
    }

    public OfficeDesk(String employeeName, List<OfficeSupply> supplies) {
        this.employeeName = employeeName;
        this.supplies = supplies;
    }

    public class NameComparator implements Comparator<OfficeSupply> {
        @Override
        public int compare(OfficeSupply os1, OfficeSupply os2) {
            return os1.getFullName().compareTo(os2.getFullName());
        }
    }

    public class PriceComparator implements Comparator<OfficeSupply> {
        @Override
        public int compare(OfficeSupply os1, OfficeSupply os2) {
            return os1.getPrice()-os2.getPrice();
        }
    }

    public class PriceAndNameComparator implements Comparator<OfficeSupply> {
        @Override
        public int compare(OfficeSupply os1, OfficeSupply os2) {
            int priceDelta = os1.getPrice()-os2.getPrice();
            if (priceDelta != 0) {
                return priceDelta;
            }
            return os1.getFullName().compareTo(os2.getFullName());
        }
    }

    public int getDeskPriceSum() {
        int sum = 0;
        for (OfficeSupply supply : supplies) {
            if (supply != null) {
                sum += supply.getPrice();
            }
        }
        return sum;
    }

    public void sortSuppliesByName(boolean desc) {
        supplies.sort(new NameComparator());
        if (desc) {
            Collections.reverse(supplies);
        }
    }

    public void sortSuppliesByPrice(boolean desc) {
        supplies.sort(new PriceComparator());
        if (desc) {
            Collections.reverse(supplies);
        }
    }

    public void sortSuppliesByPriceAndName(boolean desc) {
        supplies.sort(new PriceAndNameComparator());
        if (desc) {
            Collections.reverse(supplies);
        }
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<OfficeSupply> getSupplies() {
        return supplies;
    }

    public OfficeSupply getSupply(int i) throws InvalidParameterException {
        if (i >= supplies.size()) {
            throw new InvalidParameterException(String.format("Out of list (tried %d but size is %d)",
                    i, supplies.size()));
        }
        return supplies.get(i);
    }

    public OfficeSupply removeSupply(int i) throws InvalidParameterException {
        if (i >= supplies.size()) {
            throw new InvalidParameterException(String.format("Out of list (tried %d but size is %d)",
                    i, supplies.size()));
        }
        return supplies.remove(i);
    }

    public void setSupplies(List<OfficeSupply> supplies) {
        this.supplies = supplies;
    }

    public void addSupply(OfficeSupply supply) {
        supplies.add(supply);
    }

    public void addSupplies(List<OfficeSupply> supplies) {
        this.supplies.addAll(supplies);
    }
}
