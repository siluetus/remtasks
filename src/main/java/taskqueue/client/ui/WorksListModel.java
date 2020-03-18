package taskqueue.client.ui;

import java.util.List;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class WorksListModel extends AbstractListModel<Work> {
	
	protected List<Work> worklist = new ArrayList<Work>();
	
	public Work getElementAt(int arg0) {
		return worklist.get(arg0);
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return worklist.size();
	}

	@Override
	protected void fireContentsChanged(Object arg0, int arg1, int arg2) {
		
		super.fireContentsChanged(arg0, arg1, arg2);
	}
	

}
