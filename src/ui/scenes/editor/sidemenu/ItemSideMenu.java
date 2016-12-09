package ui.scenes.editor.sidemenu;

import block.BlockType;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import ui.builder.ComponentProperties;
import ui.scenes.editor.objects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nisa, Pim, Harshil
 *         <p>
 *         This class initializes tab-based UI used to choose ui.scenes.editor.objects to place
 *         on the overworld grid editor.
 */
public class ItemSideMenu extends SideMenu {

    private ItemViewer myViewer;

    private final BlockType [] blockTypes = {BlockType.GROUND, BlockType.DECORATION,
            BlockType.OBSTACLE, BlockType.SWITCH_FLOOR, BlockType.TELEPORT};

    public ItemSideMenu(Parent root, ResourceBundle resources, String resourceName) {
        super(root, resources, resourceName);
        myViewer = new ItemViewer();
        init();
    }


    /**
     * Creates and adds tabs for each object type to the Item Menu
     *
     * @param itemPanel is the Item Menu to which the tabs are added
     */
    public void addTabs() {
        List<Tab> tabs = new ArrayList<>();
        for (BlockType type : blockTypes) {
            Tab tab = createTab(type.name().toLowerCase(), createScrollPane(type));
            tabs.add(tab);
        }
        myPanel.getTabs().addAll(tabs);
    }

    private ScrollPane createScrollPane(BlockType type) {
        FlowPane itemPane = getFlowPane();
        List<GameObject> list = myViewer.getObjects(type);
        for (GameObject object : list) {
            String path = object.getIconPath();
            ImageView icon = (ImageView) myBuilder.addNewImageView(itemPane, new ComponentProperties()
                    .path(path)
                    .width(myUtil.getIntProperty("itemWidth"))
                    .preserveRatio(true)
                    .id("game-object"));
            object.setIcon(icon);
            icon.setOnMouseClicked(e -> {
                if (myViewer.getSelected() != null) {
                    myViewer.getSelected().getIcon().setStyle("-fx-effect: none");
                }
                myViewer.select(object);
                object.getIcon().setStyle("-fx-effect: dropshadow(gaussian, black, 8, 0.0, 2, 0);");
            });
        }
        return new ScrollPane(itemPane);
    }

    public GameObject getSelected() {
        return myViewer.getSelected();
    }
}