package ui.scenes.editor;

import editor.EditorController;
import editor.SidePanel;
import ui.GridPane;
import ui.GridPaneNode;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import java.util.ResourceBundle;


/**
 * @author Teddy Franceschi, Robert Steilberg
 *         <p>
 *         This class initializes the grid-based UI used to create the overworld.
 */
public class GridUI {

    private ResourceBundle myResources;
    private GridPane myGridPane;
    private UIBuilder myBuilder;
    private Parent myRoot;
    private SidePanel myItemMenu;
    private EditorController control;

    GridUI (Parent root, SidePanel itemMenu, ResourceBundle resources) {
        myRoot = root;
        myItemMenu = itemMenu;
        myResources = resources;
        myBuilder = new UIBuilder();
        control = new EditorController();
    }

    /**
     * Configures grid event handlers that allow the user to add and remove
     * objects from it.
     */
    private void initGridControl () {
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
        PropertiesUtilities util = new PropertiesUtilities();
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(util.getDoubleProperty(myResources, "buttonHoverOpacity"));
        int updateX = util.getIntProperty(myResources, "updateX");
        int updateY = util.getIntProperty(myResources, "updateY");
        int updateWidth = util.getIntProperty(myResources, "updateWidth");
        int widthInputX = util.getIntProperty(myResources, "inputWidthX");
        int widthInputY = util.getIntProperty(myResources, "inputWidthY");
        int widthInputWidth = util.getIntProperty(myResources, "inputWidthWidth");
        String widthInputText = myResources.getString("inputWidthText");
        int heightInputX = util.getIntProperty(myResources, "inputHeightX");
        int heightInputY = util.getIntProperty(myResources, "inputHeightY");
        int heightInputWidth = util.getIntProperty(myResources, "inputHeightWidth");
        String heightInputText = myResources.getString("inputHeightText");
        int swapX = util.getIntProperty(myResources, "swapX");
        int swapY = util.getIntProperty(myResources, "swapY");
        int swapWidth = util.getIntProperty(myResources, "swapWidth");
        String swapPath = myResources.getString("swapPath");
        Node widthInputField =
                myBuilder.addCustomTextField(myRoot, widthInputText, widthInputX, widthInputY,
                                             widthInputWidth);
        Node heightInputField =
                myBuilder.addCustomTextField(myRoot, heightInputText, heightInputX, heightInputY,
                                             heightInputWidth);
        String updatePath = myResources.getString("updatePath");
        Node updateButton =
                myBuilder.addCustomButton(myRoot, updatePath, updateX, updateY, updateWidth);
        updateButton.setOnMouseClicked(e -> {
            TextField xText = (TextField) widthInputField;
            TextField yText = (TextField) heightInputField;
            try{
                int xInput = Integer.parseInt(xText.getText());
                int yInput = Integer.parseInt(yText.getText());
                myBuilder.removeComponent(myRoot, myGridPane.getGroup());
                myGridPane.resizeReset(xInput, yInput);
                myBuilder.addComponent(myRoot, myGridPane.getGroup());               
            }
            catch(Exception exc){
                myBuilder.addNewAlert("Invalid Resize", "Please enter an inter value for row and column count");
            }
                   
        });
        updateButton.setOnMouseEntered(e -> updateButton.setEffect(hoverOpacity));
        updateButton.setOnMouseExited(e -> updateButton.setEffect(null));
        Node swapButton = myBuilder.addCustomButton(myRoot, swapPath, swapX, swapY, swapWidth);
        swapButton.setOnMouseClicked(e -> myGridPane.swap(myItemMenu.getHandler().getSelected(),
                                                          control));
        swapButton.setOnMouseEntered(e -> swapButton.setEffect(hoverOpacity));
        swapButton.setOnMouseExited(e -> swapButton.setEffect(null));
    }

    /**
     * Creates a grid of specified width and height, and then adds
     * functionality to the grid
     */
    public void initGrid (int gridWidth, int gridHeight) {
        PropertiesUtilities util = new PropertiesUtilities();
        myGridPane = new GridPane(
                                  gridWidth,
                                  gridHeight,
                                  util.getIntProperty(myResources, "gridWidth"),
                                  util.getIntProperty(myResources, "gridHeight"),
                                  util.getIntProperty(myResources, "gridX"),
                                  util.getIntProperty(myResources, "gridY"));
        control.addGrid(gridHeight, gridWidth);
        initGridControl();
    }

    /**
     * Creates a grid and then adds functionality to it
     */
    public void initGrid () {
        PropertiesUtilities util = new PropertiesUtilities();
        myGridPane = new GridPane(
                                  util.getIntProperty(myResources, "gridCellsWide"),
                                  util.getIntProperty(myResources, "gridCellsHeight"),
                                  util.getIntProperty(myResources, "gridWidth"),
                                  util.getIntProperty(myResources, "gridHeight"),
                                  util.getIntProperty(myResources, "gridX"),
                                  util.getIntProperty(myResources, "gridY"));
        initGridControl();
    }

}
