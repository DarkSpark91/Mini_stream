package com.stream.mini.mini_stream.database;


import com.stream.mini.mini_stream.requests.Form;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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

    public void signupUser(Form form){
        String query = "INSERT INTO user ( username, password  ) VALUES ( ?, ?);";
        jdbcTemplate.update(query, new String[]{form.getUname(), hash(form.getPassword())});
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
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
