package lv.javaguru.java2.resources;

import lv.javaguru.java2.domain.GamblingType;
import lv.javaguru.java2.domain.LandBasedCasino;

import java.util.List;

public class OfflineGamblingEventData {

    private List<LandBasedCasino> landBasedCasinoList;
    private List<GamblingType> gamblingTypeList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public List<GamblingType> getGamblingTypeList() {
        return gamblingTypeList;
    }

    public void setGamblingTypeList(List<GamblingType> gamblingTypeList) {
        this.gamblingTypeList = gamblingTypeList;
    }

    public List<LandBasedCasino> getLandBasedCasinoList() {
        return landBasedCasinoList;
    }

    public void setLandBasedCasinoList(List<LandBasedCasino> landBasedCasinoList) {
        this.landBasedCasinoList = landBasedCasinoList;
    }
}
