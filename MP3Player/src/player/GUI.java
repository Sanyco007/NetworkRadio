package player;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class GUI {

	protected Shell songTitle;
	private MP3Player player;
	private Text ipText;
	private TextConection x = new TextConection();
	
	public void Initialize(MP3Player player) {
		this.player = player;
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		songTitle.open();
		songTitle.layout();
		while (!songTitle.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		songTitle = new Shell();
		songTitle.setSize(505, 164);
		songTitle.setText("RadioClient");
		GridLayout gl_songTitle = new GridLayout(3, false);
		gl_songTitle.marginBottom = 10;
		gl_songTitle.marginRight = 5;
		gl_songTitle.marginLeft = 5;
		gl_songTitle.marginWidth = 0;
		songTitle.setLayout(gl_songTitle);
		
		Label lblNewLabel = new Label(songTitle, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("IP-\u0410\u0434\u0440\u0435\u0441:");
		
		ipText = new Text(songTitle, SWT.BORDER);
		ipText.setText("127.0.0.1");
		ipText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button connectButton = new Button(songTitle, SWT.NONE);
		connectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				player.start();
				x.start();
			}
		});
		GridData gd_connectButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_connectButton.widthHint = 101;
		connectButton.setLayoutData(gd_connectButton);
		connectButton.setText("\u041F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		
		Label lblNewLabel_1 = new Label(songTitle, SWT.NONE);
		lblNewLabel_1.setText("\u0418\u043C\u044F \u0441\u0442\u0430\u043D\u0446\u0438\u0438:");
		
		Label nameLabel = new Label(songTitle, SWT.NONE);
		nameLabel.setText("undefined");
		new Label(songTitle, SWT.NONE);
		
		Label songLabel = new Label(songTitle, SWT.NONE);
		songLabel.setText("\u041F\u0435\u0441\u043D\u044F:");
		new Label(songTitle, SWT.NONE);
		new Label(songTitle, SWT.NONE);
		
		Composite composite = new Composite(songTitle, SWT.BORDER);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
		gd_composite.widthHint = 478;
		gd_composite.heightHint = 41;
		composite.setLayoutData(gd_composite);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(10, 10, 55, 15);
		lblNewLabel_2.setText("no song");
		
		x.initialize(nameLabel, lblNewLabel_2);

	}
}
