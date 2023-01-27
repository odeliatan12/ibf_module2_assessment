package nus.iss.module2.assessment.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderDetails implements Serializable {
    
    private String id;

    // @NotNull(message = "Order cannot be empty")
    // private String pizza;

    // @NotNull(message = "Please choose a size")
    // private String size;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be more than 3 characters")
    private String name;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Phone number cannot be empty")
    @Size(min = 8, max = 8, message = "Phone number must be 8 digit.")
    private String phone;

    private boolean rush = false;
    private String comments;
    // private Integer quantity;

    // private Integer totalCost;
    

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    // public String getPizza() {
    //     return pizza;
    // }
    // public void setPizza(String pizza) {
    //     this.pizza = pizza;
    // }
    // public String getSize() {
    //     return size;
    // }
    // public void setSize(String size) {
    //     this.size = size;
    // }
    // public Integer getTotalCost() {
    //     return totalCost;
    // }
    // public void setTotalCost() {
    //     this.totalCost = Integer.parseInt(this.pizza) * Integer.parseInt(this.size) * this.quantity;
    // }
    public OrderDetails() {
        this.id = this.generateId(8);
    }

    public OrderDetails(String name, String address, String phone, String comments){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }


    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    // public Integer getQuantity() {
    //     return quantity;
    // }
    // public void setQuantity(Integer quantity) {
    //     this.quantity = quantity;
    // }
    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numChars);
    }
    
    // building Json object 
    public JsonObject toJson(){
        return Json.createObjectBuilder()
                    .add("orderId", this.getId())
                    .add("name", this.getAddress())
                    .add("phone", this.getPhone())
                    .add("rush", this.rush)
                    .add("comments", this.getComments())
                    // .add("pizza", this.getSize())
                    // .add("quantity", this.getQuantity())
                    //.add("total", this.getTotalCost())
                    .build();
    }

    public static OrderDetails create(String json) throws IOException {
        OrderDetails m = new OrderDetails();
        if(json != null)
            try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
                JsonReader r = Json.createReader(is);
                JsonObject o = r.readObject();
                m.setName(o.getString("name"));
                m.setId(o.getString("id"));
                m.setPhone(o.getString("phone"));
            
            }
        return m;
    }

    
    
}
