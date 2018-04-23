/*
 * Clase que permite especificar un Icono para un Thing o Robot determinado.
 */
package poo.lab2.uber.modelo;
import becker.robots.icons.Icon;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bicon  extends Icon
{
   private String icono;
   public Bicon(String icono) {
    this.icono = icono;
   }
    
  public void paintIcon(Graphics g)
  {
    try
    {
      BufferedImage bi = ImageIO.read(new File(this.icono));
      g.drawImage(bi, 20, 30, 84, 78, 0, 0, 38, 23, null);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
