package in.logan.webservicebasedloganlyzer.emailservice.emailinfo;

public class EmailInfo {
	private String from;
	private String to;
	private String body;

	public EmailInfo(String from, String to, String body) {
		this.from = from;
		this.to = to;
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getBody() {
		return body;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof EmailInfo))
			return false;
		else {
			EmailInfo emailInfo = (EmailInfo) obj;
			return emailInfo.getTo() == to && emailInfo.getFrom() == from
					&& emailInfo.getBody() == body;
		}
	}
	
	@Override
	public String toString() {
		return "["+ from+","+to+","+body+"]";
	}
}
