package bugtracker;

import javafx.beans.property.*;

public class Issue {
    
    private final StringProperty title;
    private final StringProperty description;
    private final ObjectProperty<Priority> priority;

    public Issue(String title, String description, Priority priority) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.priority = new SimpleObjectProperty<>(priority);
    }

    public StringProperty titleProperty() { return title; }

    public StringProperty descriptionProperty() { return description; }

    public ObjectProperty<Priority> priorityProperty() { return priority; }
}