package model.Utente;

import model.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public void doUpdateById(int userId) {
        String query = "";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //todo: implementazione lato dashboard
    public List<Utente> doRetrieveAll() {
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from utente as u where u.admin  " +
                     "<> 1")) {
            List<Utente> list = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utente user = new Utente();
                user.setId(rs.getInt("id_utente"));
                user.setEmail(rs.getString("Email"));
                user.setName(rs.getString("Nome"));
                user.setDataNascita(rs.getDate("data_di_nascita"));
                user.setSurname(rs.getString("Cognome"));
                user.setPhoneNumber(rs.getString("telefono"));
                user.setZIPCode(rs.getString("CAP"));
                user.setCity(rs.getString("Citta"));
                user.setStreet(rs.getString("Via"));
                user.setAdmin(rs.getBoolean("admin"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveEmailPassword(Utente user) {
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM utente" +
                     " WHERE email = ? AND passwordHash = ?")) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordhash());
            ResultSet set = ps.executeQuery();

            if (set.next())
                return UtenteConstructor.constructUser(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void doSave(Utente user) {
        if (user == null) return;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into utente " +
                     "(email, passwordhash, nome, cognome, data_di_nascita," +
                     " telefono, cap, citta, via, admin)" +
                     "values (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordhash());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setDate(5, user.getDataNascita());
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getZIPCode());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getStreet());
            ps.setBoolean(10, user.isAdmin());

            if (ps.executeUpdate() != 1) throw new RuntimeException();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}