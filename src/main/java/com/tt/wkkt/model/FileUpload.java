package com.tt.wkkt.model;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class FileUpload {
    private int id;
    private String fileName;

    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "FileUpload{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
