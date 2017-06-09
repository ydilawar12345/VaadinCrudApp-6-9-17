package crud.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import crud.backend.Rank;
import crud.backend.RankRepository;
import crud.backend.User;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button;

@Title("Yusuf's CRUD APP")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

    private SpringViewProvider viewProvider;

     RankRepository repo1;
     UserForm userForm;
     EventBus.UIEventBus eventBus;

    private String heading = ("Yusuf\'s Quiz") ;

    private MTextField filterByName = new MTextField()
            .withPlaceholder("Filter by name");

    private Button addNew = new MButton(VaadinIcons.PLUS, "Add Entry", this::add);

    private Button edit = new MButton(VaadinIcons.PENCIL,"Edit Entry" ,this::edit);

    private Button view = new MButton(VaadinIcons.EYE, "View Entry",this::view);

    private Button delete = new ConfirmButton(VaadinIcons.TRASH,"Delete Entry",
            "Are you sure you want to delete this entry?", this::remove);

    private MGrid<Rank> list = new MGrid<>(Rank.class)
            .withProperties("id", "name", "score", "rank")
            .withColumnHeaders("User id", "User Name","Score", "Rank")
            .withFullWidth();

        public MainUI(UserForm f, EventBus.UIEventBus b, RankRepository rr) {  //public MainUI(UserRepository r, UserForm f, EventBus.UIEventBus b, RankRepository rr) {
        this.repo1 = rr;
        this.userForm = f;
        this.eventBus = b;
    }

   @Override
    protected void init(VaadinRequest request) { // this was public
        setContent(
        new MVerticalLayout(
                new MHorizontalLayout(new Label(heading)),
                new MHorizontalLayout(filterByName, addNew,view, edit, delete),
                        list
                ).expand(list)
        );
        listEntities();

        list.asSingleSelect().addValueChangeListener(e -> adjustActionButtonState());
        filterByName.addValueChangeListener(e -> {
            listEntities(e.getValue());
        });
       eventBus.subscribe(this);
    }

    protected void adjustActionButtonState() {
        boolean hasSelection = !list.getSelectedItems().isEmpty();
        edit.setEnabled(hasSelection);
        delete.setEnabled(hasSelection);
        view.setEnabled(hasSelection);
    }

    private void listEntities() {
        listEntities(filterByName.getValue());
    }
    final int PAGESIZE = 45;
    private void listEntities(String nameFilter) {
        String likeFilter = "%" + nameFilter + "%";
        list.setRows(repo1.findByNameLikeIgnoreCase(likeFilter));
        adjustActionButtonState();
    }

    public void add(ClickEvent a) {
        edit(new Rank());
    }

    public void edit(ClickEvent e) {
        edit(list.asSingleSelect().getValue());
    }

    public void view(ClickEvent v) {view(list.asSingleSelect().getValue()); }

    public void remove() {
        repo1.delete(list.asSingleSelect().getValue());
        list.deselectAll();
        listEntities();
    }

    protected void edit(User Entry) { // protected void edit(final User Entry) {
        userForm.setEntity(Entry);
        userForm.openInModalPopup();
    }

    protected void view(User Entry) { // protected void edit(final User Entry) {
       // userForm.setEntity(Entry);
         userForm.setEntity(Entry);
         userForm.openInModalPopup();

    }

    @EventBusListenerMethod(scope = EventScope.UI)
    public void onUserModified(UserModifiedEvent event) {
        listEntities();
        userForm.closePopup();
    }
}
