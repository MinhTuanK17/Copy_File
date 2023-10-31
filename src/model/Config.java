/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MINH TUAN
 */
public class Config {

    private String COPY_FOLDER;
    private String DATA_TYPE;
    private String PATH;

    public Config(String COPY_FOLDER, String DATA_TYPE, String PATH) {
        this.COPY_FOLDER = COPY_FOLDER;
        this.DATA_TYPE = DATA_TYPE;
        this.PATH = PATH;
    }

    public Config() {
    }

    public String getCOPY_FOLDER() {
        return COPY_FOLDER;
    }

    public void setCOPY_FOLDER(String COPY_FOLDER) {
        this.COPY_FOLDER = COPY_FOLDER;
    }

    public String getDATA_TYPE() {
        return DATA_TYPE;
    }

    public void setDATA_TYPE(String DATA_TYPE) {
        this.DATA_TYPE = DATA_TYPE;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

}
