package shared_model;

public class Supplier {
        private String id;
    private String name;
    private String email;
    private String phone;
    private String itemIDs;

    public Supplier(String id, String name, String email, String phone, String itemIDs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.itemIDs = itemIDs;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getItemIDs() { return itemIDs; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setItemIDs(String itemIDs) { this.itemIDs = itemIDs; }

 
    public String toString() {
        return id + "|" + name + "|" + email + "|" + phone + "|" + itemIDs;
    }
    
}