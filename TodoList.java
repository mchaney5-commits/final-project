import java.util.ArrayList;

/**
 * Manages a to do list using an ArrayList
 *
 * This class contains all the logic for adding, removing, and editing
 * items in the to do list
 *
 * @author  Mackenzie Chaney
 */
public class TodoList {

    /** The internal storage for all to-do items. */
    private final ArrayList<String> items;

    /**
     * Constructs a new, empty to do list
     */
    public TodoList() {
        items = new ArrayList<>();
    }

    /**
     * Adds a new item to the end of the to do list.
     *
     * @param item the text of the to do item; must not be null or blank
     * @throws IllegalArgumentException if item is null or blank
     */
    public void addItem(String item) {
        if (item == null || item.isBlank()) {
            throw new IllegalArgumentException("Item text should not be null or blank.");
        }
        items.add(item.strip());
    }

    /**
     * Removes the item at the specified position from the to-do list.
     *
     * @param index the zero-based index of the item to remove
     * @throws IndexOutOfBoundsException if {@code index} is out of range
     *         ({@code index < 0 || index >= size()})
     */
    public void removeItem(int index) {
        validateIndex(index);
        items.remove(index);
    }

    /**
     * Replaces the text of an existing item at the specified position.
     *
     * @param index   the zero-based index of the item to edit
     * @param newText the replacement text; must not be null or blank
     * @throws IndexOutOfBoundsException if index is out of range
     * @throws IllegalArgumentException  if new text is null or blank
     */
    public void editItem(int index, String newText) {
        validateIndex(index);
        if (newText == null || newText.isBlank()) {
            throw new IllegalArgumentException("Replacement text should not be null or blank.");
        }
        items.set(index, newText.strip());
    }

    /**
     * Returns a shallow copy of the current list of to do items.
     *
     * @return a new ArrayList containing all current to do items in order
     */
    public ArrayList<String> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Returns the number of items currently in the to do list.
     *
     * @return the item count (never negative)
     */
    public int size() {
        return items.size();
    }

    /** @return true if code is empty, false if not */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Validates that the index is within the bounds of the current list.
     *
     * @param index the index that needs to validate
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of range for list of size %d.", index, items.size()));
        }
    }
}

