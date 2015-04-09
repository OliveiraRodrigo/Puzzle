package puzzle;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
/**
 *
 * @author Rodrigo
 */
public class Screen extends JFrame implements KeyListener {
    
    private final BufferStrategy bs;
    protected boolean next;
    
    public Screen(int rows, int cols) {
        
        addKeyListener(this);
        this.setTitle("Slide Puzzle");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setExtendedState(Jogo.MAXIMIZED_BOTH); //frame maximizado
        this.setSize(100*(1 + cols),100*(1 + rows));
        this.setLocation(800-cols*100, 100);
        //this.setMaximumSize(new Dimension(533,735));
        //this.setMinimumSize(new Dimension(371,371));
        this.setVisible(true);
        //this.initialize();
        setIgnoreRepaint(true);
        next = false;
    //}
    //
    ///** Inicializa a tela */
    //public final void initialize() {
        createBufferStrategy(2);
        bs = getBufferStrategy();
        //render();
    }

    public void render(Puzzle qPuzzle, Color pColor) {

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        
        Color numberColor;
        if(qPuzzle.isSorted()){
            numberColor = Color.BLACK;
        }
        else{
            numberColor = Color.BLACK;
        }
        for(int i=0; i<qPuzzle.getNumRows(); i++) {
            for(int j=0; j<qPuzzle.getNumCols(); j++) {

                g.setColor(qPuzzle.showPuzzle(pColor)[i][j]);
                g.fill3DRect((j*100)+51,(i*100)+63, 100, 100, true);
                g.draw3DRect((j*100)+56,(i*100)+68, 90, 90, true);
                g.setColor(numberColor);
                if(qPuzzle.puzzle[i][j] != 0){
                    g.drawString(Integer.toString(
                        qPuzzle.puzzle[i][j]),
                        (j*100)+98, (i*100)+117);
                }
                g.setColor(Color.lightGray);
                g.draw3DRect((j*100)+51,(i*100)+63, 100, 100, true);
            }
        }
        
        g.dispose();
        bs.show();
    }
    
    @Override
    public void keyPressed(KeyEvent evt) {

        switch(evt.getKeyCode()) {

            case KeyEvent.VK_ESCAPE:
                System.exit(1);
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_SPACE:
                next = !next;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
