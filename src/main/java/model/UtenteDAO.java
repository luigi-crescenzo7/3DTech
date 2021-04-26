package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    private static Connection connection;

    public UtenteDAO() {
        try {
            connection = ConPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Utente user) {
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE utente SET" +
                                                                   "Email = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utente> doRetrieveAll() {
        List<Utente> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from utente");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utente user = new Utente();
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setPasswordhash(rs.getString("Passwordhash"));
                user.setName(rs.getString("Nome"));
                user.setSurname(rs.getString("Cognome"));
                user.setPhoneNumber(rs.getString("RecapitoTelefonico"));
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

    public void doSave(Utente user) {
        if (user == null) return;

        try {
            PreparedStatement ps = connection.prepareStatement("insert into utente " +
                    "(email, passwordhash, nome, cognome," +
                    " recapitotelefonico, cap, citta, via, admin)" +
                    "values (?,?,?,?,?,?,?,?,?)");

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordhash());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getZIPCode());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getStreet());
            ps.setBoolean(9, user.isAdmin());

            if (ps.executeUpdate() != 1) throw new RuntimeException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
