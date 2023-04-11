/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onpe.com.pe.gestorconfiguracionactas.core.model;

/**
 *
 * @author CASSHERN
 */
public class Component {

    String _id;
    String id_module;
    String name_Module;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getId_module() {
        return id_module;
    }

    public void setId_module(String id_module) {
        this.id_module = id_module;
    }

    public String getName_Module() {
        return name_Module;
    }

    public void setName_Module(String name_Module) {
        this.name_Module = name_Module;
    }
}
