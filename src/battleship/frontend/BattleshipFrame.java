package battleship.frontend;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;


public class BattleshipFrame extends JFrame {
    public static final int WIDTH=1200;
    public static final int HEIGHT=500;
    public BattleshipFrame() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      super();
      setSize(WIDTH,HEIGHT);
      setResizable(false);
      setTitle("Battleship");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new FlowLayout());
      getContentPane().setBackground(Color.lightGray);

      add(new GridButtonBoard(PlayingButton.class, ButtonStyle.PLAYER_PLAYING1));
      add(new GridButtonBoard(PlayingButton.class, ButtonStyle.PLAYER_PLAYING2));
      pack();
    }

  public static void main(String[] args) {

  }
  }



