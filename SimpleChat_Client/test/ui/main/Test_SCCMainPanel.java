package ui.main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_SCCMainPanel {

	@Test
	public void test_writeMessage() {
		SCCMainPanel mainPanel = new SCCMainPanel();
		mainPanel.writeMessage("Boks");
		String str = mainPanel.getTextArea().getText();
		assertNotNull(str);
	}
	
	@Test
	public void test() {
		// TODO
	}

}
