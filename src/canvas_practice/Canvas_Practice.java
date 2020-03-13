package canvas_practice;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.font.FontDesignMetrics;

public class Canvas_Practice  extends JComponent implements  KeyListener{

   static int c=0;
    
    private static final Color m_tRed = new Color(255, 0, 0, 150);

  private static Color m_tGreen = new Color(0, 255, 0, 150);

  private static Color m_tBlue = new Color(0, 0, 255, 150);
 static int width_of_character=27;
  static int height_of_character=46;
  
   static  Font monoFont = new Font("Monospaced", Font.BOLD
      | Font.ITALIC, 40);
  
 static Canvas_Practice cp;
 static ArrayList<Object>char_list=new ArrayList<>();
 static Image image_custom=Toolkit.getDefaultToolkit().getImage("C:\\Users\\champion\\Desktop\\emoji_5.png");
 static Glyph glyph;
 static SystemFacade systemFacade;
 static CustomCharacterFlyweightFactory characterFlyweightFactory;
static Image bimage;
 static JLabel count_Words;
 static Integer fontSize=40;
 static String fontFace="monospaced";
    public Canvas_Practice() {
        
       
        FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(monoFont);
        width_of_character=fm.stringWidth("C");
        height_of_character=fm.getAscent();
        glyph=new RowComposite();
        characterFlyweightFactory=CustomCharacterFlyweightFactory.getInstance();
        systemFacade=new SystemFacade();
    }

    
  static Canvas_Practice getInstanceOf(){
      
     return cp;
 }
 
  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
                    glyph.draw(g, 0);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
   return new Dimension(1600, 600);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(500, 500); 
    }
    
    public boolean containsCharacter(String s) {
    return (s == null) ? false : s.matches("[^A-Za-z0-9!@#$%^&*(),.?\":{}|<> ]");
}
    

    
   private void Character_Pressed(java.awt.event.KeyEvent evt) {                                   
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE){
           systemFacade.removeComponent(glyph);
        }
        else{
              char ch=evt.getKeyChar();
               if(!containsCharacter(String.valueOf(ch))){
                    System.out.println("THis is Character so type it now");
                     systemFacade.insertComponent(glyph, ch,characterFlyweightFactory);
                }
        }
        System.out.println("Character Pressed : "+evt.getKeyChar()+"   Key Code = " +evt.getExtendedKeyCode());
        cp.repaint();       
    }
    public static void main(String[] args) throws ClassNotFoundException {
        
       try {
 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
           JFrame jtf=new JFrame("LEXI");
           jtf.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
           
           cp=new Canvas_Practice();
           cp.addKeyListener(cp);
           cp.setFocusable(true);
           cp.setBorder(BorderFactory.createLineBorder(m_tBlue, 5));
           JPanel panel2=new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        jtf.getContentPane().add(cp);
           JButton fontChange=new JButton("Font");
           fontChange.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   
                     JFrame f3 = new JFrame();
        JLabel size = new JLabel("Enter Size");
        JTextField size1 = new JTextField();
       f3.setBounds(200,200,300,300);
       JButton set = new JButton("Set");
        
       size.setBounds(0,0,100,50);
        size1.setBounds(0,50,100,50);
        set.setBounds(0,100,100,50);
        f3.add(size);
        f3.add(size1);
       f3.add(set);
       f3.setLayout(null);
       f3.setResizable(false);
        f3.setVisible(true); 
                 set.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             if(!"".equals(size1.getText())){
                                  fontSize=Integer.parseInt(size1.getText());
                             System.out.println("Font SIze: "+fontSize);
                            systemFacade.changeFontSize(fontSize);
                            f3.dispose();
                            cp.repaint();
                            cp.grabFocus();
                             }
                             
                         }
                     });
        
                   
                   cp.repaint();
                   cp.grabFocus();
               }
           });
           
           
           JButton fontface=new JButton("Font Face");
           fontface.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   JFrame f3=new JFrame("Font Styles");
                   JLabel style=new JLabel("Select font Name");
                   JButton btn=new JButton("Set Font");
                    String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                   JComboBox  jComboBox=new JComboBox(fonts);
                   style.setBounds(0,0,150,50);
                   f3.setBounds(400,400,400,400);
                   jComboBox.setBounds(0,50,200,50);
                   btn.setBounds(0, 200, 100,30);
                   f3.add(style);
                   f3.add(jComboBox);
                   f3.add(btn);
                   btn.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           
                            fontFace=jComboBox.getSelectedItem().toString();
                            if(fontFace!=null){
                                 systemFacade.changeFontFace(fontFace);
                           cp.repaint();
                           cp.grabFocus();
                            }
                          
                       }
                   });
               }
           });
           JButton undo=new JButton("Undo");
           undo.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   systemFacade.undoTask(glyph);
                   cp.repaint();
                   cp.grabFocus();
               }
           });
           
           JButton redo=new JButton("Redo");
           redo.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   systemFacade.redo();
                   
                   cp.repaint();
                   cp.grabFocus();
               }
           });
           
           JButton countGlyph=new JButton("Count Words");
           countGlyph.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   systemFacade.countChracters(glyph);
                   cp.repaint();
                   cp.grabFocus();
               }
           });
           
           count_Words=new JLabel();
           count_Words.setBorder(BorderFactory.createLineBorder(Color.RED));
           count_Words.setBounds(new Rectangle(10,10));
           
           
           JButton changeBorder=new JButton("Border");
           changeBorder.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   
                   Color c1=JColorChooser.showDialog(cp, "Select the Color of the Border", m_tBlue);
                   if(c1!=null){
                       systemFacade.changeBorder(cp,c1);
                   }
                   
                   cp.repaint();
                   cp.grabFocus();
               }
           });
           
           JButton insertImage=new JButton("Insert Image");
           insertImage.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    Image bimage = ImageIO.read(file);
                    
                    systemFacade.insertImage(glyph, bimage);
                     cp.repaint();
                   cp.grabFocus();
                } catch (IOException ex) {
                }
                                   } 
               }
           });

                panel2.add(fontChange);
                panel2.add(fontface);
                panel2.add(undo);
                panel2.add(redo);
                panel2.add(insertImage);
                panel2.add(changeBorder);
                panel2.add(countGlyph);
                panel2.add(count_Words);
          jtf.getContentPane().add(panel2);
                
           jtf.setVisible(true);
           jtf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           jtf.pack();
       } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
           Logger.getLogger(Canvas_Practice.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
Character_Pressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
