package dao.spring.impl;

import dao.jdbc.connectionsJDBC.DBConnectionPool;
import dao.spring.DaoNewspaperSpring;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import utils.QueryStrings;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoNewspaperSpringImpl implements DaoNewspaperSpring {

    private final DBConnectionPool pool;

    @Inject
    public DaoNewspaperSpringImpl(DBConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Either<Integer, List<Newspaper>> findNewspapers() {
        Either<Integer, List<Newspaper>> result;
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            List<Newspaper> newspaperList = jtm.query(QueryStrings.GET_ALL_NEWSPAPERS, BeanPropertyRowMapper.newInstance(Newspaper.class));
            result = Either.right(newspaperList);
        } catch (Exception e) {
            e.printStackTrace();
            result = Either.left(-1);
        }
        return result;
    }

    @Override
    public void addNewspaper(Newspaper newspaper) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            NamedParameterJdbcTemplate jdbcTemplate =  new NamedParameterJdbcTemplate(jtm.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(newspaper);

            jdbcTemplate.update(QueryStrings.ADD_NEWSPAPER, namedParameters);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNewspaper(Newspaper newspaper) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(pool.getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);

        try {
            JdbcTemplate jtm = new JdbcTemplate(transactionManager.getDataSource());
            jtm.update(QueryStrings.DELETE_ARTICLES_BY_NEWSPAPER, newspaper.getId());
            jtm.update(QueryStrings.DELETE_NEWSPAPER, newspaper.getId());
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            e.printStackTrace();
        }
    }

    @Override
    public void updateNewspaper(Newspaper newspaper) {
        try{
            Date date = Date.valueOf(newspaper.getRelease_date());
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            jtm.update(QueryStrings.UPDATE_NEWSPAPER, newspaper.getName_newspaper(), date, newspaper.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

