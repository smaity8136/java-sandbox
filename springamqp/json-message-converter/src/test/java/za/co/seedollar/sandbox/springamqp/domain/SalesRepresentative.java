package za.co.seedollar.sandbox.springamqp.domain;

/**
 * Created by seedollar on 7/28/17.
 */
public class SalesRepresentative {

    private Integer rating;
    private String company;
    private Double salesTarget;

    public SalesRepresentative() {}

    public SalesRepresentative(Integer rating, String company, Double salesTarget) {
        this.rating = rating;
        this.company = company;
        this.salesTarget = salesTarget;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getSalesTarget() {
        return salesTarget;
    }

    public void setSalesTarget(Double salesTarget) {
        this.salesTarget = salesTarget;
    }

    @Override
    public String toString() {
        return "SalesRepresentative{" +
                "rating=" + rating +
                ", company='" + company + '\'' +
                ", salesTarget=" + salesTarget +
                '}';
    }
}
