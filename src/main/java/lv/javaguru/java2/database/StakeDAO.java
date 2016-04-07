package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Stake;

import java.util.List;

/**
 * Created by tyoma17 on 29.03.2016.
 */
public interface StakeDAO {

        void create(Stake stake) throws DBException;

        Stake getByID(Long id) throws DBException;

        void delete(Long id) throws DBException;

        void update(Stake stake) throws DBException;

        List<Stake> getAllStakes(Long id) throws DBException;

}
