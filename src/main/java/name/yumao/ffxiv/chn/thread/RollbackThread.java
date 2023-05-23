package name.yumao.ffxiv.chn.thread;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import name.yumao.ffxiv.chn.swing.PercentPanel;
import name.yumao.ffxiv.chn.swing.TextPatchPanel;
import name.yumao.ffxiv.chn.util.FileUtil;

public class RollbackThread implements Runnable {

	private String resourceFolder;
	private TextPatchPanel textPatchPanel;
	private boolean showDialog;
	
	public RollbackThread(String resourceFolder, TextPatchPanel textPatchPanel, boolean showDialog) {
		this.resourceFolder = resourceFolder;
		this.textPatchPanel = textPatchPanel;
		this.showDialog = showDialog;
	}
	
	public void run() {
		Logger log = Logger.getLogger("GPLogger");
		try {
			this.textPatchPanel.rollbackButton.setEnabled(false);
			log.info("[Rollback] Start rollback.");
			String[] resourceNames = { "000000.win32.dat0", "000000.win32.index", "000000.win32.index2", "0a0000.win32.dat0", "0a0000.win32.index", "0a0000.win32.index2" };
			int fileCount = 0;
			PercentPanel percentPanel = new PercentPanel("資源還原");
			percentPanel.progressShow("正在還原……", "");
			for (String resourceName : resourceNames) {
				percentPanel.percentShow(++fileCount / resourceNames.length);
				File backupFile = new File("backup" + File.separator + resourceName);
				if (backupFile.exists() && backupFile.isFile()) {
					log.info(String.format("[Rollback] %s, %dKB", resourceName, backupFile.length() / 1024));
					FileUtil.copyTo(backupFile, this.resourceFolder + File.separator + backupFile.getName()); 
				}
			} 
			percentPanel.dispose();
			if (this.showDialog)
				JOptionPane.showMessageDialog(null, "<html><body>還原完畢</body></html>", "提示", -1); 
			this.textPatchPanel.rollbackButton.setEnabled(true);
			this.textPatchPanel.replaceButton.setEnabled(true);
			log.info("[Rollback] Rollback completed.");
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "<html><body>程式錯誤！</body></html>", "還原錯誤", 0);
			log.log(Level.SEVERE, "Error Messages:", exception);
			exception.printStackTrace();
		} 
	}
}
