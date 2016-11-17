package ui.builder;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         This class is used to build JavaFX objects and add them to the stage.
 *         <p>
 *         Dependencies: ComponentBuilder, ButtonBuilder, ImageViewBuilder, LabelBuilder
 */
public class UIBuilder {

    private ComponentBuilder buttonBuilder;
    private ComponentBuilder imageViewBuilder;
    private ComponentBuilder labelBuilder;

    public UIBuilder() {
        buttonBuilder = new ButtonBuilder();
        imageViewBuilder = new ImageViewBuilder();
        labelBuilder = new LabelBuilder();
    }

    /**
     * Adds a JavaFX node to the specified Group or Pane
     *
     * @param layout    is the Group or Pane to which the node will be added
     * @param component is the JavaFX node to be added
     * @return the added node
     */
    public Node addComponent(Parent layout, Node component) {
        if (layout instanceof Pane) {
            Pane pane = (Pane) layout;
            pane.getChildren().add(component);
        } else if (layout instanceof Group) {
            Group pane = (Group) layout;
            pane.getChildren().add(component);
        } else {
            return null;
        }
        return component;
    }

    /**
     * Removes a JavaFX node from the specified Group or Pane
     *
     * @param layout    is the Group or Pane from which the node will be removed
     * @param component is the JavaFX node to be removed
     * @return the removed node
     */
    private Node removeComponent(Parent layout, Node component) {
        if (layout instanceof Pane) {
            Pane pane = (Pane) layout;
            pane.getChildren().remove(component);
        } else if (layout instanceof Group) {
            Group pane = (Group) layout;
            pane.getChildren().remove(component);
        } else {
            return null;
        }
        return component;
    }

    /**
     * Create a new JavaFX Button and adds it to the given Group or Pane
     * @param layout the Group or Pane to which the Button will be added
     * @param properties the ComponentProperties object containing information
     *                   about the Button
     * @return the newly added Button as a node
     */
    public Node addNewButton(Parent layout, ComponentProperties properties) {
        return addComponent(layout, buttonBuilder.createComponent(properties));
    }

    /**
     * Create a new JavaFX ImageView and adds it to the given Group or Pane
     * @param layout the Group or Pane to which the ImageView will be added
     * @param properties the ComponentProperties object containing information
     *                   about the ImageView
     * @return the newly added ImageView as a node
     */
    public Node addNewImageView(Parent layout, ComponentProperties properties) {
        return addComponent(layout, imageViewBuilder.createComponent(properties));
    }

    /**
     * Create a new JavaFX Label and adds it to the given Group or Pane
     * @param layout the Group or Pane to which the Label will be added
     * @param properties the ComponentProperties object containing information
     *                   about the Label
     * @return the newly added Label as a node
     */
    public Node addNewLabel(Parent layout, ComponentProperties properties) {
        return addComponent(layout, labelBuilder.createComponent(properties));
    }

    /**
     * Initializes a JavaFX window with the specified stage and parameters given
     * in a properties file
     *
     * @param currStage          the JavaFX stage with which the window is created
     * @param propertiesFilePath the path to the properties file containing the
     *                           window's parameters
     */
    public void initWindow(Stage currStage, String propertiesFilePath) {
        ResourceBundle resources = ResourceBundle.getBundle(propertiesFilePath);
        currStage.setHeight(Integer.parseInt(resources.getString("windowHeight")));
        currStage.setWidth(Integer.parseInt(resources.getString("windowWidth")));
        currStage.centerOnScreen();
        currStage.show();
    }
}