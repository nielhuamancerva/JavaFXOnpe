
package com.mycompany.loging.score.util;

import com.mycompany.loging.score.util.constanst.VariableGlobales;
import javafx.scene.image.Image;

/**
 *
 * @author CASSHERN
 */
public class CreateObject {
    public static Image image(String pathOfFile){
        Image imageCreate = new Image(pathOfFile);
        return imageCreate;
    }
}
