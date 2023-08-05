package api.freelance.entities;

import java.util.Objects;

public class JobData {
    int id;
    String title;
    String description;
    int price;
    String user;
    int noOfComments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getNoOfComments() {
        return noOfComments;
    }

    public void setNoOfComments(int noOfComments) {
        this.noOfComments = noOfComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobData jobData = (JobData) o;
        return id == jobData.id && price == jobData.price && noOfComments == jobData.noOfComments && Objects.equals(title, jobData.title) && Objects.equals(description, jobData.description) && Objects.equals(user, jobData.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, user, noOfComments);
    }
}
