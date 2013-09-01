import java.io.IOException;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;


public class Logic {

	private List fileList;
	private Shell shell;
	private SearchConnection search = new SearchConnection();
	private SendData send = new SendData();
	
	public Logic(Shell shell) {
		this.shell = shell;
	}
	
	public void Initialize(List fileList) {
		this.fileList = fileList;
	}
	
	public void loadFile() {
		FileDialog openFile = new FileDialog(shell);
		String fileName = openFile.open();
		if (fileName != null) {
			Resources.files.add(fileName);
			int pos = fileName.lastIndexOf("\\");
			String file = fileName;
			if (pos != -1) {
				file = fileName.substring(pos + 1); 
			}
			fileList.add(file);
		}
	}
	
	public void deleteFile() {
		int index = fileList.getSelectionIndex();
		if (index >= 0) {
			fileList.remove(index);
			Resources.files.remove(index);
		}
	}
	
	public void startServer(String name) throws IOException {
		search.start();
		send.start();
	}
	
	public void interrupt() {
		search.interrupt();
		send.interrupt();
	}
	
}
