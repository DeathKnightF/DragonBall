package Demo.Object;

import java.awt.Image;
import java.awt.Rectangle;

import Demo.Data.StateEnum;

public class GameObject {
	protected int x;//position
	protected int y;
	protected int width;
	protected int height;
	protected int speed;
	protected int frozen_speed;
	protected int blood;
	protected int max_blood;
	protected int exp;
	protected int kind;
	protected int form;
	protected Image image;
	protected Image[] images;//the pictures show in the GameFrame
	protected int duration;//the duration of states  
	protected double degree;
	protected boolean alive;
	protected boolean isBoss;
	protected StateEnum state;
	public int getX() {
		return x;
	}

	public GameObject() {
	}
		
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public synchronized void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFrozen_speed() {
		return frozen_speed;
	}

	public void setFrozen_speed(int frozen_speed) {
		this.frozen_speed = frozen_speed;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getMax_blood() {
		return max_blood;
	}

	public void setMax_blood(int max_blood) {
		this.max_blood = max_blood;
	}

	public int getExp() {
		return exp;
	}

	public synchronized void setExp(int exp) {
		this.exp = exp;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image[] getImages() {
		return images;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean live) {
		this.alive = live;
	}

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

}
