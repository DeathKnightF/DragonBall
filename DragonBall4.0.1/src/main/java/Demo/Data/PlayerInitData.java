package Demo.Data;

import java.awt.Image;

import Demo.Util.ReadImageUtil;

/**
 * 这个类定义了飞机的初始参数
 * 图片、血量、伤害、速度等等信息
 *
 */
public class PlayerInitData {
	/**
	 * 在选择战机页面显示的图片
	 */
	private Image img;
	/**
	 * 初始血量
	 */
	private int blood;
	/**
	 * 初始伤害
	 */
	private int harm;
	/**
	 * 初始速度
	 */
	private int speed;
	/**
	 * 一共有4种飞机，用kind区分
	 */
	private int kind;
	/**
	 * 升级时，血量的增量
	 */
	private int upgradeBlood;
	/**
	 * 升级时，速度的增量
	 */
	private int upgradeSpeed;
	/**
	 * 升级时，伤害的增量
	 */
	private int upgradeHarm;
	/**
	 * 4种飞机的初始化信息
	 */
	private static PlayerInitData[] playerPlanes;
	static {
		/*
		 * initialize all kinds of plane
		 */
		playerPlanes=new PlayerInitData[4];
		playerPlanes[0]=new PlayerInitData(Data.PLAYER_BLOOD/2+45 ,//95
				Data.PLAYER_SHELL_HARM,//1
				Data.PLAYER_SPEED+3, //18
				Data.PLAYER_UPGRADE_BLOOD,//50
				Data.PLAYER_UPGRADE_SPEED,//1
				Data.PLAYER_UPGRADE_HARM,//0
				0);
		playerPlanes[1]=new PlayerInitData(Data.PLAYER_BLOOD*2,//200 
				Data.PLAYER_SHELL_HARM,//1
				Data.PLAYER_SPEED-2, //13
				Data.PLAYER_UPGRADE_BLOOD*2,//100
				Data.PLAYER_UPGRADE_SPEED,//1
				Data.PLAYER_UPGRADE_HARM,//0
				1);
		playerPlanes[2]=new PlayerInitData(Data.PLAYER_BLOOD/2,//50 
				Data.PLAYER_SHELL_HARM*2,//2
				Data.PLAYER_SPEED,//15 
				Data.PLAYER_UPGRADE_BLOOD-20,//30
				Data.PLAYER_UPGRADE_SPEED,//1
				Data.PLAYER_UPGRADE_HARM+1,//1
				2);
		playerPlanes[3]=new PlayerInitData(Data.PLAYER_BLOOD/2+20,//70 
				Data.PLAYER_SHELL_HARM,//1
				Data.PLAYER_SPEED*2-2, //28
				Data.PLAYER_UPGRADE_BLOOD-10,//40
				Data.PLAYER_UPGRADE_SPEED*2,//2
				Data.PLAYER_UPGRADE_HARM,//0
				3);
	}
	private PlayerInitData(int blood,int harm,int speed,
			int upgradeBlood,int upgradeSpeed,int upgradeHarm,int kind){
		this.blood=blood;
		this.harm=harm;  
		this.speed=speed;
		this.kind=kind;
		this.upgradeBlood=upgradeBlood;
		this.upgradeHarm=upgradeHarm;
		this.upgradeSpeed=upgradeSpeed;
		this.img=ReadImageUtil.getPlayerPlaneImgs(kind)[3];
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public int getHarm() {
		return harm;
	}
	public void setHarm(int harm) {
		this.harm = harm;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getUpgradeBlood() {
		return upgradeBlood;
	}
	public void setUpgradeBlood(int upgradeBlood) {
		this.upgradeBlood = upgradeBlood;
	}
	public int getUpgradeSpeed() {
		return upgradeSpeed;
	}
	public void setUpgradeSpeed(int upgradeSpeed) {
		this.upgradeSpeed = upgradeSpeed;
	}
	public int getUpgradeHarm() {
		return upgradeHarm;
	}
	public void setUpgradeHarm(int upgradeHarm) {
		this.upgradeHarm = upgradeHarm;
	}
	public static PlayerInitData[] getPlayerPlanes() {
		return playerPlanes;
	}
	public static void setPlayerPlanes(PlayerInitData[] playerPlanes) {
		PlayerInitData.playerPlanes = playerPlanes;
	}
}
