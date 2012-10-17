package se.turbotorsk.mybar.test;

import com.jayway.android.robotium.solo.Solo;
import se.turbotorsk.mybar.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class TestRobotium extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public TestRobotium() {
		super("se.turbotorsk.mybar", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testStartApp() {
		// solo.assertCurrentActivity("Seems like its the wrong activity //Fungerar inte än",
		// MainActivity.class);
		// fail();
		solo.assertMemoryNotLow();
	}

}
