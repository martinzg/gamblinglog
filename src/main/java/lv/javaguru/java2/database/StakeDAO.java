package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Stake;

import java.util.List;

/**
 * Created by tyoma17 on 29.03.2016.
 */
public interface StakeDAO extends GenericDAO<Stake> {

        List<Stake> getAllStakes(Long id);

}
