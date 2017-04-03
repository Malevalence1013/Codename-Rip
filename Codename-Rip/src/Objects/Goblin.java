package Objects;

import java.awt.Graphics2D;

import Graphics.SpriteSheet;
import Input.InputHandler;

public class Goblin extends GameObject {
	public static final int MAX_LEFT_TRAVEL = 40, MAX_RIGHT_TRAVEL = 340;

	private int accelY = 1, frame = 0, dir = 0;
	private boolean onGround = true, move = false;

	public Goblin(int x, int y, ObjectID id) {
		super(x, y, id);

		velX = 0;
		velY = 0;
	}

	public void update() {
		move = false;
		velX = 0;

		if (y >= 320) {
			onGround = true;
		}

		//if (InputHandler.keys[InputHandler.A]) this.moveRight();
		//if (InputHandler.keys[InputHandler.D]) this.moveLeft();
		//if (InputHandler.keys[InputHandler.SPACE]) this.jump();

		if (move) {
			frame++;
		} else {
			frame = 0;
		}

		if (!onGround) {
			y += velY;
			velY += accelY;
		}

		x += velX;
	}

	public void render(Graphics2D g) {
		if (!onGround) {
			if (dir == 0) {
				g.drawImage(SpriteSheet.playerOne[3][0], x, y - 64, null);
				g.drawImage(SpriteSheet.playerOne[3][1], x, y, null);
			}
			else if (dir == 1) {
				g.drawImage(SpriteSheet.playerTwo[3][0], x, y  - 64, null);
				g.drawImage(SpriteSheet.playerTwo[3][1], x, y, null);
			}
		} else {
			int stepFrame = 2 + (frame / 4) % 2;
			
			if (dir == 0) {
				g.drawImage(SpriteSheet.playerOne[stepFrame][0], x, y - 64, null);
				g.drawImage(SpriteSheet.playerOne[stepFrame][1], x, y, null);
			}
			else if (dir == 1) {
				g.drawImage(SpriteSheet.playerTwo[stepFrame][0], x, y - 64, null);
				g.drawImage(SpriteSheet.playerTwo[stepFrame][1], x, y, null);
			}
		}
	}

	private void moveLeft() {
		move = true;
		dir = 1;

		if (x == MAX_LEFT_TRAVEL) {
			velX = 0;
		} else {
			velX = -4;
		}
	}

	private void moveRight() {
		move = true;
		dir = 0;

		if (x == MAX_RIGHT_TRAVEL) {
			velX = 0;
		} else {
			velX = 4;
		}
	}

	private void jump() {
		if (onGround) {
			velY = -10;

			onGround = false;
		}
	}
}