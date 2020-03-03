package taskqueue.client.response.json;

import java.util.List;
import java.util.Map;

public class ContainerFactory implements org.json.simple.parser.ContainerFactory {

	public Map createObjectContainer() {
		return new JsonReplyMap<String,JsonReply>();
	}

	public List creatArrayContainer() {
		return new JsonReplyList<JsonReply>();
	}

}
