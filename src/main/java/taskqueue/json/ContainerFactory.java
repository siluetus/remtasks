package taskqueue.json;

import java.util.List;
import java.util.Map;

public class ContainerFactory implements org.json.simple.parser.ContainerFactory {

	public Map<String,JsonReply> createObjectContainer() {
		return new JsonReplyMap<JsonReply>();
	}

	public List<JsonReply> creatArrayContainer() {
		return new JsonReplyList<JsonReply>();
	}

}
