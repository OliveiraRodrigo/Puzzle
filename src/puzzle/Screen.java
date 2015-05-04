package puzzle;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
/**
 *
 * @author Rodrigo
 */
public class Screen extends JFrame implements
                                       KeyListener,
                                       MouseListener {
    
    private final BufferStrategy bs;
    protected boolean next;
    protected boolean[] buttons;
    //private boolean breadthButton;
    //private boolean depthButton;
    private int bdBtnX, bdBtnY, dpBtnX, dpBtnY, temp;
    
    public Screen(int rows, int cols) {
        
        addKeyListener(this);
        this.addMouseListener(this);
        this.setTitle("Slide Puzzle");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        //this.setExtendedState(Jogo.MAXIMIZED_BOTH); //frame maximizado
        this.setSize(100*(1 + 2*cols)+124,100*(1 + rows));
        this.setLocation(920-2*cols*100, 100);
        //this.setMaximumSize(new Dimension(533,735));
        //this.setMinimumSize(new Dimension(371,371));
        this.setVisible(true);
        setIgnoreRepaint(true);
        next = !false;
        buttons = new boolean[2];
        buttons[0] = false; //breadthButton 
        buttons[1] = false; //depthButton
        //temp = 0;
        
        createBufferStrategy(2);
        bs = getBufferStrategy();
    }

    public boolean[] render(int counter, Puzzle initPuzzle, Puzzle currPuzzle, Color pColor) {
        
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        
        Color numberColor;
        if(currPuzzle.isSorted()){
            numberColor = Color.BLACK;
        }
        else{
            numberColor = Color.BLACK;
        }
        
        for(int i=0; i<currPuzzle.getNumRows(); i++) {
            for(int j=0; j<currPuzzle.getNumCols(); j++) {
                
                g.setColor(initPuzzle.showPuzzle(Color.gray)[i][j]);
                g.fill3DRect((j*100)+51,(i*100)+63, 100, 100, true);
                g.draw3DRect((j*100)+56,(i*100)+68, 90, 90, true);
                g.setColor(numberColor);
                if(initPuzzle.puzzle[i][j] != 0){
                    g.drawString(Integer.toString(
                        initPuzzle.puzzle[i][j]),
                        (j*100)+98, (i*100)+117);
                }
                else{
                    g.setColor(Color.white);
                    g.drawString("Inicial", (j*100)+82, (i*100)+117);
                }
                g.setColor(Color.lightGray);
                g.draw3DRect((j*100)+51,(i*100)+63, 100, 100, true);
                
                g.setColor(currPuzzle.showPuzzle(pColor)[i][j]);
                g.fill3DRect((j*100)+171+100*initPuzzle.getNumCols(),(i*100)+63, 100, 100, true);
                g.draw3DRect((j*100)+176+100*initPuzzle.getNumCols(),(i*100)+68, 90, 90, true);
                g.setColor(numberColor);
                if(currPuzzle.puzzle[i][j] != 0){
                    g.drawString(Integer.toString(
                        currPuzzle.puzzle[i][j]),
                        (j*100)+218+100*initPuzzle.getNumCols(), (i*100)+117);
                }
                else{
                    g.setColor(Color.CYAN);
                    g.drawString("Atual", (j*100)+204+100*initPuzzle.getNumCols(), (i*100)+117);
                }
                g.setColor(Color.lightGray);
                g.draw3DRect((j*100)+171+100*initPuzzle.getNumCols(),(i*100)+63, 100, 100, true);
            }
        }
        
        bdBtnX = 70+100*currPuzzle.getNumCols();
        bdBtnY = 68;
        g.setColor(Color.PINK);
        g.fill3DRect(bdBtnX, bdBtnY, 85, 40, !buttons[0]);
        if(!buttons[0])
            g.setColor(Color.BLACK);
        else
            g.setColor(Color.YELLOW);
        g.drawString("Largura", bdBtnX+22, bdBtnY+25);
        
        dpBtnX = 70+100*currPuzzle.getNumCols();
        dpBtnY = 118;
        g.setColor(Color.MAGENTA);
        g.fill3DRect(dpBtnX, dpBtnY, 85, 40, !buttons[1]);
        if(!buttons[1])
            g.setColor(Color.BLACK);
        else
            g.setColor(Color.CYAN);
        g.drawString("Profundidade", dpBtnX+6, dpBtnY+24);
        
        g.setColor(Color.white);
        g.fill3DRect(dpBtnX, dpBtnY+100, 85, 40, false);
        g.setColor(Color.RED);
        if(counter>=0){
            g.drawString(Integer.toString(counter), (int)((double)dpBtnX+36-Math.ceil(Math.log10((double)counter+1))), dpBtnY+124);
            temp = counter;
        }
        else{
            g.drawString(Integer.toString(temp), (int)((double)dpBtnX+36-Math.ceil(Math.log10((double)temp+1))), dpBtnY+124);
        }
        
        if(buttons[1])
            g.drawString("Erro!", dpBtnX+30, dpBtnY+74);
        
        g.dispose();
        bs.show();
        
        return buttons;
    }
    
    @Override
    public void keyPressed(KeyEvent evt) {

        switch(evt.getKeyCode()) {

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        if(x>=bdBtnX && x<=bdBtnX+85 &&
           y>=bdBtnY && y<=bdBtnY+40){
            if(buttons[1])
                buttons[1] = false;
            buttons[0] = !buttons[0];
        }
        if(x>=dpBtnX && x<=dpBtnX+85 &&
           y>=dpBtnY && y<=dpBtnY+40){
            if(buttons[0])
                buttons[0] = false;
            buttons[1] = !buttons[1];
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
