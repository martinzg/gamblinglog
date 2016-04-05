package lv.javaguru.java2.resources;

import lv.javaguru.java2.domain.LandBasedCasino;

import java.util.List;

public class OfflineGamblingEventData {
    public List<LandBasedCasino> getLandBasedCasinoList() {
        return landBasedCasinoList;
    }

    public void setLandBasedCasinoList(List<LandBasedCasino> landBasedCasinoList) {
        this.landBasedCasinoList = landBasedCasinoList;
    }

    List<LandBasedCasino> landBasedCasinoList;
}
