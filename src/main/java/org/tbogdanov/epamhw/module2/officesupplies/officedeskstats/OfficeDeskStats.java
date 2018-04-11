package org.tbogdanov.epamhw.module2.officesupplies.officedeskstats;


import org.tbogdanov.epamhw.module2.officesupplies.supplies.OfficeSupply;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class OfficeDeskStats {

    private List<OfficeDesk> deskList;

    enum SortType {
        BY_NAME, BY_PRICE, BY_PRICE_NAME;
    }

    enum SortManner {
        ASCEND, DESCEND
    }

    public OfficeDeskStats() {
        deskList = new LinkedList<>();
    }

    public void addDesk(String employeeName) {
        deskList.add(new OfficeDesk(employeeName));
    }

    public void addDesk(String employeeName, List<OfficeSupply> supplies) {
        deskList.add(new OfficeDesk(employeeName, supplies));

    }

    public OfficeDesk removeDesk(int i) throws InvalidParameterException {
        if (i >= deskList.size()) {
            throw new InvalidParameterException(String.format("Out of list (tried %d but size is %d)",
                    i, deskList.size()));
        }
        return deskList.remove(i);
    }

    public OfficeDesk getDesk(int i) throws InvalidParameterException {
        if (i >= deskList.size()) {
            throw new InvalidParameterException(String.format("Out of list (tried %d but size is %d)",
                    i, deskList.size()));
        }
        return deskList.get(i);
    }

    public List<OfficeDesk> getDeskList() {
        return deskList;
    }

    public void sortDesk(int i, SortType sortType, SortManner sortManner) throws InvalidParameterException {
        if (i >= deskList.size()) {
            throw new InvalidParameterException(String.format("Out of list (tried %d but size is %d)",
                    i, deskList.size()));
        }

        boolean desc = (sortManner == SortManner.DESCEND);

        switch (sortType) {
            case BY_NAME:
                deskList.get(i).sortSuppliesByName(desc);
                break;
            case BY_PRICE:
                deskList.get(i).sortSuppliesByPrice(desc);
                break;
            case BY_PRICE_NAME:
                deskList.get(i).sortSuppliesByPriceAndName(desc);
        }
    }

}
