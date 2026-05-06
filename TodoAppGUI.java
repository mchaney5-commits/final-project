import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Swing GUI for the To do list app
 * Keeps the interface separate from TodoList class.
 *
 * @author Mackenzie Chaney
 */
public class TodoAppGUI extends JFrame implements ActionListener {

    /** Manages the to do list data. */
    private TodoList todoList;

    /** Displays the current to do items. */
    private JTextArea displayArea;

    /** Input field for new and or edited items */
    private JTextField inputField;

    /** Button to add a new item. */
    private JButton addButton;

    /** Button to remove an item based on the number given */
    private JButton removeButton;

    /** Button to edit an item based on the number given */
    private JButton editButton;

    /** Creates the GUI window and readies the components */
    public TodoAppGUI() {
        todoList = new TodoList();

        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        addButton    = new JButton("Add");
        removeButton = new JButton("Remove");
        editButton   = new JButton("Edit");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton   .addActionListener(this);
        removeButton.addActionListener(this);
        editButton  .addActionListener(this);

        setVisible(true);
    }

    /**
     * Handles button click events for Add, Remove, and Edit.
     *
     * @param e is the event that is triggered when a button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String text = inputField.getText();
            if (!text.isEmpty()) {
                todoList.addItem(text);
                inputField.setText("");
                updateDisplay();
            }

        } else if (e.getSource() == removeButton) {
            String input = JOptionPane.showInputDialog("Enter item number to remove: ");
            if (input != null) {
                int index = Integer.parseInt(input) - 1;
                todoList.removeItem(index);
                updateDisplay();
            }

        } else if (e.getSource() == editButton) {
            String numInput = JOptionPane.showInputDialog("Enter item number to edit: ");
            if (numInput != null) {
                int index = Integer.parseInt(numInput) - 1;
                String newText = JOptionPane.showInputDialog("Enter new text: ");
                if (newText != null) {
                    todoList.editItem(index, newText);
                    updateDisplay();
                }
            }
        }
    }

    /**
     * Refreshes the text to show the full list of stuff
     */
    private void updateDisplay() {
        displayArea.setText("");
        ArrayList<String> items = todoList.getItems();
        for (int i = 0; i < items.size(); i++) {
            displayArea.append((i + 1) + ". " + items.get(i) + "\n");
        }
    }

    /** Program starting place */
    public static void main(String[] args) {
        new TodoAppGUI();
    }
}
