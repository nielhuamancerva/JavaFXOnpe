/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loging.score.util;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

/**
 *
 * @author RDeLaCruz
 */
public class DropShadowE {

    public void setTabEffect(Button button) {
        button.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                button.setStyle(null);

            }
        });
        button.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.TAB) {
                button.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 10, 0.5, 0, 0);");

            }
        });
    }

}
