package com.stream.mini.mini_stream.database;


import com.stream.mini.mini_stream.requests.Form;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DatabaseController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public boolean validateLogin(Form form){
        String query = "SELECT * FROM user where username=?;";
        Form store = jdbcTemplate.query(query, new String[] {form.getUname()}, new RowMapper<Form>() {
            @Override
            public Form mapRow(ResultSet resultSet, int i) throws SQLException {
                Form f = new Form();
                f.setUname(resultSet.getString("username"));
                f.setPassword(resultSet.getString("password"));
                return f;
            }
        }).get(0);
        form.setPassword(hash(form.getPassword()));
        return form.equals(store);
    }

    public boolean signupUser(Form form){
        String query = "INSERT INTO user ( username, password  ) VALUES ( ?, ?);";
        try {
            jdbcTemplate.update(query, new String[]{form.getUname(), hash(form.getPassword())});
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    public String hash(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return bytesToHex(md.digest(message.getBytes()));
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for(byte b: bytes) {
            result.append(String.format("%02x", b&0xFF));
        }
        return result.toString();
    }
}
