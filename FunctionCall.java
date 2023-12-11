import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;


public class FunctionCall extends TreeNode {

  /**
   *
   */
  private String label;
 private int size;
  /**
   *
   */
  private ArrayList<TreeNode> children;

  /**
   * @param label
   * @param nodes
   */
  public FunctionCall(String label, int size, TreeNode... nodes) {
    this.label = label;
    this.size = size;
    TreeComponent.setCounter();
    children = new ArrayList<TreeNode>(Arrays.asList(nodes));
  }

  /**
   * @return
   */
  public String getLabel() {
    return label;
  }
  /**
   * @return
   */
  @Override
  public int getSize() {
      return size;
    }
  /**
   * @param size
   */
  public void setSize(int size) {
      this.size = size;
    }
  /**
   * @return
   */
  public ArrayList<TreeNode> getChildren() {
    return children;
  }

  @Override
  public void print() {
    System.out.print(this.getLabel());
    System.out.print("(");
    for (int i = 0; i < this.getChildren().size(); i++) {
      TreeNode child = children.get(i);
      child.print();
      if (i < this.getChildren().size() - 1) {
        System.out.print(",");
      }
    }
    System.out.print(")");
  }

  @Override
    public void draw(Graphics g, int x, int y) {
    g.drawRect(x, y, 100, size*55-5);
    g.drawString(this.getLabel(), x+20, y+40);
    for (int i = 0; i < this.getChildren().size(); i++) {
      TreeNode child = children.get(i);
      if (child instanceof FunctionCall){
       child.draw(g, x - 105, y - child.getSize()*55+110*i);
      } else if (child instanceof Constant){
        child.draw(g, x - 55, y + 55 * i);
      }
    }
  }

  @Override
  public TreeNode contains(int mouseX, int mouseY, int x, int y) {
    if (mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 100) {
      return this;
    } else {
      for (int i = 0; i < this.getChildren().size(); i++) {
        TreeNode child = children.get(i);
        TreeNode found = child.contains(mouseX, mouseY, x - 100, y + 100 * i);
        if (found != null) {
          return found;
        }
      }
    }
    return null;
  }

}
