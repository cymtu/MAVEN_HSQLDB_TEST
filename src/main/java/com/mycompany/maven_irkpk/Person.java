package com.mycompany.maven_irkpk;

public class Person  implements java.io.Serializable {

    private int id;
    private String name;
    private String address;
    private String notation;

    public Person() {
    }
    
    public Person(String name, String address, String notation) {
       this.name = name;
       this.address = address;
       this.notation = notation;
    }
   
    public int getId() {
        return this.id;
    }
    
    protected void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNotation() {
        return this.notation;
    }
    
    public void setNotation(String notation) {
        this.notation = notation;
    }
}


