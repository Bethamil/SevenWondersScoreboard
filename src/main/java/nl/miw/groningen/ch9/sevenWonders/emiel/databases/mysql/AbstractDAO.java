package nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Centraliseert gedeelde database bewerkingen
 * @author remideboer, gerke de boer, Michael Oosterhout
 */
public abstract class AbstractDAO {

    protected DBAccess dbAccess;
    protected PreparedStatement preparedStatement;

    public AbstractDAO(DBAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    /**
     * Maakt een preparedStatement voor de sql string. Een DAO gebruikt dit om de parameters te vullen.
     *
     * @param sql,
     *            de SQl query
     */
    protected void setupPreparedStatement(String sql) throws SQLException {
        preparedStatement = dbAccess.getConnection().prepareStatement(sql);
    }

    /**
     * Voert de preparedStatement uit zonder een ResultSet. Wordt gebruikt voor insert, update en
     * delete statements.
     *
     */
    protected void executeManipulateStatement() throws SQLException {
        preparedStatement.executeUpdate();
    }

    /**
     * Voert de preparedStatement uit met een ResultSet. Wordt gebruikt voor select statements.
     *
     */
    protected ResultSet executeSelectStatement() throws SQLException {
        return preparedStatement.executeQuery();
    }

    /**
     * Maakt een preparedStatement voor de sql string, die een gegenereerde sleutel terug moet geven.
     * @param sql,
     *            de SQL query
     */
    protected void setupPreparedStatementWithKey(String sql) throws SQLException {
        preparedStatement = dbAccess.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    /**
     * Voert de prepared statement uit en geeft de gegenereerde sleutel terug.
     * Wordt gebruikt voor een insert in een AutoIncrement tabel
     */
    protected int executeInsertStatementWithKey() throws SQLException {
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int gegenereerdeSleutel = 0;
        while (resultSet.next()) {
            gegenereerdeSleutel = resultSet.getInt(1);
        }
        return gegenereerdeSleutel;
    }
}
