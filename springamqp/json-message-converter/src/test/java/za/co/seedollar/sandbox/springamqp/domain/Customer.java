package za.co.seedollar.sandbox.springamqp.domain;

import java.util.Date;

/**
 * Created by seedollar on 7/28/17.
 */
public class Customer {

    private String name;
    private String status;
    private Date joinedDate;

    public Customer() {
    }

    public Customer(String name, String status, Date joinedDate) {

        this.name = name;
        this.status = status;
        this.joinedDate = joinedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
