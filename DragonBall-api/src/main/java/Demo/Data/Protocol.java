package Demo.Data;

public final class Protocol {
	public static final String FILLER="@Filler@";
	public static final String SIGN_UP_FLAG="#SIGN_UP_FLAG#";
	public static final String SIGN_IN_FLAG="#SIGN_IN_FLAG#";
	public static final String QUERY_COINS_FLAG="#QUERY_COINS_FLAG#";
	public static final String QUERY_TOP_SCORE_FLAG="#QUERY_TOP_SCORE_FLAG#";
	public static final String QUERY_TOP_SCORE_BOSS_FLAG="#QUERY_TOP_SCORE_BOSS_FLAG#";
	public static final String UPDATE_COINS_FLAG="#UPDATE_COINS_FLAG#";
	public static final String UPDATE_TOP_SCORE_FLAG="#UPDATE_TOP_SCORE_FLAG#";
	public static final String UPDATE_TOP_SCORE_BOSS_FLAG="#UPDATE_TOP_SCORE_BOSS_FLAG#";
	
	public static final String ID_FLAG="#ID_FLAG#";
	public static final String COINS_FLAG="#COINS_FLAG#";
	public static final String TOP_SCORE_FLAG="#TOP_SCORE_FLAG#";
	public static final String TOP_SCORE_BOSS_FLAG="#TOP_SCORE_BOSS_FLAG#";
	
	public static final int USER_NAME_IS_REPETITION=-1;
	public static final int SERVER_ERROR=-2;
	public static final int USER_NAME_OR_PASSWORD_ERROR=-3;
	
	//client want to disconnect with server 
	public static final String TCPSERVER_DISCONNECT_FLAG="#END#";
}
