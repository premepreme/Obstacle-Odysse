package display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import Charactor.*;
import Element.Element;
import event.Event;

import java.util.Random;

public class Game extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	public static int playerSize = 60, waveHeight = 45, coconutHeight = 100, heartHeight = 30, barrierHeight = 30;
	private static int base = 450, xStart = 1000;
	private boolean firstSpaceBarPressed = false; // add status
	/* Count speed */
	private static double count_speed = 0;
	private static int speed = 100; // Initial speed value in pixels per second
	private static boolean earn_buff = false;
	public static boolean hit_sth = false;
	private static boolean is_slide = false;
	private PointManager pointManager;

	private long lastPress = 0;
	private Player player = new Player(100, base - 50, Player.KNIGHT);
	private SoundPlayer soundPlayer = new SoundPlayer();

	static Display display;
	// ------------------Wave Size ----------------------------
	WaveImageFactory waveImageFactory = new WaveImageFactory();
	Wave[] waveSet = makeWave(4, waveImageFactory);

	CoconutImageFactory coconutImageFactory = new CoconutImageFactory();
	Coconut[] coconutSet = makeCoconut(4, coconutImageFactory);

	// ------------------Heart Size ----------------------------
	HeartImageFactory heartImageFactory = new HeartImageFactory();
	Heart[] heartSet = makeHeart(4, heartImageFactory);
	// ------------------Barrier Size ----------------------------
	BarrierImageFactory barrierImageFactory = new BarrierImageFactory();
	Barrier[] barrierSet = makeBarrier(4, barrierImageFactory);
	// --------------------Cloud--------------------------------
	private Environment[] envSet = makeEnv(2, Environment.CLOUD);
	private Environment building = new Environment(xStart - 100, base - 150, this, Environment.BUILDING, 4);
	// --------------------Ghost--------------------------------
	GhostImageFactory ghostImageFactory = new GhostImageFactory();
	Ghost[] ghostSet = makeGhost(3, ghostImageFactory);

	public Game() {
		this.setBounds(0, 0, 1000, 600);
		this.addKeyListener(this);
		this.setLayout(null);
		this.setFocusable(true);
		this.pointManager = PointManager.getInstance();
	}

	@Override
	public void paint(Graphics g) {
		try {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			this.drawBackground(g2);
			// ---POINT----
			g2.setFont(Element.getFont(10));
			g2.setColor(Color.white);
			// speed
			count_speed = Math.floor((pointManager.getPoint() + 100) / 100); //........
			speed = speed - (int) count_speed;
			/* edit from here */
			g2.drawString("SCORE ", 50, 40);
			g2.setColor(Color.decode("#21ef80"));
			g2.drawString(" " + pointManager.getPoint(), 100, 40);
			/* to here */
			// --- player --
			g2.setColor(Color.RED);
			drawPlayerHealth(g2);
			if (!earn_buff && !is_slide) {
				g2.drawImage(player.getImage(0), player.x, player.y + 50, playerSize, playerSize, null); /* edit here */
			}
			if (earn_buff && !is_slide) {
				g2.drawImage(player.getImage(1), player.x, player.y + 50, playerSize, playerSize, null); /* edit here */
			}
			if (is_slide) {
				g2.drawImage(player.getImage(2), player.x, player.y + 80, playerSize, playerSize / 2, null); /* edit here */
			}
			// ----Wave----
			for (Wave item : waveSet) {
				drawWave(item, g2);
			}
			for(Coconut item : coconutSet) {
			drawCoconut(item,g2);
			}
			for(Heart item: heartSet) {
			drawHeart(item, g2);
			}
			for (Barrier item : barrierSet) {
				drawBarrier(item, g2);
			}
			for(Ghost item : ghostSet) {
			drawGhost(item,g2);
			}
			this.pointManager.incrementPoint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drawBackground(Graphics2D g2) throws IOException {
		g2.drawImage(ImageIO.read(new File("img/sky.png")), 0, 0, 2000, 1000, null);
		g2.drawImage(ImageIO.read(new File("img/mist.png")), 0, 170, 1000, 300, null); /* edit here */
		g2.drawImage(building.getImage(), building.x, building.y - 300, 800, 800, null); /* edit here */
		g2.drawImage(ImageIO.read(new File("img/dir.png")), 0, base + 10, 1000, 180, null); /* edit here */
		for (Environment item : envSet) {
			g2.drawImage(item.getImage(), item.x, item.y, 550, 260, null);
		}
	}

	private void drawPlayerHealth(Graphics2D g2) {
		try {
			for (int i = 1; i < player.health + 1; i++) {
				g2.drawImage(ImageIO.read(new File("img/heart.png")), 840 + (i * 25), 22, 23, 20, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Wave[] makeWave(int size, WaveImageFactory waveImageFactory) {
		Wave[] waveSet = new Wave[size];
		int far = 500;
		Random random = new Random();
		int randomNumber = random.nextInt(1501) + 600;
		int start = random.nextInt(1201) + 500;
		for (int i = 0; i < size; i++) {
			waveSet[i] = new Wave(start + far, base, speed, this, waveImageFactory);
			far += randomNumber;
		}
		return waveSet;
	}	


	private Coconut[] makeCoconut(int size, CoconutImageFactory coconutImageFactory) {
		Coconut[] coconutSet = new Coconut[size];
		int far = 1500;
		Random random = new Random();
		int randomNumber = random.nextInt(1501) + 700;
		int start = random.nextInt(2001) + 1000;
		for (int i = 0; i < size; i++) {
			coconutSet[i] = new Coconut(start + far, base, speed, this, coconutImageFactory);
			far += randomNumber;
		}
		return coconutSet;
	}

	private Heart[] makeHeart(int size, HeartImageFactory heartImageFactory) {
		Heart[] heartSet = new Heart[size];
		int far = 2200;
		Random random = new Random();
		int randomNumber = random.nextInt(1501) + 800;
		int start = random.nextInt(2501) + 1500;
		for (int i = 0; i < size; i++) {
			heartSet[i] = new Heart(start + far, base, speed, this, heartImageFactory);
			far += randomNumber;
		}
		return heartSet;
	}

	private Ghost[] makeGhost(int size, GhostImageFactory ghostImageFactory) {
		Ghost[] ghostSet = new Ghost[size];
		int far = 1800;
		Random random = new Random();
		int randomNumber = random.nextInt(1501) + 900;
		int start = random.nextInt(3001) + 1000;
		for (int i = 0; i < size; i++) {
			int y = -(int) (Math.random() * (70 - 20 + 1) + 20); // random y position
			ghostSet[i] = new Ghost(start + far, base + y, speed, this, ghostImageFactory);
			far += randomNumber;
		}
		return ghostSet;
	}

	private Barrier[] makeBarrier(int size, BarrierImageFactory barrierImageFactory) {
		Barrier[] barrierSet = new Barrier[size];
		int far = 2000;
		Random random = new Random();
		int randomNumber = random.nextInt(1501) + 1000;
		int start = 500;
		for (int i = 0; i < size; i++) {
			barrierSet[i] = new Barrier(start + far, base, speed, this, barrierImageFactory);
			far += randomNumber;
		}
		return barrierSet;
	}

	private Environment[] makeEnv(int size, int eType) {
		Environment[] envSet = new Environment[size];
		int far = 0;
		for (int i = 0; i < size; i++) {
			envSet[i] = new Environment(xStart + far, 20, this, eType, 10);
			far += 600;
		}
		return envSet;
	}

	private void drawWave(Wave wave, Graphics2D g2) {
		WaveImage waveImage = WaveImageFactory.getWaveImage("img/mushroom.png");
		g2.drawImage(waveImage.getImage(), wave.x, wave.y, 50, waveHeight + 10, null);
		if (Event.checkHit(player, wave, playerSize, waveHeight) && !earn_buff) {
			g2.setColor(new Color(241, 98, 69)); /* red */
			g2.setStroke(new BasicStroke(20));
			g2.drawRect(0, 0, 1000, 1000);
			soundPlayer.playHitSound();
			player.health -= 1;
			hit_sth = true;
			if (player.health <= 0) {
				display.endGame(pointManager.getPoint());
				earn_buff = false;
				player.health = new Player().health;
				pointManager.setPoint();
			}
		}
	}
	

	private void drawGhost(Ghost ghost, Graphics2D g2) {
		g2.drawImage(ghost.getImage(), ghost.x, (ghost.y), 50, waveHeight + 10, null);
		if (Event.checkHitGhost(player, ghost, playerSize, 20) && !earn_buff) {
			g2.setColor(new Color(241, 98, 69)); /* red */
			g2.setStroke(new BasicStroke(20));
			g2.drawRect(0, 0, 1000, 1000);
			soundPlayer.playHitSound();
			player.health -= 1;
			hit_sth = true;
			if (player.health <= 0) {
				display.endGame(pointManager.getPoint());
				earn_buff = false;
				player.health = new Player().health;
				pointManager.setPoint();
			}
		}
	}

	private void drawHeart(Heart heart, Graphics2D g2) {
		g2.drawImage(heart.getImage(), heart.x, (heart.y - heartHeight + 50), 40, heartHeight + 10, null);
		if (Event.checkHitHeart(player, heart, playerSize, heartHeight)) {
			if (player.health < 5) {
				g2.setColor(new Color(225, 99, 216)); /* pink */
				g2.setStroke(new BasicStroke(20));
				g2.drawRect(0, 0, 1000, 1000);
				soundPlayer.playGetItemSound();
				player.health += 1;
			}
		}
	}

	private void drawBarrier(Barrier barrier, Graphics2D g2) {
		g2.drawImage(barrier.getImage(), barrier.x, (barrier.y - barrierHeight + 50), 40, barrierHeight + 10, null);
		if (hit_sth) {
			hit_sth = false;
		}
		if (hit_sth && earn_buff) {
			earn_buff = false;
			hit_sth = false;
			if (player.health <= 5) {
				player.health += 1;
			}
		}
		if (Event.checkHitBarrier(player, barrier, playerSize, barrierHeight) && !earn_buff) {
			earn_buff = true;
			soundPlayer.playGetItemSound();
			if (earn_buff) {
				g2.setColor(new Color(255, 215, 7)); /*yellow */
				g2.setStroke(new BasicStroke(20));
				g2.drawRect(0, 0, 1000, 1000);
				lastPress = System.currentTimeMillis();
				Timer timer = new Timer(3000, event -> {
				earn_buff = false;
				});
				timer.setRepeats(false);
				timer.start();
				lastPress = System.currentTimeMillis();
			}
		}
	}

	private void drawCoconut(Coconut coconut, Graphics2D g2) {
		g2.drawImage(coconut.getImage(), coconut.x, (coconut.y - coconutHeight), 70, coconutHeight + 54, null);
		if (Event.checkHitCoconut(player, coconut, playerSize, coconutHeight) && !earn_buff) {
			g2.setColor(new Color(241, 98, 69)); /* red */
			g2.setStroke(new BasicStroke(50));
			g2.drawRect(0, 0, 1000, 1000);
			soundPlayer.playHitSound();
			player.health -= 1;
			hit_sth = true;
			if (player.health <= 0) {
				display.endGame(pointManager.getPoint());
				earn_buff = false;
				player.health = new Player().health;
				pointManager.setPoint();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (System.currentTimeMillis() - lastPress > 600) {
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				soundPlayer.playJumpSound();
				if (System.currentTimeMillis() - lastPress > 200) {
					firstSpaceBarPressed = false;
				}
				if (!firstSpaceBarPressed) {
					firstSpaceBarPressed = true;
					player.jump(this);
					lastPress = System.currentTimeMillis();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				is_slide = true;
				soundPlayer.playSlideSound();
				Timer timer = new Timer(450, event -> {
					is_slide = false;
				});
				timer.setRepeats(false);
				timer.start();
				lastPress = System.currentTimeMillis();
			}

		} else {
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				if (firstSpaceBarPressed) {
					soundPlayer.playDoubleJumpSound();
					player.doubleJump(this);
					lastPress = System.currentTimeMillis();
					firstSpaceBarPressed = false;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// ---
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// ---
	}

	public static void main(String[] arg) {
		display = new Display();
	}
}
