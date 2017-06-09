package crud.vaadin;

import crud.backend.Rank;
import java.io.Serializable;

public class UserModifiedEvent implements Serializable {

    private Rank rank;

    public UserModifiedEvent(Rank r) {
        this.rank = r;
    }

    public Rank getRank() {
        return rank;
    }
}
