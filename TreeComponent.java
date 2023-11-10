import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TreeComponent extends JComponent {
  
  private final static int ORIGIN_X = 500;
  private final static int ORIGIN_Y = 200;

  private final TreeNode tree;

  public TreeComponent(TreeNode tree) {
    this.tree = tree;
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent ev) {
        int mouseX = ev.getX();
        int mouseY = ev.getY();
        TreeNode nodeWeHit = tree.contains(mouseX, mouseY, ORIGIN_X, ORIGIN_Y);
        System.out.println("You clicked on:");
        nodeWeHit.print();
      }
    });
  }

  public void paintComponent(Graphics g) {
    tree.draw(g, ORIGIN_X, ORIGIN_Y);
  }

}