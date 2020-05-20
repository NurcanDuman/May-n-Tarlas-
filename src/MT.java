import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MT implements MouseListener{
    
    JFrame frame;
    JButton reset = new JButton("Reset");
    Btn[][] board = new Btn[10][10];
    int openButton;
    JTextField zaman=new JTextField("Süre");
    JPanel panel = new JPanel();
    //Stopwatch sw;
    JMenu oyun = new JMenu("Oyun");
    JMenuItem restart = new JMenuItem("Reset");
    JMenuBar menubar = new JMenuBar();
    public MT(){
        openButton = 0;
        frame = new JFrame("Mayın Tarlası");
        JOptionPane.showMessageDialog(frame, "Oyunu oynarken mayın olduğunu tahmin ettiğin yerler sağ tıkla bayrak eklemeyi unutma. Kolay gelsin :)");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ico/mayin.png"));
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
         
       
       
        frame.setLayout(new GridLayout(10,10));
         frame.setBackground(Color.BLACK);
        frame.setJMenuBar(menubar);
        menubar.add(oyun);
         oyun.add(restart);
        //Panel ayarları
       
        panel.setLayout(new BorderLayout());
       // panel.add(reset);
      // panel.add(zaman, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
       
       
        for (int satir = 0; satir < board.length; satir++) {
            for (int sutun = 0; sutun < board[0].length; sutun++) {
                 Btn b = new Btn(satir,sutun);
                 frame.add(b);
                 b.addMouseListener(this);
                 board[satir][sutun]=b;
            }
        }
        
        mayinTuret();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocationRelativeTo(null);
        updateCount();
        //print();
        
        frame.setVisible(true);  
    }
    
    public void mayinTuret(){
        int i=0;
        while(i<10){
            int randomSatir = (int) (Math.random() * board.length);
            int randomSutun = (int) (Math.random() * board[0].length);
            
            while(board[randomSatir][randomSutun].isMine()){
                randomSatir = (int) (Math.random() * board.length);
                randomSutun = (int) (Math.random() * board[0].length);
                
            }
            board[randomSatir][randomSutun].setMine(true);
            i++;
            
        }
    }
    public void print() {
        for (int satir = 0; satir < board.length; satir++) {
            for (int sutun = 0; sutun < board[0].length; sutun++) {
                 if(board[satir][sutun].isMine()){
                     board[satir][sutun].setIcon(new ImageIcon("src/icon/mayin.png"));
                 }else{
                     board[satir][sutun].setText(board[satir][sutun].getCount()+"");
                     board[satir][sutun].setEnabled(false);
                 }
            }
        }
    }
    public void printMine(){
        for (int satir = 0; satir < board.length; satir++) {
            for (int sutun = 0; sutun < board[0].length; sutun++) {
                 if(board[satir][sutun].isMine()){
                    board[satir][sutun].setIcon(new ImageIcon("src/icon/mayin.png"));
                 }
            }
        }
    }
    
    public void updateCount(){
        for (int satir = 0; satir < board.length; satir++) {
            for (int sutun = 0; sutun < board[0].length; sutun++) {
                 if (board[satir][sutun].isMine()) {
                     counting(satir, sutun);
                }
            }
        }
    }

    public void counting(int satir,int sutun){
        for (int i = satir - 1; i <= satir + 1; i++) {
            for (int k = sutun - 1; k <= sutun + 1; k++) {
                try {
                    int value = board[i][k].getCount();
                    board[i][k].setCount(++value);
                }catch(Exception e){
                    
                }
            }
        }
    }
    
    public void open(int r,int c){
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c].getText().length() > 0 || board[r][c].isEnabled() == false ) {
            return;
        }else if (board[r][c].getCount()!=0){
            board[r][c].setText(board[r][c].getCount()+"");
            board[r][c].setEnabled(false);
            openButton++;
        }else{
            board[r][c].setEnabled(false);
            openButton++;
            open(r-1,c);
            open(r+1,c);
            open(r,c-1);
            open(r,c+1);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Btn b = (Btn) e.getComponent();
        
        if(e.getButton() == 1){
            if (b.isMine()) {
                print();
                JOptionPane.showMessageDialog(frame,"Mayına Bastınız GAME OVER!!!");
            }else {
                open(b.getRow(),b.getCol());
                if(openButton == (board.length * board[0].length)-10){
                    JOptionPane.showMessageDialog(frame,"Güzel bir oyundu kazandın");
                    print();
                }
            }
        }else if(e.getButton() == 3){
            if(!b.isFlag()){
                b.setIcon(new ImageIcon("src/icon/flag.jpg"));
                b.setFlag(true);
            }
            else{
                b.setIcon(null);
                b.setFlag(false);
            }
        }
        
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    private void setIconImage(Image image) {
        //To change body of generated methods, choose Tools | Templates.
    }

}
    

