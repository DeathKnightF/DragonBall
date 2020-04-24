package Demo.Object;

import java.awt.Graphics;


import Demo.Data.Data;
import Demo.Object.PlayerObject.Player;
import Demo.Util.ReadImageUtil;

public class Prop extends GameObject{
	Player player;
	public Prop(int x,int y,int kind,Player player) {
		super.x=x;
		super.y=y;
		super.width=Data.PROP_WIDTH;
		super.height=Data.PROP_HEIGHT;
		super.speed=Data.PROP_SPEED;
		super.degree=Math.random()*Math.PI*3/4+Math.PI/4;
		super.kind=kind;
		super.image=ReadImageUtil.propImgs.get(kind);
		this.player=player;
		super.alive=true;
	}
	
	public void use() {
		switch(kind) {
			case 0://增加血量
				player.blood=player.blood+50>player.max_blood?player.max_blood:player.blood+50;
				break;
			case 1://增加攻击
				player.setHarm(player.getHarm()+1);
				break;
			case 2:
				
		}
	}
	public void drawProp(Graphics g) {
		alive=alive&isStillAlive();
		if(alive) {
			x+=speed*Math.cos(degree);
			y+=speed*Math.sin(degree);
			if(x<=0||x>=Data.WINDOW_WIDTH-width)
				degree=Math.PI-degree;
			g.drawImage(image, x, y, width, height, null);
		}
	}
	/**
	 * 是否被玩家吃到
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isStillAlive() {
		if(y>Data.WINDOW_HEIGHT)
			return false;
		return true;
	}
}
