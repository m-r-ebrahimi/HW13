package school.entity;

import java.util.Objects;
import java.util.Set;

public class CourseStudents {
    private Set<Item> items;

    public CourseStudents(Set<Item> items) {
        this.items = items;
    }

    public CourseStudents() {
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudents that = (CourseStudents) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "CourseStudents{" +
                "items=" + items +
                '}';
    }
}

