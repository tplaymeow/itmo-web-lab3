package com.tplaymeow.itmoweblab3;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WelcomeBean {
    public String displayResultsPage() {
        return "success";
    }
}
