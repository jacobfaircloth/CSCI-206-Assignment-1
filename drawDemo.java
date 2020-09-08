import javax.swing.*;  
import java.awt.event.*;
import java.awt.*;

public class drawDemo extends JPanel implements KeyListener, ActionListener  //classname must match filename exactly
{
    private String lastKeyPressed = "Have Fun!";   //instance variable

    Timer tm = new Timer(5, this);
    int x = 0, y = 0, velX = 0, velY = 0;
  
    public static void main(String[] args) //The method that is called when the java interpreter "Runs" a class file.
    {
        drawDemo theApp = new drawDemo(); //instantiating an object of the DrawDemo class
        JFrame frame = new JFrame(); //make a new frame/window on the screen
        frame.setVisible(true); //a new frame is not visible by default
        frame.setSize(500, 500); //sets the frame to a static size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the X box behavior
        frame.add(theApp); //adds theApp instance to the frame
        frame.addKeyListener(theApp); //adds a key listener to receive key events
        frame.requestFocus(); //makes the frame recieve the key events
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
        
    }

    public drawDemo()
    
    {
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);       //constructor this gets called when you instantiate the class (call new)
    }

    @Override //tells the compiler the we are overriding the parent method on purpose
    protected void paintComponent(Graphics g)  //this method gets called whenever the compoennt needs to be updated
    {
        super.paintComponent(g); //take care of all the jobs that the parent does
        g.clearRect(0,0,this.getHeight(), this.getWidth());  //clear the screen before we paint on it
        g.setColor(Color.blue); 
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawString(lastKeyPressed, 10, 10);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (x < 0)
        {
            velX = 0;
            x = 0;
        }
        if (x > 410)
        {
            velX = 0;
            x = 410;
        }
        if (y < 0)
        {
            velY = 0;
            y = 0;
        }
        if (y> 410)
        {
            velY = 0;
            y = 410;
        }

        x = x + velX;   
        y = y + velY;
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) //gets called on a keypress event
    {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT)
        {
            velX = -1;
            velY = 0;
        }
        if (c == KeyEvent.VK_UP)
        {
            velX = 0;
            velY = -1;
        }
        if (c == KeyEvent.VK_RIGHT)
        {
            velX = 1;
            velY = 0;
        }
        if (c == KeyEvent.VK_DOWN)
        {
            velX = 0;
            velY = 1;
        }
        lastKeyPressed = "key code: "+e.getKeyCode();  //this is the keycode from the event
        System.out.println(e.getKeyCode());
        repaint(); //triggers the call to paintComponent
    }

    @Override
    public void keyTyped(KeyEvent e) {} //all interface methods must exist
    @Override
    public void keyReleased(KeyEvent e) 
    {
        velX = 0;
        velY = 0;
    } //all interface methods must exist
}