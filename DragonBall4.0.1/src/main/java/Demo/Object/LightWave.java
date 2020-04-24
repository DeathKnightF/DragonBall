package Demo.Object;

import java.awt.Graphics;

import Demo.Data.Data;
import Demo.Data.StateEnum;
import Demo.Object.PlayerObject.Player;
import Demo.Util.ReadImageUtil;

public class LightWave extends GameObject{
	private int harm;
	private int time;
	public LightWave(int harm,Player player) {
		//Light ware doesn't disappear when it comes into contact with other object
		state=StateEnum.unbeatable;
		this.harm=harm;
		changePosition(player);
		super.image=ReadImageUtil.laserImg;
		super.y=0;
		super.width=player.width*2;
		this.time=Math.min(Data.SKILL_W_MAX_TIME, player.getUseTime());
		super.alive=true;
	}
	
	public void changePosition(Player player) {
		super.x=player.x-width*2/7;
		super.height=player.y+player.height/2;
	}
	
	public void draw(Player player,Graphics g) {
		if(!alive)
			return;
		if(time--<0)
			alive=false;
		changePosition(player);
		g.drawImage(image, x, y, width, height, null);
	}

	public int getHarm() {
		return harm;
	}

	public synchronized void setHarm(int harm) {
		this.harm = harm;
	}
	
}
