package puzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
/**
 *
 * @author Rodrigo
 */
public class Screen extends JFrame implements KeyListener {
    
    private BufferStrategy bs;
    private Color[][] matrix;
    protected boolean next;
    //private Puzzle sPuzzle;
    
    public Screen(int rows, int cols) {
        
        //sPuzzle = inPuzzle.clonePuzzle();
        addKeyListener(this);
        this.setTitle("Slide Puzzle");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setExtendedState(Jogo.MAXIMIZED_BOTH); //frame maximizado
        this.setSize(100*(1 + cols),100*(1 + rows));
        this.setLocation(800-cols*100, 100);
        //this.setMaximumSize(new Dimension(533,735));
        //this.setMinimumSize(new Dimension(371,371));
        this.setVisible(true);
    //    this.initialize();
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

    /** Configura e atualiza o desenho dos objetos na tela */
    public void render(Puzzle qPuzzle, Color pColor) {
        int a=54;//54

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        
        /** Grade do jogo */
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

            case KeyEvent.VK_SHIFT:
                //visual = !visual;
                break;
            case KeyEvent.VK_NUMPAD0:
                //desenhaGrade = !desenhaGrade;
                break;
            case KeyEvent.VK_ENTER:
                //novo = true;
                this.setVisible(false);
                break;
            case KeyEvent.VK_CONTROL:
                //jogo1.pausado = !jogo1.pausado;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(1);
                break;
            case KeyEvent.VK_LEFT:
                //jogo1.formaAtual.praEsquerda();
                //jogo1.praLeft = true;
                break;
            case KeyEvent.VK_UP:
                //jogo1.formaAtual.gira();
                //jogo1.girou = true;
                break;
            case KeyEvent.VK_RIGHT:
                //jogo1.formaAtual.praDireita();
                //jogo1.praRight = true;
                break;
            case KeyEvent.VK_DOWN:
                //jogo1.formaAtual.praBaixo();
                break;
            case KeyEvent.VK_SPACE:
                //jogo1.usaTrunfo();
                next = true;
                break;
            case KeyEvent.VK_T:
                //jogo1.trollar();
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
