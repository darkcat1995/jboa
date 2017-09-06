package com.jbit.jboa.entity;

public class Dictionary implements java.io.Serializable {

    private static final long serialVersionUID = 3746048852882818114L;

    private Long id;
    private String type;
    private String item;
    private String value;

    /** default constructor */
    public Dictionary() {
    }

    /** full constructor */
    public Dictionary(String type, String item, String value) {
        this.type = type;
        this.item = item;
        this.value = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}