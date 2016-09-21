package pl.siata.tetromino.algorithm;

import org.junit.Assert;
import org.junit.Test;

import pl.siata83.tetromino.newalgo.NewBlock;
import pl.siata83.tetromino.newalgo.Volume;

public class NewVolumeTest {

	@Test
	public void testEmpty() {
		Volume newVolume = new Volume();
		Assert.assertEquals(0, newVolume.getFirstFree());
	}

	@Test
	public void testNotEmpty() {
		Volume newVolume = new Volume();
		newVolume.addNewBlock(new NewBlock(new Integer[] { 0, 1, 2, 7 }));
		Assert.assertEquals(3, newVolume.getFirstFree());
	}
	
	@Test
	public void testNotEmpty2() {
		Volume newVolume = new Volume();
		newVolume.addNewBlock(new NewBlock(new Integer[] { 0, 6, 12, 7 }));
		Assert.assertEquals(1, newVolume.getFirstFree());
	}

}
