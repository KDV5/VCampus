package seu.edu.client.view.Library;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class WelcomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public WelcomePanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(WelcomePanel.class.getResource("/Image/Library/UI/欢迎界面 panel.png")));
		lblNewLabel.setBounds(0, 0, 814, 640);
		add(lblNewLabel);

	}
}
