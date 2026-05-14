package PipesInTheDesert.GUI.View;

import PipesInTheDesert.Elements.MapObject;
import PipesInTheDesert.Connectors.Pipe;
import PipesInTheDesert.Elements.Pump;
import PipesInTheDesert.Players.Player;
import PipesInTheDesert.Players.Plumber;
import PipesInTheDesert.Players.Saboteur;
import PipesInTheDesert.Constants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

/**
 * InfoPanel displays detailed information about the currently selected element.
 * When a user clicks an element on the map, GameController calls displayElementInfo()
 * and this panel shows the element's type, ID, position, and state-specific properties.
 */
public class InfoPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JPanel statusArea;

    public InfoPanel() {
        setBorder(BorderFactory.createTitledBorder("Element Details"));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 400));
        // Top section for name and type
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        nameLabel = new JLabel("—");
        typeLabel = new JLabel("—");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(nameLabel);
        topPanel.add(typeLabel);
        add(topPanel, BorderLayout.NORTH);
        // Center section for dynamic content
        statusArea = new JPanel();
        statusArea.setLayout(new BoxLayout(statusArea, BoxLayout.Y_AXIS));
        add(new JScrollPane(statusArea), BorderLayout.CENTER);
        // Show empty state initially
        clear();
    }
    /**
     * Displays detailed information about the given element.
     *
     * @param element the MapObject to display information for (Pipe, Pump, Plumber, or Saboteur)
     */
    public void displayElementInfo(MapObject element) {
        if (element == null) {
            clear();
            return;
        }

        statusArea.removeAll();
        statusArea.setLayout(new GridLayout(0, 2, 10, 5));

        if (element instanceof Pipe) {
            Pipe pipe = (Pipe) element;
            nameLabel.setText("ID: pipe" + pipe.getId());
            typeLabel.setText("Type: Pipe");
            addInfoRow("Position:", formatPosition(pipe));
            addInfoRow("Leaking:", pipe.isLeaking() ? "Yes 🔴" : "No");
            addInfoRow("Flowing:", pipe.isWaterFlowing() ? "Yes 🟢" : "No");
            addInfoRow("Water Amount:", pipe.getWaterAmount() + " units");
            Player occupant = pipe.getOccupant();
            addInfoRow("Occupant:", occupant != null ? getPlayerName(occupant) : "None");
        }
        else if (element instanceof Pump) {
            Pump pump = (Pump) element;
            nameLabel.setText("ID: pump" + pump.getId());
            typeLabel.setText("Type: Pump");
            addInfoRow("Position:", formatPosition(pump));
            addInfoRow("Health:", pump.isHealthy() ? "Healthy 🟢" : "Broken 🔴");
            addInfoRow("Water Tank:", pump.getWaterTankLevel() + " units");
            addInfoRow("Input:", pump.getInputPipe() != null ? "Connected" : "None");
            addInfoRow("Output:", pump.getOutputPipe() != null ? "Connected" : "None");
            addInfoRow("Occupants:", pump.getOccupants().size() + " player(s)");
        }
        else if (element instanceof Plumber) {
            Plumber plumber = (Plumber) element;
            nameLabel.setText("Name: Plumber" + plumber.getId());
            typeLabel.setText("Type: Plumber");
            addInfoRow("Position:", formatPosition(plumber));
            addInfoRow("Team:", "Plumbers 🔵");
            addInfoRow("Stamina:", plumber.getStamina() + " / " + Constants.PLAYER_MAX_STAMINA);
            addInfoRow("Holding Pump:", plumber.isHoldingPump() ? "Yes" : "No");
        }
        else if (element instanceof Saboteur) {
            Saboteur saboteur = (Saboteur) element;
            nameLabel.setText("Name: Saboteur" + saboteur.getId());
            typeLabel.setText("Type: Saboteur");
            addInfoRow("Position:", formatPosition(saboteur));
            addInfoRow("Team:", "Saboteurs 🔴");
            addInfoRow("Stamina:", saboteur.getStamina() + " / " + Constants.PLAYER_MAX_STAMINA);
        }
        else {
            nameLabel.setText("ID: unknown");
            typeLabel.setText("Type: Unknown");
            addInfoRow("Position:", formatPosition(element));
        }

        statusArea.revalidate();
        statusArea.repaint();
    }

    /**
     * Resets the InfoPanel to empty state showing placeholder message.
     */
    public void clear() {
        statusArea.removeAll();
        JLabel emptyLabel = new JLabel("Click on an element to see details");
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusArea.add(emptyLabel);
        nameLabel.setText("—");
        typeLabel.setText("—");
        statusArea.revalidate();
        statusArea.repaint();
    }

    /**
     * Helper method to add a name-value row to statusArea.
     */
    private void addInfoRow(String label, String value){
        JLabel labelField = new JLabel(label);
        labelField.setFont(new Font("Arial", Font.BOLD, 11));
        JLabel valueField = new JLabel(value);
        statusArea.add(labelField);
        statusArea.add(valueField);
    }

    /**
     * Helper method to format position from MapObject coordinates.
     */
    private String formatPosition(MapObject element) {
        return "(" + (int) element.getX() + ", " + (int) element.getY() + ")";
    }
    /**
     * Helper method to get player name from Player object.
     */
    private String getPlayerName(Player player){
        if (player instanceof Plumber) {
            return "Plumber" + ((Plumber) player).getId();
        }
        if (player instanceof Saboteur) {
            return "Saboteur" + ((Saboteur) player).getId();
        }
        return "Player";
    }
}