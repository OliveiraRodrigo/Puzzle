package puzzle;

import java.awt.Color;
import java.awt.Dimension;
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
    
    private BufferStrategy bs;
    private Color[][] matrix;
    //private Puzzle sPuzzle;
    
    public Screen(Puzzle inPuzzle) {
        
        //sPuzzle = inPuzzle.clonePuzzle();
        addKeyListener(this);
        this.setTitle("Slide Puzzle");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setExtendedState(Jogo.MAXIMIZED_BOTH); //frame maximizado
        this.setSize(500,500);
        this.setLocation(440, 0);
        //this.setMaximumSize(new Dimension(533,735));
        //this.setMinimumSize(new Dimension(371,371));
        this.setVisible(true);
    //    this.initialize();
        setIgnoreRepaint(true);
    //}
    //
    ///** Inicializa a tela */
    //public final void initialize() {
        createBufferStrategy(2);
        bs = getBufferStrategy();
        //render();
    }

    /** Configura e atualiza o desenho dos objetos na tela */
    public void render(Puzzle qPuzzle) {
        int a=54;//54

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        
        /** Grade do jogo */
        int k=0;
        for(int i=0; i<qPuzzle.getNumRows(); i++) {
            for(int j=0; j<qPuzzle.getNumCols(); j++) {

                g.setColor(qPuzzle.showPuzzle()[i][j]);
                g.fill3DRect((j*100)+100,(i*100)+100, 100, 100, true);
                g.setColor(Color.WHITE);
                if(qPuzzle.puzzle[i][j] != 0){
                    g.drawString(Integer.toString(
                        qPuzzle.puzzle[i][j]),
                        (j*100)+150, (i*100)+150);
                }
                g.setColor(Color.darkGray);
                g.draw3DRect((j*100)+100,(i*100)+100, 100, 100, true);
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
