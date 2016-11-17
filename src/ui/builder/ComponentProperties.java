package ui.builder;

/**
 * @author Harshil Garg, Robert Steilberg
 *
 * This class holds information used to build JavaFX objects.
 */
public class ComponentProperties {

    protected String id;
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected String text;
	protected String path;
    protected boolean preserveRatio;
    protected String font;
    protected int size;


	public ComponentProperties(double x, double y) {
		this.x = x;
		this.y = y;
	}

    public ComponentProperties id(String id) {
        this.id = id;
        return this;
    }

	public ComponentProperties width(double width) {
		this.width = width;
		return this;
	}
	
	public ComponentProperties height(double height) {
		this.height = height;
		return this;
	}
	
	public ComponentProperties text(String text) {
		this.text = text;
		return this;
	}

    public ComponentProperties path(String path) {
        this.path = path;
        return this;
    }

    public ComponentProperties preserveRatio(boolean preserveRatio) {
        this.preserveRatio = preserveRatio;
        return this;
    }

    public ComponentProperties font(String font) {
        this.font = font;
        return this;
    }

    public ComponentProperties size(int size) {
        this.size = size;
        return this;
    }
}