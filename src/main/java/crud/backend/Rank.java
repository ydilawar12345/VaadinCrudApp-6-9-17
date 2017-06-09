package crud.backend;

import javax.persistence.*;

/**
 * Created by user on 6/7/2017.
 */
@Entity
@DiscriminatorValue("rank")
public class Rank extends User {

    public Rank (){}

   @Column()
    private Integer rank = 0;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
