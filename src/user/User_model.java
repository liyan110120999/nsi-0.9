package user;

public class User_model {
    private int id;           //=1;
	private String name;      //
	private String password;  //
	private int Member_sign;	//用户级别标记位
	private String User_TureName;	//用户真实姓名	
	private String User_Organization;
	private String User_position;
	private String User_phone;
	private String User_registerCode;
	private String User_score;
	private String Load_time;
	private String WechatId;
	

	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getMember_sign() {
		return Member_sign;
	}
	public void setMember_sign(int member_sign) {
		this.Member_sign = member_sign;
	}
	public String getUser_TureName() {
		return User_TureName;
	}
	public void setUser_TureName(String user_TureName) {
		this.User_TureName = user_TureName;
	}
	public String getUser_Organization() {
		return User_Organization;
	}
	public void setUser_Organization(String user_Organization) {
		this.User_Organization = user_Organization;
	}
	public String getUser_position() {
		return User_position;
	}
	public void setUser_position(String user_position) {
		this.User_position = user_position;
	}
	public String getUser_phone() {
		return User_phone;
	}
	public void setUser_phone(String user_phone) {
		this.User_phone = user_phone;
	}
	public String getUser_registerCode() {
		return User_registerCode;
	}
	public void setUser_registerCode(String user_registerCode) {
		this.User_registerCode = user_registerCode;
	}
	public String getUser_score() {
		return User_score;
	}
	public void setUser_score(String user_score) {
		this.User_score = user_score;
	}
	public String getLoad_time() {
		return Load_time;
	}
	public void setLoad_time(String load_time) {
		this.Load_time = load_time;
	}
	public String getWechatId() {
		return WechatId;
	}
	public void setWechatId(String wechatId) {
		this.WechatId = wechatId;
	}
	
	
}
