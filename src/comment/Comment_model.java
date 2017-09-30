package comment;

public class Comment_model {
	
	private int Id;
	private String Reviewer;
	private String Classify;
	private String SubjectId;
	private String ReleaseTime;
	private String ReleaseTimeSecond;
	private String Thumbs_up;
	private String Thumbs_down;
	private String Content;
	private String VerifySign;
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getReviewer() {
		return Reviewer;
	}
	public void setReviewer(String reviewer) {
		this.Reviewer = reviewer;
	}
	public String getClassify() {
		return Classify;
	}
	public void setClassify(String classify) {
		this.Classify = classify;
	}
	public String getSubjectId() {
		return SubjectId;
	}
	public void setSubjectId(String subjectId) {
		this.SubjectId = subjectId;
	}
	public String getReleaseTime() {
		return ReleaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.ReleaseTime = releaseTime;
	}
	public String getReleaseTimeSecond() {
		return ReleaseTimeSecond;
	}
	public void setReleaseTimeSecond(String releaseTimeSecond) {
		this.ReleaseTimeSecond = releaseTimeSecond;
	}
	public String getThumbs_up() {
		return Thumbs_up;
	}
	public void setThumbs_up(String thumbs_up) {
		this.Thumbs_up = thumbs_up;
	}
	public String getThumbs_down() {
		return Thumbs_down;
	}
	public void setThumbs_down(String thumbs_down) {
		this.Thumbs_down = thumbs_down;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		this.Content = content;
	}
	public String getVerifySign() {
		return VerifySign;
	}
	public void setVerifySign(String verifySign) {
		this.VerifySign = verifySign;
	}
	
	
	
}
