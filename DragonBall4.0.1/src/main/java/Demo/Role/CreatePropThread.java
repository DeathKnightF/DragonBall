package Demo.Role;

import java.util.concurrent.ConcurrentLinkedQueue;

import Demo.Data.Data;
import Demo.Object.Prop;
import Demo.Object.Container.PropsQueue;
import Demo.Object.PlayerObject.Player;

public class CreatePropThread extends Thread{
	ConcurrentLinkedQueue<Prop> props;
	int num;
	int kind;
	Player player;
	public CreatePropThread(int num,int kind,Player player,PropsQueue props) {
		this.props=props;
		this.num=num;
		this.kind=kind;
		this.player=player;
	}
	@Override
	public void run() {
		for(int i=0;i<num;i++) {
			try {
			if(kind==0)//增加血量
				sleep((int)(Math.random()*60000));
			if(kind==1)//增加攻击
				sleep((int)(Math.random()*240000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			props.add(new Prop((int)(Math.random()*Data.WINDOW_WIDTH), 100, kind, player));
		}
	}
}
