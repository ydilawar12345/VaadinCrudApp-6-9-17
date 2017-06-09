package crud.vaadin;

import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import crud.backend.*;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import java.util.List;

@UIScope
@SpringComponent
public class UserForm extends AbstractForm<User> {

private Scorer scorer = new Scorer();
private Ranker ranker = new Ranker();
private static final long serialVersionUID = 1L;

    EventBus.UIEventBus eventBus;
    //UserRepository repo;
    RankRepository repo1;

    TextField name = new MTextField("Enter your name");
    TextField phoneNumber = new MTextField("Enter Yusuf's phone # : ");
    DateField birthDay = new DateField("Enter Yusuf's birthday : ");
    TextField eyeColor = new MTextField("Enter Yusuf's eye colour : ");
    TextField height = new MTextField("Enter Yusuf's height : ");

    UserForm(RankRepository rr, EventBus.UIEventBus b) {
        super(User.class);
        this.repo1 = rr;
        this.eventBus = b;

          setSavedHandler(user -> {
            scorer.scoreHowWellYouKnow(user,repo1);
            List<Rank> q = repo1.findAll();
            q = ranker.theranker(q, (Rank) user,repo1);
            repo1.save(q);
            eventBus.publish(this, new UserModifiedEvent((Rank) user));
        });

        setResetHandler(p -> eventBus.publish(this, new UserModifiedEvent((Rank) p)));
        setSizeUndefined();
    }

    @Override
    protected void bind() {
        // DateField in Vaadin 8 uses LocalDate by default, the backend
        // uses plain old java.util.Date, thus we need a converter, using
        // built in helper here
        getBinder()
                .forMemberField(birthDay)
                .withConverter(new LocalDateToDateConverter());
        super.bind();
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        name,
                        phoneNumber,
                        birthDay,
                        height,
                        eyeColor
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }
}
