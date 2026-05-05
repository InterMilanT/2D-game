package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	//SCREEN SETTINGS
	final int originalTileSize = 16 ; 
	final int scale = 3 ;
	
	final int tileSize = originalTileSize * scale ;
	
	final int maxScreenCol = 16 ;
	final int maxScreenRow = 12 ;
	final int screenWidth = tileSize * maxScreenCol ;
	final int screenHeight = tileSize * maxScreenRow ;
	
	KeyHandle KH = new KeyHandle() ;
	
	Thread gameThread ;
	
	// set players default position
	int playerX = 100 ;
	int playerY = 100 ;
	int playerSpeed = 4 ;
	
	public GamePanel() 
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)) ;
		this.setBackground(Color.black) ;
		this.setDoubleBuffered(true) ;
		this.setFocusable(true) ;
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this) ;
		gameThread.start() ;
	}

	@Override
	public void run() 
	{
		while (gameThread != null)
		{
			long currentTime = System.nanoTime() ;
			
			
			update() ;
			
			repaint() ;
		}
	}
	
	public void update()
	{
		if (KH.upPressed == true)
		{
			playerY -= playerSpeed ;
		}
		else
		{
			if (KH.downPressed == true)
			{
				playerY += playerSpeed ;
			}
			else
			{
				if (KH.leftPressed == true)
				{
					playerX -= playerSpeed ;
				}
				else
				{
					if (KH.rightPressed == true)
					{
						playerX += playerSpeed ;
					}
				}
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g) ;
		
		Graphics2D g2 = (Graphics2D)g ;
		
		g2.setColor(Color.white) ;
		
		g2.fillRect(playerX, playerY, tileSize, tileSize) ;
		
		g2.dispose() ;
	}
	
	
}
