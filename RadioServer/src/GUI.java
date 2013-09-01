import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class GUI {
	
	private Display display;
	private Shell shell;
	private Logic logic;

	public GUI() {
		display = Display.getDefault();
		shell = new Shell(display, SWT.SHELL_TRIM);
		logic = new Logic(shell);
		createContents();
		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				logic.interrupt();
			}
		});
	}
	
	public void start() {
		try {
			Display display = Display.getDefault();
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void createContents() {
		shell.setText("RadioServer");
		shell.setSize(616, 441);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginHeight = 0;
		shell.setLayout(gridLayout);
		
		Composite composite = new Composite(shell, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_composite.widthHint = 202;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_1.setText("\u0424\u0430\u0439\u043B\u044B:");
		new Label(composite, SWT.NONE);
		
		List fileList = new List(composite, SWT.BORDER);
		fileList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(2, false));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_composite_2.heightHint = 34;
		composite_2.setLayoutData(gd_composite_2);
		
		Button deleteButton = new Button(composite_2, SWT.NONE);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				logic.deleteFile();
			}
		});
		GridData gd_deleteButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_deleteButton.widthHint = 91;
		deleteButton.setLayoutData(gd_deleteButton);
		deleteButton.setText("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		
		Button addButton = new Button(composite_2, SWT.NONE);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				logic.loadFile();
			}
		});
		GridData gd_addButton = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_addButton.widthHint = 85;
		addButton.setLayoutData(gd_addButton);
		addButton.setText("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 0;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.horizontalSpacing = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(new GridLayout(2, false));
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite_3.heightHint = 57;
		composite_3.setLayoutData(gd_composite_3);
		
		Label lblNewLabel_2 = new Label(composite_3, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("\u0418\u043C\u044F \u0441\u0442\u0430\u043D\u0446\u0438\u0438:");
		
		final Text nameText = new Text(composite_3, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		
		Button startButton = new Button(composite_3, SWT.NONE);
		startButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					logic.startServer(nameText.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridData gd_startButton = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_startButton.widthHint = 96;
		startButton.setLayoutData(gd_startButton);
		startButton.setText("\u0417\u0430\u043F\u0443\u0441\u043A");
		
		Composite composite_4 = new Composite(composite_1, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_3.setText("\u041A\u043B\u0438\u0435\u043D\u0442\u044B:");
		
		List clientList = new List(composite_4, SWT.BORDER);
		clientList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		logic.Initialize(fileList);
		
		Label lblNewLabel = new Label(shell, SWT.BORDER);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		lblNewLabel.setText("\u0412\u0440\u0435\u043C\u044F \u0440\u0430\u0431\u043E\u0442\u044B \u0441\u0435\u0440\u0432\u0435\u0440\u0430:");
	}

}
