package Demo.Data;

import java.awt.Toolkit;

/**
 * 常量
 * @author Administrator
 *
 */
public final class Data {
	
	
	//----------------------------GameFrame-----------------------------------------
	/**
	 * 游戏界面宽度
	 */
	public static int WINDOW_WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width/3*2;
	/**
	 * 游戏界面高度
	 */
	public static int WINDOW_HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().height;
	/**
	 * 游戏界面水平位置
	 */
	public static int WINDOW_POSITION_X=100;
	/**
	 * 游戏界面垂直位置
	 */
	public static final int WINDOW_POSITION_Y=0;
	/**
	 * 背景移动速度
	 */
	public static final int BACKGROUND_SPEED_Y=8;
	/**
	 * 默认字体大小
	 */
	public static final int FONT_SIZE=30;
	/**
	 * 刷新时间间隔
	 */
	public static final int REFALSH_INTERVAL=40;
	/**
	 * 玩家数据框的宽度
	 */
	public static  int HEAD_PORTRAIT_FRAME_WIDTH=WINDOW_WIDTH/2;
	/**
	 * 玩家数据框的高度
	 */
	public static final int HEAD_PORTRAIT_FRAME_HEIGHT=HEAD_PORTRAIT_FRAME_WIDTH/4;
	/**
	 * 玩家头像框的水平位置
	 */
	public static final int HEAD_PORTRAIT_X=15;
	/**
	 * 玩家头像框的垂直位置
	 */
	public static final int HEAD_PORTRAIT_Y=15;
	/**
	 * 玩家头像框的宽度
	 */
	public static final int HEAD_PORTRAIT_WIDTH=HEAD_PORTRAIT_FRAME_WIDTH/5+10;
	/**
	 * 玩家头像框的高度
	 */
	public static final int HEAD_PORTRAIT_HEIGHT=HEAD_PORTRAIT_FRAME_HEIGHT*2/3;
	/**
	 * 玩家血条最大宽度
	 */
	public static final int BLOOD_STRIP_MAX_WIDTH=HEAD_PORTRAIT_FRAME_WIDTH*3/5;
	/**
	 * 玩家血条高度
	 */
	public static final int BLOOD_STRIP_HEIGHT=HEAD_PORTRAIT_FRAME_HEIGHT/5;
	/**
	 * 玩家血条水平位置
	 */
	public static final int BLOOD_STRIP_X=HEAD_PORTRAIT_X+HEAD_PORTRAIT_WIDTH;
	/**
	 * 玩家血条垂直位置
	 */
	public static final int BLOOD_STRIP_Y=HEAD_PORTRAIT_FRAME_HEIGHT*3/4;
	/**
	 * 玩家经验条宽度
	 */
	public static final int EXP_STRIP_MAX_WIDTH=BLOOD_STRIP_MAX_WIDTH;
	/**
	 * 玩家经验条高度
	 */
	public static final int EXP_STRIP_HEIGHT=BLOOD_STRIP_HEIGHT;
	/**
	 * 玩家经验条水平坐标
	 */
	public static final int EXP_STRIP_X=BLOOD_STRIP_X;
	/**
	 * 玩家经验条垂直坐标
	 */
	public static final int EXP_STRIP_Y=BLOOD_STRIP_Y-EXP_STRIP_HEIGHT-5;
	/**
	 * "EXP"字显示水平坐标
	 */
	public static final int EXP_CHARACERS_POSITION_X=BLOOD_STRIP_X+BLOOD_STRIP_MAX_WIDTH+10;
	/**
	 * "EXP"字显示垂直坐标
	 */
	public static final int EXP_CHARACERS_POSITION_Y=BLOOD_STRIP_Y;
	/**
	 * "HP"字显示水平坐标
	 */
	public static final int BLOOD_CHARACERS_POSITION_X=EXP_CHARACERS_POSITION_X;
	/**
	 * "HP"字显示垂直坐标
	 */
	public static final int BLOOD_CHARACERS_POSITION_Y=EXP_CHARACERS_POSITION_Y+FONT_SIZE+10;
	/**
	 * BOSS头框宽度
	 */
	public static  int BOSS_HEAD_PORTRAIT_WIDTH=WINDOW_WIDTH/3;
	/**
	 * BOSS头框高度
	 */
	public static final int BOSS_HEAD_PORTRAIT_HEIGHT=75;
	/**
	 * BOSS头框水平位置
	 */
	public static  int BOSS_HEAD_PORTRAIT_X=HEAD_PORTRAIT_FRAME_WIDTH+WINDOW_WIDTH/10;
	/**
	 * BOSS头框垂直位置
	 */
	public static  int BOSS_HEAD_PORTRAIT_Y=WINDOW_HEIGHT/20;
	/**
	 * BOSS血条最大宽度
	 */
	public static final int BOSS_BLOOD_STRIP_MAX_WIDTH=BOSS_HEAD_PORTRAIT_WIDTH*7/12;
	/**
	 * BOSS血条高度
	 */
	public static final int BOSS_BLOOD_STRIP_HEIGHT=20;
	/**
	 * BOSS血条水平坐标
	 */
	public static final int BOSS_BLOOD_STRIP_X=BOSS_HEAD_PORTRAIT_X*11/10;
	/**
	 * BOSS血条垂直坐标
	 */
	public static final int BOSS_BLOOD_STRIP_Y=BOSS_HEAD_PORTRAIT_Y*5/4;
	/**
	 * BOSS头像（图片）宽度
	 */
	public static final int BOSS_HEAD_PORTRAIT_IMG_WIDTH=BOSS_HEAD_PORTRAIT_HEIGHT;
	/**
	 * BOSS头像（图片）高度
	 */
	public static final int BOSS_HEAD_PORTRAIT_IMG_HEIGHT=BOSS_HEAD_PORTRAIT_IMG_WIDTH;
	/**
	 * BOSS头像（图片）水平位置
	 */
	public static final int BOSS_HEAD_PORTRAIT_IMG_X=BOSS_HEAD_PORTRAIT_X+BOSS_HEAD_PORTRAIT_WIDTH*3/4;
	/**
	 * BOSS头像（图片）垂直位置
	 */
	public static final int BOSS_HEAD_PORTRAIT_IMG_Y=BOSS_HEAD_PORTRAIT_Y;
	/**
	 * MenuButton菜单按钮宽度
	 */
	public static  int MENU_BUTTON_WIDTH=WINDOW_WIDTH/10;
	/**
	 * MenuButton菜单按钮高度
	 */
	public static  int MENU_BUTTON_HEIGHT=MENU_BUTTON_WIDTH;
	/**
	 * MenuButton菜单按钮水平位置
	 */
	public static  int MENU_BUTTON_X=WINDOW_WIDTH-MENU_BUTTON_WIDTH;
	/**
	 * MenuButton菜单按钮垂直位置
	 */
	public static  int MENU_BUTTON_Y=WINDOW_POSITION_Y+WINDOW_HEIGHT-MENU_BUTTON_HEIGHT;
	/**
	 * 4个技能按钮
	 * 按钮宽度
	 */
	public static int SKILL_BUTTON_WIDTH=MENU_BUTTON_WIDTH;
	/**
	 * 4个技能按钮
	 * 按钮高度
	 */
	public static int SKILL_BUTTON_HEIGHT=SKILL_BUTTON_WIDTH;
	/**
	 * 4个技能按钮
	 * 第一个按钮水平位置
	 */
	public static int SKILL_BUTTON_POSITION_X=0;
	/**
	 * 4个技能按钮
	 * 按钮垂直位置
	 */
	public static int SKILL_BUTTON_POSITION_Y=WINDOW_HEIGHT-SKILL_BUTTON_HEIGHT;
	/**
	 * 4个技能按钮
	 * 按钮间水平间隔
	 */
	public static int SKILL_BUTTON_INTERVAL=SKILL_BUTTON_WIDTH;
	
	//----------------------------MainMenuFrame--------------------------------------
	/**
	 * 主界面按钮宽度
	 */
	public static  int MAIN_MENU_FRAME_BUTTON_WIDTH=WINDOW_WIDTH/5;
	/**
	 * 主界面按钮高度
	 */
	public static  int MAIN_MENU_FRAME_BUTTON_HEIGHT=WINDOW_HEIGHT/12;
	/**
	 * 主界面按钮间垂直间隔
	 */
	public static final int MAIN_MENU_FRAME_BUTTONS_INTERVAL=MAIN_MENU_FRAME_BUTTON_HEIGHT+30;
	/**
	 * 主界面按钮水平位置
	 */
	public static final int MAIN_MENU_FRAME_BUTTON_POSITION_X=MAIN_MENU_FRAME_BUTTON_WIDTH/2;
	/**
	 * 主界面按钮垂直位置
	 */
	public static final int MAIN_MENU_FRAME_BUTTON_POSITION_START_Y=40;
	/**
	 * 金币图标水平位置
	 */
	public static final int COINS_IMG_POSITION_X=WINDOW_WIDTH*2/3;
	/**
	 * 金币图标垂直位置
	 */
	public static final int COINS_IMG_POSITION_Y=20;
	/**
	 * 金币图标宽度
	 */
	public static final int COINS_IMG_WIDTH=FONT_SIZE+20;
	/**
	 * 金币图标高度
	 */
	public static final int COINS_IMG_HEIGHT=COINS_IMG_WIDTH;
	//----------------------------对话框---------------------------------------
	/**
	 * 对话框宽度
	 */
	public static  int DIALOG_WIDTH=WINDOW_WIDTH;
	/**
	 * 对话框高度
	 */
	public static  int DIALOG_HEIGHT=WINDOW_HEIGHT;
	/**
	 * 对话框水平位置
	 */
	public static  int DIALOG_POSITION_X=WINDOW_POSITION_X;
	/**
	 * 对话框垂直位置
	 */
	public static final int DIALOG_POSITION_Y=WINDOW_POSITION_Y;
	/**
	 * 对话框中按钮宽度
	 */
	public static final int DIALOG_BUTTON_WIDTH=MAIN_MENU_FRAME_BUTTON_WIDTH;
	/**
	 * 对话框中按钮高度
	 */
	public static final int DIALOG_BUTTON_HEIGHT=MAIN_MENU_FRAME_BUTTON_HEIGHT;
	//---------------------------------choosePlayerPlaneFrame------------------------------------------
	
	/**
	 * choosePlayerPlaneFrame界面中
	 * 确认按钮的水平位置
	 */
	public static  int CHOOSE_PLAYER_PLANE_FRAME_CONFIRM_BUTTON_POSITION_X=WINDOW_WIDTH/2-DIALOG_BUTTON_WIDTH/2;
	/**
	 * choosePlayerPlaneFrame界面中
	 * 确认按钮的垂直位置
	 */
	public static  int CHOOSE_PLAYER_PLANE_FRAME_CONFIRM_BUTTON_POSITION_Y=WINDOW_HEIGHT*2/3;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  飞机图片的高度
	 */
	public static  int CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_WIDTH=WINDOW_WIDTH/4;
	/**
	 * choosePlayerPlaneFrame界面中
	 * 飞机图片的高度
	 */
	public static final int CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_HEIGHT=CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_WIDTH;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  飞机图片的水平位置
	 */
	public static  int CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_POSITION_X=WINDOW_WIDTH/2
			-CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_WIDTH/2;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  飞机图片的垂直位置
	 */
	public static  int CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_POSITION_Y=WINDOW_HEIGHT/3;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  左右按钮的宽度
	 */
	public static final int CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_WIDTH=DIALOG_BUTTON_WIDTH/2;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  左右按钮的高度
	 */
	public static final int CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_HEIGHT=DIALOG_BUTTON_HEIGHT*2;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  左右按钮的垂直坐标
	 */
	public static  int CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_POSITION_Y=WINDOW_HEIGHT/2
			-CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_HEIGHT/2;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  左按钮的水平坐标
	 */
	public static final int CHOOSE_PLAYER_PLANE_FRAME_TURN_LEFT_BUTTON_POSITION_X=0;
	/**
	 * choosePlayerPlaneFrame界面中
	 *  右按钮的水平坐标
	 */
	public static  int CHOOSE_PLAYER_PLANE_FRAME_TURN_RIGHT_BUTTON_POSITION_X=WINDOW_WIDTH
			-CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_WIDTH;
	
	public static  int CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_STANDARD_WIDTH=WINDOW_WIDTH/4;
	public static final int CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_HEIGHT=BLOOD_STRIP_HEIGHT;
	public static final int CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE=CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_HEIGHT;
	public static  int CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_POSITION_X=WINDOW_WIDTH/15;
	public static  int CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FIRST_POSITION_Y=WINDOW_HEIGHT*6/7;
	public static final int CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_INTERVAL=
			CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE+10;
	public static final int CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_POSITION_X=CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_POSITION_X
			+CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE*2+50;
	public static  int CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_FIRST_POSITION_Y=
			CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FIRST_POSITION_Y-CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_HEIGHT;
	public static final int CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_INTERVAL=CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_INTERVAL;
	//chooseBossFrame
	public static  int CHOOSE_BOSS_BUTTON_WIDTH=WINDOW_WIDTH/8;
	public static final int CHOOSE_BOSS_BUTTON_HEIGHT=CHOOSE_BOSS_BUTTON_WIDTH;
	public static  int CHOOSE_BOSS_BUTTON_POSITIN_X=WINDOW_WIDTH/4;
	public static  int CHOOSE_BOSS_BUTTON_FIRST_POSITION_Y=WINDOW_HEIGHT/20;
	public static  int CHOOSE_BOSS_BUTTON_INTERVAL_X=WINDOW_WIDTH*3/8;
	public static  int CHOOSE_BOSS_BUTTON_INTERVAL_Y=CHOOSE_BOSS_BUTTON_HEIGHT+WINDOW_HEIGHT/10;
	
	public static  int CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_WIDTH=WINDOW_WIDTH/3;
	public static final int CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_HEIGHT=CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_WIDTH/4;
	public static  int CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_POSITION_X=WINDOW_WIDTH/3;
	public static  int CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_POSITION_Y=WINDOW_HEIGHT*4/5;
	//endDialog
	public static  int END_DIALOG_POSITION_X=WINDOW_WIDTH*5/12;
	public static  int END_DIALOG_FIRST_POSITION_Y=WINDOW_HEIGHT/3;
	public static final int END_DIALOG_INTERVAL=FONT_SIZE*2;
	
	public static  int END_DIALOG_COIN_POSITION_Y=WINDOW_HEIGHT*31/48;
	public static  int END_DIALOG_COIN_POSITION_X=WINDOW_WIDTH*8/12;
	//setDialog
	public static  int SET_DIALOG_SELECT_BUTTON_POSITION_X1=WINDOW_WIDTH/10;
	public static  int SET_DIALOG_SELECT_BUTTON_POSITION_X2=WINDOW_WIDTH*3/5;
	public static  int SET_DIALOG_SELECT_BUTTON_POSITION_Y=WINDOW_HEIGHT/15;
	public static  int SET_DIALOG_SELECT_BUTTON_INTERVAL_Y=WINDOW_HEIGHT/10;
	public static  int SET_DIALOG_SELECT_BUTTON_WIDTH=WINDOW_WIDTH/3;
	public static  int SET_DIALOG_SELECT_BUTTON_HEIGHT=WINDOW_HEIGHT/10;
	/**
	 * 
	 */
	
	public static  int PLAYER_START_X=WINDOW_WIDTH/2-30;
	public static  int PLAYER_START_Y=WINDOW_HEIGHT-130;
	public static  int PLAYER_WIDTH=WINDOW_WIDTH/15;
	public static final int PLAYER_HEIGHT=PLAYER_WIDTH;
	public static final int PLAYER_SPEED=15;
	public static final int PLAYER_BLOOD=100;
	public static final int PLAYER_UPGRADE_BLOOD=50;
	public static final int PLAYER_UPGRADE_SPEED=1;
	public static final int PLAYER_UPGRADE_HARM=0;
	public static final int[] PLAYER_UPGRADE_EXP= {300,3000,30000,Integer.MAX_VALUE};

	
	//����
	public static final int ROUND=5;
	public static  int ENEMY_PlANE_WIDTH=WINDOW_WIDTH/14;
	public static final int ENEMY_PlANE_HEIGHT=ENEMY_PlANE_WIDTH;
	public static final int ENEMY_PLANE_SPEED=15;
	public static final double ENEMY_PLANE_FIRE_PROBABILITY=0.01;
	public static final int ENEMY_PLANE_BLOOD=1;
	
	public static  int BOSS_POSITION_X=WINDOW_WIDTH/2;
	public static final int BOSS_POSITION_Y=100;
	public static final int BOSS_SPEED=2;
	public static  int BOSS_WIDTH=WINDOW_WIDTH/5;
	public static  int BOSS_HEIGHT=BOSS_WIDTH;
	public static final int[] BOSS_BLOOD={1500,10000,100000,12000,25000};
//	public static final int[] BOSS_BLOOD={10,10,100000,12000,25000};
	public static final int BOSS_FORM_NUM=4;//boss形态
	public static final double BOSS_FIRE_PROBABILITY=0.05;
	public static final double BOSS_CHANGE_PROBABILITY=0.01;
	public static final int BOSS_HARM=5;//boss基础伤害
	/**
	 * 玩家技能
	 */
	public static final int PLAYER_SHELL_WIDTH=30;
	public static final int PLAYER_SHELL_HEIGHT=60;
	public static final int PLAYER_SHELL_SPEED=15;
	public static final int PLAYER_SHELL_HARM=1;
	public static final int SKILL_Q_NUM=5;
	public static final int SKILL_Q_COOLING_TIME=REFALSH_INTERVAL*7;
	public static final int SKILL_W_COOLING_TIME=REFALSH_INTERVAL*15;
	public static final int SKILL_W_MAX_TIME=50;
	public static final int SKILL_E_COOLING_TIME=REFALSH_INTERVAL*12;
	public static final int SKILL_E_DURATION=25;
	public static final int SKILL_R_COOLING_TIME=REFALSH_INTERVAL*30;
	public static final int EXPLODE_RANGE_OF_ATOMIC_BOMB=350;
	
	
	/**
	 * 敌人
	 */
	public static final int ENEMY_SHELL_WIDTH=25;
	public static final int ENEMY_SHELL_HEIGHT=50;
	public static final int ENEMY_SHELL_SPEED=10;
	public static final int ENEMY_SHELL_HARM=1;
	public static final int ENEMY_SHELL_BAD_STATE_DURATION=30;
	public static final int BOSS_SHELL_WIDTH=100;
	public static final int BOSS_SHELL_HEIGHT=200;
	public static final int BOSS_SHELL_SPEED=10;
	public static final int BOSS_SHELL_HARM=8;
	public static final int BOSS_SHELL_IMAGE_NUM=4;
	public static final int BOSS_ONE_SHELL_WIDTH=50;
	public static final int BOSS_ONE_SHELL_HEIGHT=50;
	/**
	 * signInFrame
	 */
	public static final int SIGNIN_FRAME_TEXT_FIELD_POSITION_X=WINDOW_WIDTH/3;
	public static final int SIGNIN_FRAME_TEXT_FIELD_POSITION_Y=WINDOW_HEIGHT/10;
	public static final int SIGNIN_FRAME_TEXT_FIELD_WIDTH=WINDOW_WIDTH/3;
	public static final int SIGNIN_FRAME_TEXT_FIELD_HEIGHT=FONT_SIZE+20;
	public static final int SIGNIN_FRAME_BUTTON_FIRST_POSITION_X=WINDOW_WIDTH/10;
	public static final int SIGNIN_FRAME_BUTTON_POSTION_Y=SIGNIN_FRAME_TEXT_FIELD_POSITION_Y+4*SIGNIN_FRAME_TEXT_FIELD_HEIGHT;
	public static final int SIGNIN_FRAME_BUTTON_WIDTH=WINDOW_WIDTH/8;
	public static final int SIGNIN_FRAME_BUTTON_HEIGHT=SIGNIN_FRAME_TEXT_FIELD_HEIGHT;
	public static final int SIGNIN_FRAME_BUTTON_INTERVAL=WINDOW_WIDTH/5;
	
	
	public static final int PROP_WIDTH=100;
	public static final int PROP_HEIGHT=100;
	public static final int PROP_SPEED=10;
	
	
	public static final String FILE_PATH="data/PlayerData.txt";
	
	public static String Target_IP="127.0.0.1";
	public static int Port=8888;
	
}
